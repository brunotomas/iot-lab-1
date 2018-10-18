package server;

import org.eclipse.californium.core.CoapServer;

import resources.TempHumidityResource;


public class CentralServer extends CoapServer {

	public static void main(String[] args) {
		
		CentralServer centralServer = new CentralServer();
		
		TempHumidityResource tempHumidityResource = new TempHumidityResource("temp-humidity");
		centralServer.add(tempHumidityResource);
		tempHumidityResource.setObservable(true);
		tempHumidityResource.getAttributes().setObservable();
				
		centralServer.start();
	}

}
