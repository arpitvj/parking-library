package com.gojek.parking.starter;
import java.util.Scanner;

/**
 * Application starter. Entry point to decide application run as interactive mode or File mode
 * @author avijayvargiy
 *
 */
public class Boot {

	public static void main(String[] args) {
		RunMode runMode;
		Scanner scanner = new Scanner(System.in);
		
		String fileName = "";
		if (null != args && args.length > 0) {
			fileName = args[0];
		}

		if (fileName.isEmpty())
			runMode = new InteractiveMode();
	   else
			runMode = new FileMode();

		// running the application here
		runMode.run(scanner, fileName);
		
		scanner.close();
	}
}
