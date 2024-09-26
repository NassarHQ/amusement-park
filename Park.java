// Imports
import java.util.ArrayList;

// Class definition for park (Main Structure)
public class Park {

    // Instance variables holding park's name and location
    private String parkName; // Name of the Park
    private String parkLocation; // Location of the Park

    // ArrayLists to store all visitors in the park
    private ArrayList<Visitor> visitors;

    // ArrayLists to store all employees in the park
    private ArrayList<Employee> employees;

    // ArrayList to store all park sections in the park
    private ArrayList<Section> sections;

    // ArrayLists to store all rides in the park
    private ArrayList<Ride> rides;

    // ArrayLists to store all tickets issued in the park
    private ArrayList<Ticket> tickets;

    // Constructor for the Park class
    public Park(String name, String location) {

        this.parkName = parkName; // Sets the park's name
        this.parkLocation = parkLocation; // Sets the park's location

        // Initialize the ArrayLists
        this.visitors = new ArrayList<>();
        this.employees = new ArrayList<>();
        this.sections = new ArrayList<>();
        this.rides = new ArrayList<>();
        this.tickets = new ArrayList<>();
    }

    // Method to add a visitior
    public void addVisitor(Visitor v) {
        // Check if the visitor object is not null and is not in the list
        if (v != null && !visitors.contains(v)) {
            visitors.add(v);

            // Print a message indicating the success of the visitor adding to the list
            System.out.println("Visitor " + v.getName() + " has been added to the park.");
        } else {
            // Print a message indicating the failure of the visitor adding to the list
            System.out.println("Visitor already in the park or invalid.");
        }
    }

    // Method to remove a visitor
    public void removeVisitor(Visitor v) {
        // Check if the visitor object is not null and is in the list
        if (v != null && visitors.contains(v)) {
            // If it passes the conditions, vistor is removed from the list
            visitors.remove(v);
            // Print a message indicating the success of the visitor removal from the list
            System.out.println("Visitor " + v.getName() + " has been removed from the park");

        } else {
            // Print a message indicating the failure of the visitor removal from the list
            System.out.println("Visitors was not in the park or invalid.");
        }
    }

    // Method to assign an employee for a section
    public void assignEmployee(Employee e, Section s) {

    }

    // Method to operate rides
    public void operateRide() {

    }

    // Method to manage tickets in the park
    public void manageTickets(Ticket t) {

    }
}