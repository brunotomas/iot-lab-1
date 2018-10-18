package resources;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

import python.from.java.*;

public class TempHumidityResource extends CoapResource {	
	String pythonFilePath, params[], tempHumidity = "";
	double minHumidity = 0, maxHumidity = 100;
	double minTemp = 0, maxTemp = 100;
	
	final GpioController gpio;
	final GpioPinDigitalOutput humidityLed;
	final GpioPinDigitalOutput tempLed;
	PythonFromJava pythonCode;
	
	boolean isBlink = false;
	public TempHumidityResource(String name) {
		super(name);
		
		pythonFilePath = "/home/pi/trabalho/SensorReader.py";
		params = new String[1];
		params[0] = "25";
		pythonCode = new PythonFromJava(pythonFilePath, params);
		
		gpio = GpioFactory.getInstance();
		humidityLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "Humidity LED");
		tempLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "Temperature LED");
		
		Timer timer = new Timer(); timer.schedule(new
				UpdateTask(this), 0, 1000);
		
	}


	@Override
	public void handleGET(CoapExchange exchange){
		exchange.respond(ResponseCode.CONTENT,
				tempHumidity,
				MediaTypeRegistry.TEXT_PLAIN);
	}

	@Override
	public void handlePOST(CoapExchange exchange) {
		exchange.accept();
		String sensor = exchange.getRequestText().split(";")[0];
		if(sensor.equals("umidade")) {
			minHumidity = Double.parseDouble(exchange.getRequestText().split(";")[1]);
			maxHumidity = Double.parseDouble(exchange.getRequestText().split(";")[2]);
		}
		else if (sensor.equals("temperatura")) {
			minTemp = Double.parseDouble(exchange.getRequestText().split(";")[1]);
			maxTemp = Double.parseDouble(exchange.getRequestText().split(";")[2]);
		}
	}
	
	private class UpdateTask extends TimerTask {
		private CoapResource mCoapRes; 
		boolean maxH = false;
		boolean minH = false;
		boolean maxT = false;
		boolean minT = false;
		
		public UpdateTask(CoapResource coapRes) {
			mCoapRes = coapRes;
		} 
		
		@Override 
		public void run() {
						
			String valor = pythonCode.run();
									
			double humidity = Double.parseDouble(valor.split(";")[0]);
			double temp = Double.parseDouble(valor.split(";")[1]);	
			
			maxH = (humidity > maxHumidity);
			minH = (humidity < minHumidity);
			maxT = (temp > maxTemp);
			minT = (temp < minTemp);
			
			if ((maxH || minH) && (maxT || minT)) {
				humidityLed.toggle();
				tempLed.toggle();				
				isBlink = true;
				System.out.println("Piscando");
			}
			else if (maxH) {
				humidityLed.setState(true);
				tempLed.setState(false);
				isBlink = false;
				System.out.println("Umidade ON");
			}
			else if (maxT) {
				humidityLed.setState(false);
				tempLed.setState(true);
				isBlink = false;
				System.out.println("Temperatura ON");
			}
			else {
				System.out.println("Apagados");
				humidityLed.setState(false);
				tempLed.setState(false);
				isBlink = false;
			}
			
			if(!tempHumidity.equals(valor + ";" + isBlink + ";" + humidityLed.getState().isHigh() + ";" + tempLed.getState().isHigh())){
				tempHumidity = valor + ";" + isBlink + ";" + humidityLed.getState().isHigh() + ";" + tempLed.getState().isHigh();				
				mCoapRes.changed();
			}
			System.out.println(tempHumidity);
		}
	}
}
