package cos.ufrj.br.labiot032018.rt.client.main;

import cos.ufrj.br.labiot032018.rt.client.controller.Controller;
import cos.ufrj.br.labiot032018.rt.client.model.HumiditySensor;
import cos.ufrj.br.labiot032018.rt.client.model.TemperatureSensor;
import cos.ufrj.br.labiot032018.rt.client.view.View;

public class App {
	public static void main(String[] args) {
		HumiditySensor humiditySensor = new HumiditySensor(0, 0, 100);
		TemperatureSensor temperatureSensor = new TemperatureSensor(0, 0, 100);

		View v = new View("Client");
		Controller c = new Controller(v, humiditySensor, temperatureSensor);
		c.initController();
	}
}
