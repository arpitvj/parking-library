package com.gojek.parking.service;

import java.util.List;

import com.gojek.parking.model.data.ParkingSlot;

public interface ParkingService {

	void createSlot(int noOfSlots);
	
	String generateTicket(String regNumber, String color);
	
	void vacateSlot(int slotNumber);
	
	List<String> registrationNumbers(String color);
	
	int checkCarSlot(String regNumber);
	
	List<Integer> trackCarWithColor(String color);
	
	List<ParkingSlot> status();
	
}
