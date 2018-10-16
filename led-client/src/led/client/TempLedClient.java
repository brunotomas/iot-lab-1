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

public class TempLedClient extends CoapClient {

	public static void main(String[] args) {
		CoapClient client = new CoapClient("coap://127.0.0.1:5683/temp-humidity"); 
		
		while (true) {
			Request request = new Request(Code.GET);
			//Synchronously send the GET message (blocking call)
			CoapResponse coapResp = client.advanced(request);
			//The "CoapResponse" message contains the response.
			//System.out.println(Utils.prettyPrint(coapResp));
			double temp = Double.parseDouble(coapResp.getResponseText().split(";")[0]);
			double humidity = Double.parseDouble(coapResp.getResponseText().split(";")[1]);
			
			//Do something with the response
			//Logic to turn ON OFF BLINK temp LED
			final GpioController gpio = GpioFactory.getInstance();;
			final GpioPinDigitalOutput tempLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "Temperature LED");
			
			if (temp > BoundData.maxTemp || temp < BoundData.minTemp) {
				if (humidity > BoundData.maxHumidity || humidity < BoundData.minHumidity) {
					tempLed.blink(1000);
				}
				else {
					tempLed.setState(PinState.HIGH);
				}
			}
			else {
				tempLed.setState(PinState.LOW);
			}
		}
	}

}
