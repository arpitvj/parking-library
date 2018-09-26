package com.gojek.parking.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.gojek.parking.model.data.Level;
import com.gojek.parking.model.data.ParkingSlot;
import com.gojek.parking.model.data.Vehicle;
import com.gojek.parking.service.impl.ParkingServiceImpl;

public class ParkingServiceTest {

	private ParkingService parkingService;
	
	/**
	 * Test Data
	 */
	static String TEST_REGN_NUMBER_1 = "KA-01-HH-1234";
	static String TEST_REGN_NUMBER_2 = "KA-01-HH-9999";
	static String TEST_REGN_NUMBER_3 = "KA-01-BB-0001";
	static String TEST_REGN_NUMBER_4 = "KA-01-HH-2701";
	
	static enum TestVehicleColor {
		RED,
		GREEN,
		BLUE,
		YELLOW,
		ORANGE;
	}  
	static Vehicle TEST_VEHICLE_1 = new Vehicle(TEST_REGN_NUMBER_1, TestVehicleColor.RED.toString());
	static Vehicle TEST_VEHICLE_2 = new Vehicle(TEST_REGN_NUMBER_2, TestVehicleColor.GREEN.toString());

    @Before
    public void setUp() {
        
    	parkingService = new ParkingServiceImpl();
    }
	
	@Test
	public void testCreateSlot() {
		
		List<ParkingSlot> expectedParkingSlots = new ArrayList<ParkingSlot>();
		expectedParkingSlots.add(new ParkingSlot(1, Level.GROUND, false));
		expectedParkingSlots.add(new ParkingSlot(2, Level.GROUND, false));
		expectedParkingSlots.add(new ParkingSlot(3, Level.GROUND, false));
		expectedParkingSlots.add(new ParkingSlot(4, Level.GROUND, false));
		expectedParkingSlots.add(new ParkingSlot(5, Level.GROUND, false));
		expectedParkingSlots.add(new ParkingSlot(6, Level.GROUND, false));
		
		//assertEquals("Created a parking lot with 6 slots", parkingService.createSlot(6));
		
		List<ParkingSlot> actualSlotList = parkingService.status();
		
		assertEquals(expectedParkingSlots, actualSlotList);
	}
	
	@Test
	public void testGenerateTicket() {
		
		// Add a new parking slot
		List<ParkingSlot> expectedParkingSlots = new ArrayList<ParkingSlot>();
		expectedParkingSlots.add(new ParkingSlot(1, Level.GROUND, false));
		
		parkingService.createSlot(1);
		
		List<ParkingSlot> actualSlotList = parkingService.status();
		
		// Check if that parking slot is successfully added
		assertEquals(expectedParkingSlots, actualSlotList);
		
		// Generate the parking ticket
		String slotNumber = parkingService.generateTicket(TEST_REGN_NUMBER_1, TestVehicleColor.RED.toString());
		
		assertEquals(1, slotNumber);
	}
	
	@Test
	public void testVacateSlot() {
		
		// Add a new parking slot
		List<ParkingSlot> expectedParkingSlots = new ArrayList<ParkingSlot>();
		expectedParkingSlots.add(new ParkingSlot(1, Level.GROUND, false));
		
		parkingService.createSlot(1);
		
		List<ParkingSlot> actualSlotList = parkingService.status();
		
		// Check if that parking slot is successfully added
		assertEquals(expectedParkingSlots, actualSlotList.get(0).getSlotNumber());
				
		parkingService.vacateSlot(1);
		
		List<ParkingSlot> slotStatus = parkingService.status();
		
		assertTrue(slotStatus.isEmpty());
	}
	
	@Test
	public void testRegistrationNumbers() {
		
		// Add a new parking slot
		parkingService.createSlot(3);
		
		parkingService.generateTicket(TEST_REGN_NUMBER_1, TestVehicleColor.RED.toString());
		parkingService.generateTicket(TEST_REGN_NUMBER_2, TestVehicleColor.GREEN.toString());
		parkingService.generateTicket(TEST_REGN_NUMBER_3, TestVehicleColor.RED.toString());
		
		List<String> actualVehicleNumbers = parkingService.registrationNumbers(TestVehicleColor.RED.toString());
		
		List<String> expectedVehicleNumbers = new ArrayList<String>();
		expectedVehicleNumbers.add(TEST_REGN_NUMBER_1);
		expectedVehicleNumbers.add(TEST_REGN_NUMBER_3);
		
		assertEquals(expectedVehicleNumbers, actualVehicleNumbers);
	}
	
	@Test
	public void testCheckCarSlot() {
	
		// Add a new parking slot
		parkingService.createSlot(1);
		
		String expectedSlotNumber = parkingService.generateTicket(TEST_REGN_NUMBER_1, TestVehicleColor.RED.toString());
		
		int actualSlotNumber = parkingService.checkCarSlot(TEST_REGN_NUMBER_1);
		
		assertEquals(expectedSlotNumber, String.valueOf(actualSlotNumber));
	}
	
	@Test
	public void testTrackCarWithColor() {
		
		// Add a new parking slot
		parkingService.createSlot(3);
		
		parkingService.generateTicket(TEST_REGN_NUMBER_1, TestVehicleColor.RED.toString());
		parkingService.generateTicket(TEST_REGN_NUMBER_2, TestVehicleColor.GREEN.toString());
		parkingService.generateTicket(TEST_REGN_NUMBER_3, TestVehicleColor.RED.toString());
		
		List<Integer> actualSlots = parkingService.trackCarWithColor(TestVehicleColor.RED.toString());
		
		List<Integer> expectedSlots = new ArrayList<Integer>();
		expectedSlots.add(1);
		expectedSlots.add(3);
		
		assertEquals(expectedSlots, actualSlots);
	}
	
	@Test
	public void testStatus() {
		
		List<ParkingSlot> expectedParkingSlots = new ArrayList<ParkingSlot>();
		expectedParkingSlots.add(new ParkingSlot(1, Level.GROUND, false, TEST_VEHICLE_1));
		expectedParkingSlots.add(new ParkingSlot(2, Level.GROUND, false, TEST_VEHICLE_2));	
		
		// Add a new parking slot
		parkingService.createSlot(3);
		
		parkingService.generateTicket(TEST_REGN_NUMBER_1, TestVehicleColor.RED.toString());
		parkingService.generateTicket(TEST_REGN_NUMBER_2, TestVehicleColor.GREEN.toString());
		
		List<ParkingSlot> actualParkingSlot = parkingService.status();
		
		assertEquals(expectedParkingSlots, actualParkingSlot);
	}
}
