package resources;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class TempHumidityResource extends CoapResource {
	public TempHumidityResource(String name) {
		super(name);
	}
	
	@Override
	public void handleGET(CoapExchange exchange) {
	exchange.respond(ResponseCode.CONTENT,
	"Substituir por dados de temperatura e umidade lidos do sensor", //replace string by data reading from sensor 
	MediaTypeRegistry.TEXT_PLAIN);

	}
}
