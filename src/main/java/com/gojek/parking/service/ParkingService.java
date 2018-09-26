package com.gojek.parking.service;

import java.util.List;

import com.gojek.parking.model.data.ParkingSlot;

public interface ParkingService {

	void createSlot(int noOfSlots);
	
	String generateTicket(String regNumber, String color);
	
	void vacateSlot(int slotNumber);
	
	void registrationNumbers(String color);
	
	void checkCarSlot(String regNumber);
	
	void trackCarWithColor(String color);
	
	void status();
	
}
