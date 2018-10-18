package it.unipr.iotlab.iot2018.cf.client;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.Utils;
import org.eclipse.californium.core.coap.CoAP.Code;
import org.eclipse.californium.core.coap.Request;

public class SimpleCoapClient {

	public static void main(String[] args) {
		CoapClient client = new CoapClient("coap://10.0.0.103:5683/temp-humidity");
		Request request = new Request(Code.GET);
		//Synchronously send the GET message (blocking call)
		CoapResponse coapResp = client.advanced(request);
		//The "CoapResponse" message contains the response.
		System.out.println(Double.parseDouble(coapResp.getResponseText().split(";")[0]));
	}

}
