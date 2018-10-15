package resources;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class TempLedResource extends CoapResource {
	public TempLedResource(String name) {
		super(name);
	}
	
	@Override
	public void handleGET(CoapExchange exchange) {
	exchange.respond(ResponseCode.CONTENT,
	"Status do led de temperatura", //replace string by temperature led status (ON/OFF/BLINK) 
	MediaTypeRegistry.TEXT_PLAIN);

	}
}
