package com.gojek.parking.client;

import java.util.List;

import com.gojek.parking.service.ParkingService;
import com.gojek.parking.service.impl.ParkingServiceImpl;

/**
 * 
 * @author avijayvargiy
 * Parking client
 */
public class ParkingClient 
{
	
	ParkingService parkingService;
	
	public ParkingClient(){
		parkingService = new ParkingServiceImpl();
	}
	
	/**
	 * Creates parking lots
	 * @param noOfLots
	 * @return
	 */
    public void createParkingLot(int noOfSlots) {
    	
    	parkingService.createSlot(noOfSlots);
    }
    
    /**
     * Generated parking ticket for the vehicle
     * @param regNumber
     * @param color
     * @return
     */
    public String generateTicket(String regNumber, String color) {
    	
    	return parkingService.generateTicket(regNumber, color);
    }
    
    /**
     * Marks the parking slot empty
     * @param slotNumber
     */
    public void vacateSlot(int slotNumber) {
    	
    	parkingService.vacateSlot(slotNumber);
    }
    
    /**
     * Returns the list of vehicle registration numbers with the color
     * @param color
     * @return
     */
    public List<String> registrationNumbers(String color) {
    	
    	return parkingService.registrationNumbers(color);
    }
    
    /**
     * Returns the parking slot of the registration number of car
     * @param regNumber
     * @return
     */
    public void checkCarPosition(String regNumber) {
    	
    	parkingService.checkCarSlot(regNumber);
    }
    
    /**
     * 
     * @param color
     * @return
     */
    public void trackCarWithColor(String color) {
    
    	parkingService.trackCarWithColor(color);
    }
    
    public void status() {
        
    	parkingService.status();
    }
}
