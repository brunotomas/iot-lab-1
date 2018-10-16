package utils;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;

public final class LedUtils {

	public static String getLedStatus(GpioPinDigitalOutput led) throws InterruptedException {
		PinState read1 = led.getState();
		Thread.sleep(1100);
		PinState read2 = led.getState();
		Thread.sleep(1100);	
		PinState read3 = led.getState();
		
		if ((read1 == read3 && read1 != read2)||(read1==read2 && read1!=read3)) return "BLINK";
		else if (read1 == PinState.HIGH) return "ON";
		else return "OFF";
	}
}
