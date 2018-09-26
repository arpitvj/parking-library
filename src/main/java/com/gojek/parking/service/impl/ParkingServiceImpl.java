package com.gojek.parking.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
			
			for(int i=0; i < noOfSlots; i++) {
				
				int totalSlot = lastSlotNumber + 1;
				if(totalSlot > maxSlotsOnEachLevel) {
					
					currentLevel++;
					parkingSlots.add(new ParkingSlot(lastSlotNumber + 1, totalLevels[currentLevel], false));
					lastSlotNumber++;
				} else {
					
					parkingSlots.add(new ParkingSlot(lastSlotNumber + 1, totalLevels[currentLevel], false));
					lastSlotNumber++;
				}
			}
			
			System.out.println("Created a parking lot with " + noOfSlots + " slots");
		} else {
			System.out.println("Cannot allot "+noOfSlots+" slots. No parking space left on any level.");
		}
	}

	public String generateTicket(String regNumber, String color) {
		
		
		return null;
	}

	public void vacateSlot(int slotNumber) {
		
		Optional<ParkingSlot> parkingSlot = parkingSlots.stream().filter(p -> p.getSlotNumber() == slotNumber).findFirst();
		parkingSlot.ifPresent(slot -> {
			slot.setOccupied(false);
			slot.setVehicle(null);	
		});
		
		parkingSlots.removeIf(x -> x.getSlotNumber() == slotNumber);
		parkingSlots.add(parkingSlot.get());
		
		System.out.println("Slot number "+ slotNumber +" is free");
	}

	public List<String> registrationNumbers(String color) {
		
		return null;
	}

	public int checkCarSlot(String regNumber) {
		
		
		return 0;
	}

	public void trackCarWithColor(String color) {
		
		List<String> slots = parkingSlots.stream().filter(p -> (p.isOccupied() == true) && (p.getVehicle().getColor().equals(color)))
		.map(m -> m.getVehicle().getRegistrationNumber())
		.collect(Collectors.toList());
		
		String slotsCommaSeparated = String.join(",", slots);

		System.out.println(slotsCommaSeparated);
	}

	public void status() {
		
		System.out.printf("%10s %30s %20s", "Slot No.", "Registration No", "Colour");
		parkingSlots.stream().filter(p -> p.isOccupied() == true).map(parkingSlot -> {
			
			int slotNumber = parkingSlot.getSlotNumber();
			String registrationNumber = parkingSlot.getVehicle().getRegistrationNumber();
			String colour = parkingSlot.getVehicle().getColor();
			
			System.out.printf("%10o %30s %20s", slotNumber, registrationNumber, colour);
			return parkingSlot;
		});
	}

}
