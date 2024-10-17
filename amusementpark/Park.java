package amusementpark;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;
import static amusementpark.Main.exitProgram;

public class Park {

    // Instance variables holding park's name and location
    private String parkName; // Name of the Park
    private String parkLocation; // Location of the Park
    private double dailyRevenueGoal;
    private int dailyVisitorGoal;

    // Variables to store daily metrics
    private double totalRevenue;   // Stores total revenue
    private int totalVisitors;     // Stores total number of visitors
    private int totalTicketsSold;  // Stores total number of tickets sold

    // HashSets to store visitors and employees.
    // ArrayLists to store sections, rides, and tickets
    private Set<Visitor> visitors;
    private Set<Employee> employees;
    private ArrayList<ParkStore> stores;
    private ArrayList<Ride> rides;
    private Set<Ticket> soldTickets;
    private ArrayList<String> reportedIssues;

    // Constructor for the Park class
    public Park() {
        // Initialize the collections
        this.parkName = "Ride & Seek";
        this.parkLocation = "666 Roller Coaster Avenue";
        this.dailyRevenueGoal = 5000.00;
        this.dailyVisitorGoal = 100;
        this.visitors = new HashSet<>();
        this.employees = new HashSet<>();
        this.stores = new ArrayList<>();
        this.rides = new ArrayList<>();
        this.soldTickets = new HashSet<>();
        this.reportedIssues = new ArrayList<>();
    }

    // Constructors for Park.java
    public Park(String parkName, String parkLocation, double dailyRevenueGoal, int dailyVisitorGoal){
        this.parkName = parkName;
        this.parkLocation = parkLocation;
        this.dailyRevenueGoal = dailyRevenueGoal;
        this.dailyVisitorGoal = dailyVisitorGoal;
        this.visitors = new HashSet<>();
        this.employees = new HashSet<>();
        this.stores = new ArrayList<>();
        this.rides = new ArrayList<>();
        this.soldTickets = new HashSet<>();
        this.reportedIssues = new ArrayList<>();

    }

    // Method to add a Person to the park
    public void addPerson(Person p) {
        // Check if the object of Person is null
        if (p == null) {
            System.out.println("Invalid person. Cannot add null.");
            return; // Exit the method if p is null
        }

        // Use instanceof to determine the type of Person
        if (p instanceof Visitor visitor) {
            // Check if the visitor is already in the park
            if (visitors.contains(visitor)) {
                System.out.println(visitor.toString() + " is already in the park.");
                return; // Exit the method if the visitor is already present
            } else {
                visitors.add(visitor); // Add the visitor to the park's list
                visitor.addedToPark(this); // Call the Visitor's method to add to the park
            }
        } else if (p instanceof Employee employee) {
            // Check if the employee is already in the park
            if (employees.contains(employee)) {
                System.out.println(employee.toString() + " is already in the park.");
                return; // Exit the method if the employee is already present
            } else {
                employees.add(employee); // Add the employee to the park's list
                employee.addedToPark(this); // Call the Employee's method to add to the park
            }
        } else {
            // Handle other types of Person if necessary
            System.out.println(p.getName() + " is not a valid type to add to the park.");
        }
    }



    // Method to remove a Person from the park
    public void removePerson(Person p) {
        // Check if the object of Person is null
        if (p == null) {
            // Print an error message if object p is null
            System.out.println("Invalid person. Cannot remove null.");
            return;
        }

        // Use instanceof to determine the type of Person
        if (p instanceof Visitor visitor) {
            // Call the Visitor's method to remove from the park
            visitor.removedFromPark(this);
        } else if (p instanceof Employee employee) {
            // Call the Employee's method to remove from the park
            employee.removedFromPark(this); // Assuming you have a similar method in Employee
        } else {
            // Handle other types of Person if necessary
            System.out.println(p.getName() + " is not a valid type to remove from the park.");
        }
    }


    // Utility method to print generic action-related messages for rides or tickets
public void printActionMessage(String entityType, String entityNameOrID, boolean success, String additionalMessage) {
    String status = success ? "successfully" : "unsuccessfully";
    System.out.println("\n================================");
    System.out.printf(" %s %s: %s\n", entityType, additionalMessage, entityNameOrID);
    System.out.println("--------------------------------");
    System.out.printf(" Status: %s %s\n", status, additionalMessage);
    System.out.println("================================\n");
}


private <T> boolean manageEntity(T entity, ArrayList<T> list, String entityName, boolean add) {
    if (entity == null) {
        System.out.println("Invalid " + entityName + ". Cannot perform operations.");
        return false;
    }

    String action = add ? "added" : "removed";

    if (add) {
        if (list.contains(entity)) {
            System.out.printf("The %s is already in the park.\n", entityName);
            return false;
        }
        list.add(entity);
        System.out.printf("The %s was successfully %s in the park.\n", entityName, action);
        return true;
    } else {
        if (!list.contains(entity)) {
            System.out.printf("The %s is not found in the park.\n", entityName);
            return false;
        }
        list.remove(entity);
        System.out.printf("The %s was successfully %s from the park.\n", entityName, action);
        return true;
    }
}

// Manage rides using the generic method
private boolean manageRide(Ride r, boolean add) {
    return manageEntity(r, rides, "ride", add);
}

// Manage stores using the generic method
private boolean manageStore(ParkStore s, boolean add) {
    return manageEntity(s, stores, "store", add);
}

// Public method to add a ride
public boolean addRide(Ride r) {
    return manageRide(r, true);  // Call manageRide with true to add
}

// Public method to remove a ride
public boolean removeRide(Ride r) {
    return manageRide(r, false);  // Call manageRide with false to remove
}

// Public method to add a store
public boolean addStore(ParkStore s) {
    return manageStore(s, true);  // Call manageStore with true to add
}

// Public method to remove a store
public boolean removeStore(ParkStore s) {
    return manageStore(s, false);  // Call manageStore with false to remove
}


    // Calculate park metrics (total revenue, visitors, and tickets sold)
    public void calculateParkMetric() {
        totalVisitors = visitors.size(); // Get the count of total visitors
        totalTicketsSold = soldTickets.size(); // Get the count of total sold tickets

        // Calculate total revenue from sold tickets
        totalRevenue = soldTickets.stream().mapToDouble(Ticket::getTicketPrice).sum();

        // Check if the revenue goal is met
        if (totalRevenue >= dailyRevenueGoal) {
            System.out.println("GOOD JOB TEAM! WE MET OUR GOAL");
        } else {
            System.out.println("Almost there. We need $" + (dailyRevenueGoal - totalRevenue) + " more to reach our goal.");
        }

        // Check if the visitor goal has been met
        if (totalVisitors >= dailyVisitorGoal) {
            System.out.println("Goal Met: " + totalVisitors + " visitors have entered the park today!");
        } else {
            System.out.println("Goal Not Met: " + (dailyVisitorGoal - totalVisitors) + " more visitors needed to meet the goal.");
        }
    }

    // Display park metrics (calls calculateParkMetric and prints results)
    public void displayParkMetric() {
        calculateParkMetric(); // Calculate metrics before displaying
        // Display the metrics for the day
        System.out.println("Park Metrics:");
        System.out.println("Total visitors Today: " + totalVisitors);
        System.out.println("Total revenue Today: $" + totalRevenue);
        System.out.println("Tickets Sold Today: " + totalTicketsSold);
    }


    // Simplified ticket selling method
    public boolean sellTicket(Visitor v) {
        double basePrice = 100.00;

        // Generate a new ticket
        Ticket newTicket = Ticket.generateTicket(basePrice);
        double finalPrice = newTicket.applyDiscount(v);

        System.out.printf("Your ticket price after discount: $%.2f\n", finalPrice);

        System.out.println("Would you like to proceed with the purchase? (yes/no)");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();

        exitProgram(response);

        if (response.equalsIgnoreCase("yes")) {
            newTicket.setTicketPrice(finalPrice);
            soldTickets.add(newTicket);
            v.addTicketToPurchaseHistory(newTicket);
            totalRevenue += finalPrice;
            totalTicketsSold++;
            newTicket.displayTicketReceipt(v);
            return true;
        } else {
            System.out.println("Purchase cancelled.");
        }
        return false;
    }



  // Method to display all feedbacks from visitors
    public void displayAllFeedbacks() {
        System.out.println("Visitor Feedback:");
        if (visitors.isEmpty()) {
            System.out.println("No visitors have provided feedback."); // Message if no visitors
        } else {
            for (Visitor visitor : visitors) {
                visitor.provideFeedback(); // Call visitor's method to display their feedback
            }
        }
    }

  public void addIssue(String issue){
        reportedIssues.add(issue);
        System.out.println("Issue reported: " + issue);
    }

    public void viewReportedIssues(){
        System.out.println("Reported Issues:");
        if (reportedIssues.isEmpty()) System.out.println("None at the moment.");
        else {
            for (String str : reportedIssues){
                System.out.println("- " + str);
            }
        }
    }

    // Method to get list of rides from the park
    public ArrayList<Ride> getRidesList(){
        return this.rides;
    }

    // Method to display all rides from the park
    public void displayAllRides() {
        for (Ride ride : rides) {
            System.out.println(ride.getRideName());
        }
    }

    // Method to get list of stores from the park
    public ArrayList<ParkStore> getStoresList(){
        return this.stores;
    }

    // Method to display all stores from the park
    public void displayAllStores() {
        for (ParkStore store : stores) {
            System.out.println(store.getParkStoreName());
        }
    }

    // Getter for visitors (returns an unmodifiable set to prevent outside modification)
    public Set<Visitor> getVisitors() {
        return visitors;
    }

    // Getter for employees (returns an unmodifiable set to prevent outside modification)
    public Set<Employee> getEmployees() {
        return employees;
    }

    // Getter for total revenue
    public double getTotalRevenue() {
        return totalRevenue;
    }
}