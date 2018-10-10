package python.from.java;

public class Main {

	public static void main(String[] args) {
		String [] params = new String[1];
		params[0] = "25";
		String file = "C:\\Users\\romulo\\Documents\\iot-lab-1\\python-from-java\\src\\python\\from\\java\\test.py";
		System.out.println(new PythonFromJava(file, params).run());
	}
}
