package com.gojek.parking.starter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.gojek.parking.client.ParkingClient;
import com.gojek.parking.util.ApplicationConstants;

/**
 * Runs the application in File mode
 * @author avijayvargiy
 *
 */
public class FileMode implements RunMode {
	
	public void run(Scanner scanner, String fileName) {
		
		// For File Mode
		File file = new File(fileName);
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			scanner.close();
		}

		ParkingClient parkingClient = new ParkingClient();
		
		while (scanner.hasNextLine()) {
			String inputLine = scanner.nextLine();
			String command = "";
			
			if (inputLine.equals(ApplicationConstants.EXIT)) {
				break;
			} else if (inputLine.equals(ApplicationConstants.EMPTY)) {
				System.out.println("Please enter correct input. \n");
			} else {

				String[] splitValues = inputLine.split(" ");
				command = inputLine.split(" ")[0];
				if (command.equals(ApplicationConstants.CREATE_PARKING_LOT)) {

					int convertedInt = ApplicationConstants.convertRawToInt(splitValues[1]);
					if (convertedInt > 0) {

						parkingClient.createParkingLot(convertedInt);
					} else {

						System.out.println("Incorrect command format. Please try again with correct format.");
					}
				} else if (command.equals(ApplicationConstants.PARK)) {

					parkingClient.generateTicket(splitValues[1], splitValues[2]);
				} else if (command.equals(ApplicationConstants.LEAVE)) {

					int convertedInt = ApplicationConstants.convertRawToInt(splitValues[1]);
					if (convertedInt > 0) {
						parkingClient.vacateSlot(convertedInt);
					} else {
						System.out.println("Incorrect command format. Please try again with correct format.");
					}
				} else if (command.equals(ApplicationConstants.STATUS)) {

					parkingClient.status();
				} else if (command.equals(ApplicationConstants.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOR)) {

					parkingClient.registrationNumbers(splitValues[1]);
				} else if (command.equals(ApplicationConstants.SLOT_NUMBER_FOR_REGISTRATION_NUMBER)) {

					parkingClient.checkVehiclePosition(splitValues[1]);
				} else if (command.equals(ApplicationConstants.SLOT_NUMBERS_FOR_CARS_WITH_COLOR)) {

					parkingClient.findVehicleWithColor(splitValues[1]);
				} else {
					
					System.out.println("Please enter correct input. \n");
				}
			}
		}
	}
}
