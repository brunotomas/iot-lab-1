package cos.ufrj.br.labiot032018.rt.client.model;

public class HumiditySensor extends Sensor {
	
	public HumiditySensor(double value, double minValue, double maxValue) {
		super(value, "%", minValue, maxValue);
	}
	

}
