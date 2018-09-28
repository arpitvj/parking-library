package com.gojek.parking.starter;

import java.util.Scanner;

public interface RunMode {

	/**
	 * Run method which runs the mode selected 
	 * @param scanner
	 * @param fileName
	 */
	public void run(Scanner scanner, String fileName);
}
