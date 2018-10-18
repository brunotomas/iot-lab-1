package led.client;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.core.coap.CoAP.Code;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import utils.BoundData;
public class TempHumidityLedClient extends CoapClient {

	public static void main(String[] args) throws InterruptedException {
		CoapClient client = new CoapClient("coap://localhost:5683/temp-humidity");
		while (true) {
			Request request = new Request(Code.GET);
			//Synchronously send the GET message (blocking call)
			CoapResponse coapResp = client.advanced(request);
			//The "CoapResponse" message contains the response.
			//System.out.println(Utils.prettyPrint(coapResp));
			if(coapResp !=null) {
				double humidity = Double.parseDouble(coapResp.getResponseText().split(";")[0]);
				double temp = Double.parseDouble(coapResp.getResponseText().split(";")[1]);
				double minHumidity = Double.parseDouble(coapResp.getResponseText().split(";")[2]);
				double maxHumidity = Double.parseDouble(coapResp.getResponseText().split(";")[3]);
				double minTemp= Double.parseDouble(coapResp.getResponseText().split(";")[4]);
				double maxTemp = Double.parseDouble(coapResp.getResponseText().split(";")[5]);

				System.out.println(temp);
				System.out.println(humidity);
				//Do something with the response
				//Logic to turn ON OFF BLINK humidity/temp LEDs
				final GpioController gpio = GpioFactory.getInstance();
				final GpioPinDigitalOutput humidityLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "Humidity LED");
				final GpioPinDigitalOutput tempLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "Temperature LED");

				//humidity led logic
				System.out.println("Analyzing humidity data:");
				boolean maxH = (humidity > maxHumidity);
				boolean minH = (humidity < minHumidity);
				boolean maxT = (temp > maxTemp);
				boolean minT = (temp < minTemp);

				System.out.println(humidity + " - " +  maxHumidity);
				System.out.println(humidity  + " - " +   minHumidity);
				System.out.println(temp  + " - " +   maxTemp);
				System.out.println(temp  + " - " +   minTemp);

				if ((maxH || minH) && (maxT || minT)) {
					humidityLed.blink(1000);
					tempLed.blink(1000);
					System.out.println("Piscando");
				}
				else if (maxH) {
					humidityLed.setState(true);
					tempLed.setState(false);
					System.out.println("Umidade ON");
				}
				else if (maxT) {
					humidityLed.setState(false);
					tempLed.setState(true);
					System.out.println("Temperatura ON");
				}
				else {
					System.out.println("Apagados");
					humidityLed.setState(false);
					tempLed.setState(false);
				}

				//unprovision pins for next iteration
				gpio.unprovisionPin(humidityLed);
				gpio.unprovisionPin(tempLed);
				Thread.sleep(5000);
				//gpio.shutdown();
			}

		}
	}
}
