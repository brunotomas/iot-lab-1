package resources;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

import utils.LedUtils;

public class TempLedResource extends CoapResource {
	final GpioController gpio;
	final GpioPinDigitalOutput tempLed;
	
	public TempLedResource(String name) {
		super(name);
		gpio = GpioFactory.getInstance();
		//remember to change GPIO pin properly
		tempLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "TemperatureLED");
	}
	
	@Override
	public void handleGET(CoapExchange exchange) {
	try {
		exchange.respond(ResponseCode.CONTENT,
		LedUtils.getLedStatus(tempLed),
		MediaTypeRegistry.TEXT_PLAIN);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}

	}
}
