package it.unipr.iotlab.iot2018.cf.server;

import org.eclipse.californium.core.CoapServer;
import it.unipr.iotlab.iot2018.cf.server.resources.HelloWorldResource;
import it.unipr.iotlab.iot2018.cf.server.resources.ObservableResource;

public class TutorialServer extends CoapServer {

	public static void main(String[] args) {
		TutorialServer tutorialServer = new TutorialServer();
		HelloWorldResource hello = new HelloWorldResource("hello-world");
		tutorialServer.add(hello);
		ObservableResource obsRes = new ObservableResource("observable-resource");
		obsRes.setObservable(true);
		obsRes.getAttributes().setObservable();
		tutorialServer.add(obsRes);
		tutorialServer.start();
	}

}
