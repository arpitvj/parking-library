package com.gojek.parking.service;

import java.util.List;

public interface ParkingService {

	String createSlot(int noOfSlots);
	
	String generateTicket(String regNumber, String color);
	
	void freeSlot(int slotNumber);
	
	List<String> registrationNumbers(String color);
	
	int checkCarSlot(String regNumber);
	
	List<Integer> trackCarWithColor(String color);
	
}
