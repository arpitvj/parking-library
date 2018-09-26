import java.util.Scanner;

import com.gojek.parking.client.ParkingClient;
import com.gojek.parking.util.ApplicationConstants;

public class Boot {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ParkingClient parkingClient = new ParkingClient();
		
		String inputLine = "";
		while(!inputLine.equals(ApplicationConstants.EXIT)) {
			inputLine = sc.nextLine();
			
			String command = "";
			
			if(inputLine.equals(ApplicationConstants.EMPTY)) {
				System.out.println("Please enter correct input. \n");
				inputLine = "";
			} else {
				
				command = inputLine.split(" ")[0];
				if(command.equals(ApplicationConstants.CREATE_PARKING_LOT)) {
					
					parkingClient.createParkingLot();
				} else if(command.equals(ApplicationConstants.PARK)) {
					
				} else if(command.equals(ApplicationConstants.LEAVE)) {
					
				} else if(command.equals(ApplicationConstants.STATUS)) {
					
				} else if(command.equals(ApplicationConstants.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOR)) {
					
				} else if(command.equals(ApplicationConstants.SLOT_NUMBERS_FOR_CARS_WITH_COLOR)) {
					
				} else {
					System.out.println("Please enter correct input. \n");
					inputLine = "";
				}
			}
		}
		
		sc.close();
	}
}
