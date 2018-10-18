package resources;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;
import com.pi4j.io.gpio.*;
import utils.LedUtils;

public class HumidityLedResource extends CoapResource {
	final GpioController gpio;
	final GpioPinDigitalOutput humidityLed;

	public HumidityLedResource(String name) {
		super(name);
		gpio = GpioFactory.getInstance();
		//remember to change GPIO pin properly
		humidityLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "Humidity LED");
	}

	@Override
	public void handleGET(CoapExchange exchange) {
		try {
			exchange.respond(ResponseCode.CONTENT,
					LedUtils.getLedStatus(humidityLed),
					MediaTypeRegistry.TEXT_PLAIN);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
