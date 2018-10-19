package cos.ufrj.br.labiot032018.rt.client.model;

public class Sensor {
	
	private double value;
	private double minValue;
	private double maxValue;
	@SuppressWarnings("unused")
	private final String unit;
	
	public Sensor(double value, String unit, double minValue, double maxValue) {
		super();
		this.value = value;
		this.unit = unit;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}
	
	public double getValue() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public double getMinValue() {
		return minValue;
	}
	
	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}
	
	public double getMaxValue() {
		return maxValue;
	}
	
	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}
	
	public boolean valueLessThanMinValue() {
		return value < minValue;
	}
	
	public boolean valueGreaterThanMaxValue() {
		return value > maxValue;
	}

}
