package server;

import org.eclipse.californium.core.CoapServer;

import resources.HumidityTemperatureResource;


public class CentralServer extends CoapServer {

	public static void main(String[] args) {
		
		CentralServer centralServer = new CentralServer(); // create instance of serve
		
		HumidityTemperatureResource tempHumidityResource = new HumidityTemperatureResource("humidity-temperature"); // create instance of resource
		centralServer.add(tempHumidityResource); // add the resource to serve
		tempHumidityResource.setObservable(true); // enable observing
		tempHumidityResource.getAttributes().setObservable(); // mark observable in the Link-Format
				
		centralServer.start(); // start the server
	}

}
