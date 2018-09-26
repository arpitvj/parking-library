package com.gojek.parking.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.gojek.parking.model.data.ParkingSlot;
import com.gojek.parking.model.data.Vehicle;
import com.gojek.parking.service.ParkingService;

public class ParkingServiceImpl implements ParkingService {

	private List<ParkingSlot> parkingSlots = new ArrayList<ParkingSlot>();
	private int maxSlotsOnEachLevel = 3;
	private int lastSlotNumber = 0;
	private int currentLevel = 0;
	
	public void createSlot(int noOfSlots) {
		
		for(int i=0; i < noOfSlots; i++) {
			
			int newSlotNumber = lastSlotNumber + 1;
			if(newSlotNumber > maxSlotsOnEachLevel) currentLevel++;
			
			parkingSlots.add(new ParkingSlot(lastSlotNumber + 1, currentLevel, false));
			lastSlotNumber++;
		}
		
		System.out.println("Created a parking lot with " + noOfSlots + " slots");
	}

	public void generateTicket(String regNumber, String color) {
		
		Optional<ParkingSlot> emptySlot = parkingSlots.stream().filter(p -> p.isOccupied() == false).findFirst()
		.map(slot -> {
			slot.setVehicle(new Vehicle(regNumber, color));
			slot.setOccupied(true);
			System.out.println("Allocated slot number: "+ slot.getSlotNumber());
			return slot;
		});
			
		if(!emptySlot.isPresent()) {
			System.out.println("Sorry, parking lot is full");
		}		
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

	public void registrationNumbers(String color) {
		
		List<String> slots = parkingSlots.stream().filter(p -> (p.isOccupied() == true) && (p.getVehicle().getColor().equals(color)))
		.map(m -> m.getVehicle().getRegistrationNumber())
		.collect(Collectors.toList());
		
		String slotsCommaSeparated = String.join(", ", slots);

		System.out.println(slotsCommaSeparated);
	}

	public void checkCarSlot(String regNumber) {
		
		Optional<ParkingSlot> parkingSlot = parkingSlots.stream().filter(p -> (p.isOccupied() == true) && (p.getVehicle().getRegistrationNumber().equals(regNumber))).findFirst();
		
		parkingSlot.ifPresent(slot -> {
			System.out.println(slot.getSlotNumber());
		});
		
		if(!parkingSlot.isPresent()) {
			System.out.println("Not found");
		}
	}

	public void trackCarWithColor(String color) {
		
		List<String> slots = parkingSlots.stream().filter(p -> (p.isOccupied() == true) && (p.getVehicle().getColor().equals(color)))
		.map(m -> String.valueOf(m.getSlotNumber()))
		.collect(Collectors.toList());
		
		String slotsCommaSeparated = String.join(", ", slots);

		System.out.println(slotsCommaSeparated);
	}

	public void status() {
		
		System.out.printf("%10s %30s %20s", "Slot No.", "Registration No", "Colour");
		List<ParkingSlot> slots = parkingSlots.stream().filter(p -> (p.isOccupied() == true)).collect(Collectors.toList());
			
		for (ParkingSlot parkingSlot : slots) {
			int slotNumber = parkingSlot.getSlotNumber();
			String registrationNumber = parkingSlot.getVehicle().getRegistrationNumber();
			String colour = parkingSlot.getVehicle().getColor();
			
			System.out.printf("\n%10o %30s %20s", slotNumber, registrationNumber, colour);
		}		
	}
}
