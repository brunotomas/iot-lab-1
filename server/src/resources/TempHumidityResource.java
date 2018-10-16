package resources;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;

import python.from.java.*;

public class TempHumidityResource extends CoapResource {	
	String pythonFilePath, params[];
	PythonFromJava pythonCode;
	public TempHumidityResource(String name) {
		super(name);
		pythonFilePath = "/server/src/server/SensorReader.py";
		params = new String[1];
		params[0] = ""; //use as debug
		pythonCode = new PythonFromJava(pythonFilePath, params);
	}
	
	
	@Override
	public void handleGET(CoapExchange exchange){
	exchange.respond(ResponseCode.CONTENT,
	this.pythonCode.run(), //replace string by data reading from sensor 
	MediaTypeRegistry.TEXT_PLAIN);

	}
}
