package amusementpark.test;

import amusementpark.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDateTime;
import java.util.List;

public class AmusementParkTest {

    // Instances for testing
    private Employee employee;
    private Visitor visitor;
    private Ticket ticket;
    private Ride ride;
    private Park park;
    
    // Setup method to initialize objects before each test
    @Before
    public void setUp() {
        employee = new Employee("John Doe", 30, "E123", "Manager");
        visitor = new Visitor("Jane Doe", 25, 170, 70);
        ticket = new Ticket(50.0, "T123");
        ride = new Ride("Roller Coaster", 1, 20, 60, 150, 200);
        park = new Park();
    }

    // --------- Employee Tests ---------

    @Test
    public void testCalculateEmployeeSalary() {
        // Test the salary calculation for a Manager
        assertEquals(5000.00, employee.calculateSalary(), 0.01);

        // Change role to General Associate and test new salary
        employee.setRole("General Associate");
        assertEquals(2500.00, employee.calculateSalary(), 0.01);
    }

    @Test
    public void testEmployeeShiftIn() {
        // Test that employee goes on shift
        employee.shiftIn();
        assertTrue(employee.getOnShift()); // Verify employee is on shift
    }

    @Test
    public void testEmployeeShiftOut() {
        // Test that employee goes off shift
        employee.shiftIn(); // Employee must be on shift first
        employee.shiftOut();
        assertFalse(employee.getOnShift()); // Verify employee is off shift
    }

    @Test
    public void testEmployeeAddToPark() {
        // Test adding employee to park
        employee.addToPark(park);
        assertTrue(park.getEmployees().contains(employee)); // Verify employee is in the park's employee list
    }

    @Test
    public void testEmployeeCheckRideEligibility() {
        // Test ride eligibility check
        visitor = new Visitor("Visitor", 12, 130, 50);  // Invalid height for the ride
        ride.addRider(visitor);
        assertFalse(employee.checkRideEligibility(ride)); // Expect false due to height restriction
    }

    // --------- Visitor Tests ---------

    @Test
    public void testVisitorAddTicketToPurchaseHistory() {
        // Test adding ticket to visitor's purchase history
        visitor.addTicketToPurchaseHistory(ticket);
        assertTrue(visitor.getPurchaseHistory().contains(ticket)); // Verify ticket is in purchase history
    }

    @Test
    public void testVisitorFeedback() {
        // Test adding feedback for the visitor
        visitor.provideFeedback("Great ride!");
        assertEquals("Great ride!", visitor.viewFeedback().get(0)); // Verify feedback was added
    }

    // --------- Ticket Tests ---------

    @Test
    public void testTicketPrice() {
        // Test the ticket price
        assertEquals(50.0, ticket.getTicketPrice(), 0.01); // Verify ticket price
    }

    @Test
    public void testTicketDiscount() {
        // Test applying a discount to the ticket
        ticket.applyDiscount(10.0); // Apply a 10% discount
        assertEquals(45.0, ticket.getTicketPrice(), 0.01); // Verify new ticket price
    }

    @Test
    public void testTicketRefundStatus() {
        // Test setting the refund status of the ticket
        ticket.setRefundStatus(true);
        assertTrue(ticket.isRefunded()); // Verify ticket is marked as refunded
    }

    // --------- Ride Tests ---------

    @Test
    public void testRideAddRider() {
        // Test adding a visitor to the ride
        ride.addRider(visitor);
        assertTrue(ride.getOnRide().contains(visitor)); // Verify visitor is on the ride
    }

    @Test
    public void testRideStartStop() {
        // Test starting and stopping the ride
        ride.start();
        assertTrue(ride.isRideOperational()); // Verify ride is operational
        
        ride.stop();
        assertFalse(ride.isRideOperational()); // Verify ride is not operational after stop
    }

    @Test
    public void testRideCapacity() {
        // Test that the ride can't exceed its capacity
        for (int i = 0; i < ride.getCapacity(); i++) {
            ride.addRider(new Visitor("Visitor" + i, 20, 160, 70)); // Add visitors
        }
        assertEquals(ride.getCapacity(), ride.getOnRide().size()); // Verify capacity is met

        Visitor extraVisitor = new Visitor("Extra Visitor", 25, 170, 75);
        ride.addRider(extraVisitor);  // Try to add another visitor
        assertFalse(ride.getOnRide().contains(extraVisitor)); // Verify extra visitor was not added
    }
}
