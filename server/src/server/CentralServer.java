package server;

import org.eclipse.californium.core.CoapServer;

import resources.HumidityLedResource;
import resources.TempHumidityResource;
import resources.TempLedResource;

public class CentralServer extends CoapServer {

	public static void main(String[] args) {
		
		CentralServer centralServer = new CentralServer();
		
		TempHumidityResource tempHumidityResource = new TempHumidityResource("temp-humidity");
		centralServer.add(tempHumidityResource);
		tempHumidityResource.setObservable(true);
		tempHumidityResource.getAttributes().setObservable();
		
		TempLedResource tempLedResource = new TempLedResource("temp-led");
		centralServer.add(tempLedResource);
		tempLedResource.setObservable(true);
		tempLedResource.getAttributes().setObservable();
		
		HumidityLedResource humidityLedResource = new HumidityLedResource("humidity-led");
		centralServer.add(humidityLedResource);
		humidityLedResource.setObservable(true);
		humidityLedResource.getAttributes().setObservable();
		
	}

}
