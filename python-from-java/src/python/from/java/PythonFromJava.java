package python.from.java;

import java.io.*;
import java.util.Scanner;                                                       
class PythonFromJava {
	String file;
	String [] params;

	public PythonFromJava(String file, String [] params) {
		super();
		this.file = file;
		this.params = params;
	}

	public String run() {
		String returnValue = ""; 
		try {                                                                                                 
			String command = "python " + file;
			for (int i = 0; i < params.length; i++)
				command = command + " " + params[i];
			Process p = Runtime.getRuntime().exec(command);
			Scanner stdin = new Scanner(new BufferedInputStream(p.getInputStream()));
			while (stdin.hasNext())
				returnValue = returnValue + stdin.nextLine();
			stdin.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return returnValue;
	}
}