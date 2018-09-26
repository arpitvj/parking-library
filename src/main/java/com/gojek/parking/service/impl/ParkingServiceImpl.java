package com.gojek.parking.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.gojek.parking.model.data.Level;
import com.gojek.parking.model.data.ParkingSlot;
import com.gojek.parking.service.ParkingService;

public class ParkingServiceImpl implements ParkingService {

	private List<ParkingSlot> parkingSlots = new ArrayList<ParkingSlot>();
	private int maxSlotsOnEachLevel = 20;
	private int lastSlotNumber = 0;
	private int currentLevel = 0;
	private Level[] totalLevels = Level.values();
	
	public void createSlot(int noOfSlots) {
		//Created a parking lot with 6 slots
		
		int remainingSlots = (maxSlotsOnEachLevel * totalLevels.length) - noOfSlots;
		
		if(remainingSlots >= 0) {
			System.out.println("Created a parking lot with "+ noOfSlots +" slots");
			
			for(int i=0; i < noOfSlots; i++) {
				
				int totalSlot = lastSlotNumber + 1;
				if(totalSlot > maxSlotsOnEachLevel) {
					
					currentLevel++;
					parkingSlots.add(new ParkingSlot(lastSlotNumber + 1, totalLevels[currentLevel], false));
					lastSlotNumber++;
					System.out.println("Allocated slot number: " + lastSlotNumber);
				} else {
					
					parkingSlots.add(new ParkingSlot(lastSlotNumber + 1, totalLevels[currentLevel], false));
					lastSlotNumber++;
					System.out.println("Allocated slot number: " + lastSlotNumber);
				}
			}
		} else {
			System.out.println("Cannot allot "+noOfSlots+" slots. Not enough space.");
		}

	}

	public String generateTicket(String regNumber, String color) {
		// TODO Auto-generated method stub
		return null;
	}

	public void vacateSlot(int slotNumber) {
		// TODO Auto-generated method stub
		
	}

	public List<String> registrationNumbers(String color) {
		// TODO Auto-generated method stub
		return null;
	}

	public int checkCarSlot(String regNumber) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Integer> trackCarWithColor(String color) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ParkingSlot> status() {
		// TODO Auto-generated method stub
		return null;
	}

}
