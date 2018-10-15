package resources;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class HumidityLedResource extends CoapResource {
	public HumidityLedResource(String name) {
		super(name);
	}
	
	@Override
	public void handleGET(CoapExchange exchange) {
	exchange.respond(ResponseCode.CONTENT,
	"Status do led de umidade", //replace string by humidity led status (ON/OFF/BLINK) 
	MediaTypeRegistry.TEXT_PLAIN);

	}
}
