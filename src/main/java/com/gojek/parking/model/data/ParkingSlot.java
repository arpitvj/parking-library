package com.gojek.parking.model.data;

public class ParkingSlot {
	
	int slotNumber;
	
	int level;
	
	boolean isOccupied = false;
	
	Vehicle vehicle;
	
	

	public int getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(int slotNumber) {
		this.slotNumber = slotNumber;
	}

	public boolean isOccupied() {
		return isOccupied;
	}

	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public ParkingSlot(int slotNumber, int level, boolean isOccupied) {
		super();
		this.slotNumber = slotNumber;
		this.level = level;
		this.isOccupied = isOccupied;
	}
	
	public ParkingSlot(int slotNumber, int level, boolean isOccupied, Vehicle vehicle) {
		super();
		this.slotNumber = slotNumber;
		this.level = level;
		this.isOccupied = isOccupied;
		this.vehicle = vehicle;
	}
	
}
