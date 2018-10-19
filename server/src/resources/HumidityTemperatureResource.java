package resources;

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

public class HumidityTemperatureResource extends CoapResource {	
	
	String humidityTemperatureLedStates = ""; // stores current humidity, temperature and led states separated by commas
	
	// stores the ranges for current humidity and temperature
	double minHumidity = 0, maxHumidity = 100;
	double minTemperature = 0, maxTemperature = 100;
	
	// performs state control of a GPIO pin on the Raspberry Pi
	final GpioController gpio;
	final GpioPinDigitalOutput humidityLed;
	final GpioPinDigitalOutput temperatureLed;
	
	public HumidityTemperatureResource(String name) {
		super(name);

		gpio = GpioFactory.getInstance();
		humidityLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "Humidity LED");
		temperatureLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "Temperature LED");
		
		// schedule a periodic update task
		Timer timer = new Timer(); timer.schedule(new
				UpdateTask(this), 0, 1000);		
	}
	
	@Override
	// responds by informing the humidity, temperature and led states separated by commas
	public void handleGET(CoapExchange exchange){
		exchange.respond(ResponseCode.CONTENT,
				humidityTemperatureLedStates,
				MediaTypeRegistry.TEXT_PLAIN);
	}

	@Override
	// receives from client the type of sensor and the ranges for current humidity and temperature separated by commas
	public void handlePOST(CoapExchange exchange) {
		exchange.accept();
		
		String sensorType = exchange.getRequestText().split(";")[0];
		double minValue = Double.parseDouble(exchange.getRequestText().split(";")[1]);
		double maxValue = Double.parseDouble(exchange.getRequestText().split(";")[2]);

		if (sensorType.equals("temperatura")) {
			minTemperature = minValue;
			maxTemperature = maxValue;
		}
		
		if(sensorType.equals("umidade")) {
			minHumidity = minValue;
			maxHumidity = maxValue;
		}
	}
	
	// performs a periodic task
	private class UpdateTask extends TimerTask {
		private CoapResource mCoapRes;
		
		boolean isHumidityGreaterThanMaxHumidity = false;
		boolean isHumidityLessThanMinHumidity = false;
		boolean isTemperatureGreaterThanMaxTemperature = false;
		boolean isTemperatureLessThanMinTemperature = false;
				
		boolean isBlinking = false; // indicates whether the LEDs are blinking
		
		public UpdateTask(CoapResource coapRes) {
			mCoapRes = coapRes;
		} 
		
		@Override 
		public void run() {
						
			String newTemperatureHumidity = getTemperatureHumidity();
									
			double newHumidity = Double.parseDouble(newTemperatureHumidity.split(";")[0]);
			double newTempetature = Double.parseDouble(newTemperatureHumidity.split(";")[1]);
			
			isHumidityGreaterThanMaxHumidity = (newHumidity > maxHumidity);
			isHumidityLessThanMinHumidity = (newHumidity < minHumidity);
			isTemperatureGreaterThanMaxTemperature = (newTempetature > maxTemperature);
			isTemperatureLessThanMinTemperature = (newTempetature < minTemperature);
			
			// condition for blinking
			if ((isHumidityGreaterThanMaxHumidity || isHumidityLessThanMinHumidity) && (isTemperatureGreaterThanMaxTemperature || isTemperatureLessThanMinTemperature)) {
				humidityLed.toggle();
				temperatureLed.toggle();				
				isBlinking = true;
			}
			
			// condition to light the humidity led
			else if (isHumidityGreaterThanMaxHumidity) {
				humidityLed.setState(true);
				temperatureLed.setState(false);
				isBlinking = false;
			}
			
			// condition to light the temperature led
			else if (isTemperatureGreaterThanMaxTemperature) {
				humidityLed.setState(false);
				temperatureLed.setState(true);
				isBlinking = false;
			}
			
			// condition to turn off both leds
			else {
				System.out.println("Apagados");
				humidityLed.setState(false);
				temperatureLed.setState(false);
				isBlinking = false;
			}
			
			String newHumidityTemperatureLedStates = newTemperatureHumidity + ";" + isBlinking + ";" + humidityLed.getState().isHigh() + ";" + temperatureLed.getState().isHigh();
			
			// check whether there was a change in temperature, humidity or led states
			if(!humidityTemperatureLedStates.equals(newHumidityTemperatureLedStates)){
				humidityTemperatureLedStates = newHumidityTemperatureLedStates;				
				mCoapRes.changed();
			}
		}
	}
	
	public String getTemperatureHumidity(){
		String path = "/home/pi/trabalho/SensorReader.py"; // path to python script used to read the humidity and temperature from sensor
		String params[] = {"25"}; // parameters to python script // only the pin where the sensor is localized is passed
		return new PythonFromJava(path, params).run(); // execute the python script and return a value // for this case, return the humidity and separated by comma
	}
}
