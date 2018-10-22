package cos.ufrj.br.labiot032018.rt.client.controller;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;

import cos.ufrj.br.labiot032018.rt.client.model.HumiditySensor;
import cos.ufrj.br.labiot032018.rt.client.model.TemperatureSensor;
import cos.ufrj.br.labiot032018.rt.client.utils.LedStateConstants;
import cos.ufrj.br.labiot032018.rt.client.view.View;

public class Controller {
	View view;
	HumiditySensor humiditySensor;
	TemperatureSensor temperatureSensor;
	
	public Controller(View view, HumiditySensor humiditySensor, TemperatureSensor temperatureSensor) {
		this.view = view;
		this.temperatureSensor = temperatureSensor;
		this.humiditySensor = humiditySensor;
		initView();
	}

	private void initView() {
		view.getCurrentHumidityTextField().setText(humiditySensor.getValue() + "");
		view.getCurrentTemperatureTextField().setText(humiditySensor.getMinValue() + "");
		view.getMinHumidityTextField().setText(humiditySensor.getMinValue() + "");
		view.getMaxHumidityTextField().setText(humiditySensor.getMaxValue() + "");
		view.getMinTemperatureTextField().setText(temperatureSensor.getMinValue() + "");
		view.getMaxTemperatureTextField().setText(temperatureSensor.getMaxValue() + "");
	}
	
	public void initController() {	
		observe(view.getServerIp());
		view.getOkHumidityButton().addActionListener(e -> sendHumidityRange(view.getServerIp()));
		view.getOkTemperatureButton().addActionListener(e -> sendTemperatureRange(view.getServerIp()));
	}
	
	public void observe(String serverIp) {
		CoapClient clientTempHumidity = new CoapClient("coap://" + serverIp + ":5683/humidity-temperature");
		clientTempHumidity.observe(
				new CoapHandler() {
					@Override public void onLoad(CoapResponse response) {
						String content = response.getResponseText();
						view.getCurrentHumidityTextField().setText(content.split(";")[0]);
						view.getCurrentTemperatureTextField().setText(content.split(";")[1]);
						
						boolean isBlinking = Boolean.parseBoolean(content.split(";")[2]);
						boolean isOnLedHumidity = Boolean.parseBoolean(content.split(";")[3]);
						boolean isOnLedTemperature = Boolean.parseBoolean(content.split(";")[4]);
						
						if(isBlinking) {
							view.getLedStateTemperatureLabel().setText(LedStateConstants.BLINKING);
							view.getLedStateHumidityLabel().setText(LedStateConstants.BLINKING);
						}else {
							if(isOnLedHumidity) {
								view.getLedStateHumidityLabel().setText(LedStateConstants.ON);
							}else {
								view.getLedStateHumidityLabel().setText(LedStateConstants.OFF);
							}
							if(isOnLedTemperature) {
								view.getLedStateTemperatureLabel().setText(LedStateConstants.ON);
							}else {
								view.getLedStateTemperatureLabel().setText(LedStateConstants.OFF);
							}
						}
						
					}

					@Override public void onError() {
						System.err.println("OBSERVING FAILED (press enter to exit)");
					}
				});
	}
	
	public void sendTemperatureRange(String serverIp) {
		CoapClient clientTempHumidity = new CoapClient("coap://" + serverIp + ":5683/humidity-temperature");
		String payload = "temperature;" + view.getMinTemperatureTextField().getText() + ";" + view.getMaxTemperatureTextField().getText();
		clientTempHumidity.post(payload, MediaTypeRegistry.TEXT_PLAIN);

	}
	
	public void sendHumidityRange(String serverIp) {
		CoapClient clientTempHumidity = new CoapClient("coap://" + serverIp + ":5683/humidity-temperature");
		String payload = "humidity;" + view.getMinHumidityTextField().getText() + ";" + view.getMaxHumidityTextField().getText();
		clientTempHumidity.post(payload, MediaTypeRegistry.TEXT_PLAIN);

	}

}
