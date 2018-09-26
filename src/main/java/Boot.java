import java.util.Scanner;

import com.gojek.parking.client.ParkingClient;
import com.gojek.parking.util.ApplicationConstants;

public class Boot {

	public static void main(String[] args) {
		
		String one = "";
		if(null != args && args.length > 0) {
			System.out.println(args);
			one = args[0];
		}
		
		System.out.println(one);
		Scanner sc = new Scanner(System.in);
		ParkingClient parkingClient = new ParkingClient();

		String inputLine = "";
		while (!inputLine.equals(ApplicationConstants.EXIT)) {
			inputLine = sc.nextLine();

			String command = "";

			if (inputLine.equals(ApplicationConstants.EXIT)) {
				break;
			} else if (inputLine.equals(ApplicationConstants.EMPTY)) {
				System.out.println("Please enter correct input. \n");
			} else {

				String[] splitValues = inputLine.split(" ");
				command = inputLine.split(" ")[0];
				if (command.equals(ApplicationConstants.CREATE_PARKING_LOT)) {

					int convertedInt = convertRawToInt(splitValues[1]);
					if (convertedInt > 0) {

						parkingClient.createParkingLot(convertedInt);
					} else {

						System.out.println("Incorrect command format. Please try again with correct format.");
					}
				} else if (command.equals(ApplicationConstants.PARK)) {

					parkingClient.generateTicket(splitValues[1], splitValues[2]);
				} else if (command.equals(ApplicationConstants.LEAVE)) {

					int convertedInt = convertRawToInt(splitValues[1]);
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

					parkingClient.checkCarPosition(splitValues[1]);
				} else if (command.equals(ApplicationConstants.SLOT_NUMBERS_FOR_CARS_WITH_COLOR)) {

					parkingClient.trackCarWithColor(splitValues[1]);
				} else {
					System.out.println("Please enter correct input. \n");
				}
			}
		}

		sc.close();
	}

	private static int convertRawToInt(String value) {

		try {
			return Integer.valueOf(value);
		} catch (NumberFormatException e) {
			return -1;
		}
	}
}
