package com.gojek.parking.service;

public interface ParkingService {

	void createSlot(int noOfSlots);
	
	void generateTicket(String regNumber, String color);
	
	void vacateSlot(int slotNumber);
	
	void registrationNumbers(String color);
	
	void checkCarSlot(String regNumber);
	
	void trackCarWithColor(String color);
	
	void status();
	
}
