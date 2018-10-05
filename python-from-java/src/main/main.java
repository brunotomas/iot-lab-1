package main;

import java.io.*;
import java.util.Scanner;                                                       
class main {                                                      
public static void main(String a[]) {                                          
	try {                                                                   
		String returnValue = "";                                        
		// We are using python from anaconda                            
		String command = "python ";
		/* any parameters needed including the python script path can be 
                   added in the command as below */
		String [] parameters = new String[2];
		parameters[0] = "C:\\Users\\romulo\\Documents\\iot-lab-1\\python-from-java\\src\\main\\inc_one.py";
		parameters[1] = "3";
		for (int i = 0; i < parameters.length; i++)
			command = command + " " + parameters[i];
		// executing the script
		Process p = Runtime.getRuntime().exec(command);
		Scanner stdin = new Scanner(new BufferedInputStream(p.getInputStream()));
		while (stdin.hasNext())
			returnValue = returnValue + stdin.nextLine();
		stdin.close();
		System.out.println("O incremento em 1 de " + parameters[1] + " é " + returnValue);
	} catch (Exception e) {
		System.out.println(e);
	}
}
}