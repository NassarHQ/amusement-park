package amusementpark;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static amusementpark.Main.exitProgram;

public class Visitor extends Person {
    private double height;
    private double weight;
    private List<String> purchaseTicketHistory;
    private List<String> purchaseItemHistory;
    private String feedback;
    private boolean hasProvidedFeedback;
    private boolean ticketPurchased;
    private Ride currentQueuedRide; // The ride the visitor is currently queued for

    public Visitor(){
        super();
        this.purchaseTicketHistory = new ArrayList<>(); // Initialize or create a new list;
        this.purchaseItemHistory = new ArrayList<>();
        this.height = 0.00;
        this.weight = 0.00;
        this.hasProvidedFeedback = false;   // mInitialize the flag as false
        this.ticketPurchased = false; // Initialize the flag as false
    }

    public Visitor(String name, int age) {
        super (name, age);
        this.purchaseTicketHistory = new ArrayList<>();
        this.purchaseItemHistory = new ArrayList<>();
        this.height = 0.00;
        this.weight = 0.00;
        this.hasProvidedFeedback = false;   // Initialize the flag as false
        this.ticketPurchased = false; // Initialize the flag as false
    }

    public Visitor(String name, int age, String username, String password) {
        super(name, age, username, password);  // Call the parameterized constructor of Person
        this.purchaseTicketHistory = new ArrayList<>();
        this.purchaseItemHistory = new ArrayList<>();
        this.height = 0.00;
        this.weight = 0.00;
        this.hasProvidedFeedback = false;
        this.ticketPurchased = false;
    }

    public Visitor(String name, int age, double height, double weight, String username, String password) {
        super(name, age, username, password); // Call the parent class constructor
        this.height = height;
        this.weight = weight;
        this.purchaseTicketHistory = new ArrayList<>();
        this.purchaseItemHistory = new ArrayList<>();
        this.hasProvidedFeedback = false; // Initialize the flag as false
        this.ticketPurchased = false; // Initialize the flag as false
    }

    // Getter for visitor's height
    public double getVisitorHeight() {
        return height;
    }

    // Setter for visitor's height
    public void setVisitorHeight(double height) {
        validatePositiveNumber(height, "Height");
        this.height = height;
    }

    // Getter for visitor's weight
    public double getVisitorWeight() {
        return weight;
    }

    // Setter for visitor's weight
    public void setVisitorWeight(double weight) {
        validatePositiveNumber(weight, "Weight");
        this.weight = weight;
    }

    // Getter to retrieve purchase ticket history
    public List<String> getPurchaseTicketHistory() {
        return purchaseTicketHistory;
    }

    //Getter for feedback
    public String getFeedback() {
        return feedback;
    }

    // Getter for hasProvidedFeedback
    public boolean hasProvidedFeedback() {
        return hasProvidedFeedback;     // Return the feedback flag status
    }

    // Setter for ticketPurchased flag
    public void setTicketPurchased(boolean purchased) {
        this.ticketPurchased = purchased; // Set ticket purchase status
    }

    // Getter for ticketPurchased flag
    public boolean getTicketPurchased() {
        return ticketPurchased; // Set ticket purchase status
    }

    public String getVisitorCategory() {
        int age = getAge();
        if (age < 12) {
            return "Child";
        } else if (age >= 65) {
            return "Senior";
        } else {
            return "Adult";
        }
    }

    @Override
    public void personType(){
        System.out.println("Visitor");
    }

    // Method to provide feedback
    public String provideFeedback() {
        System.out.println("Enter your feedback: ");
        Scanner sc = new Scanner(System.in); // Create sc to read user's feedback

        feedback = sc.nextLine();
        exitProgram(feedback.trim());
        this.hasProvidedFeedback = true;    // Set flag to true if visitor has provided feedback

        return feedback;
    }


    // Method to view ticket purchase history
    public void viewPurchaseTicketHistory() {
        if (purchaseTicketHistory.isEmpty()) {
            System.out.println("You haven't purchased any tickets.");
        } else {
            System.out.println("Your purchased ticket ID: " + purchaseTicketHistory);
        }
    }

    // Method to view product purchase history
    public void viewPurchaseItemHistory() {
        if (purchaseItemHistory.isEmpty()) {
            System.out.println("You haven't purchased any items.");
        } else {
            System.out.println("Your purchased items: " + purchaseItemHistory);
        }
    }

    // Method to add purchased ticket ID(s) to purchase history
    public void addTicketToPurchaseHistory(Ticket t) {
        if (!hasPurchased(t.getTicketID())) {
            purchaseTicketHistory.add(t.getTicketID());
        }
    }

    // Method to add purchased store products to purchase history
    public void addItemToPurchaseHistory(String item, int quantity) {
        purchaseItemHistory.add(quantity + "x " + item);
    }


    // Method to check if the visitor has purchased a specific ticket
    public boolean hasPurchased(String ticketID) {
        return purchaseTicketHistory.contains(ticketID); // Return true if ticket ID is in purchase history
    }

    // Helper method to validate positive number
    private void validatePositiveNumber(double number, String field) {
        if (number <= 0) {
            throw new IllegalArgumentException(field + " has to be greater than 0.");
        }
    }

    @Override
    public void viewProfile() {
        System.out.println("Visitor information: ");
        System.out.println("Name: " + getName());
        System.out.println("Age: " + getAge());
        System.out.println("Height: " + height +" cm");
        System.out.println("Weight: " + weight + " kg");
    }

    // Method to check if visitor is already queued for a ride
    public boolean isQueuedForARide() {
        return currentQueuedRide != null;
    }

    // Method to set the current queued ride
    public void setQueuedRide(Ride ride) {
        this.currentQueuedRide = ride;
    }

    // Method to clear the queued ride
    public void clearQueuedRide() {
        this.currentQueuedRide = null;
    }

    // Method to get the current queued ride
    public Ride getCurrentQueuedRide() {
        return currentQueuedRide;
    }

    // Method to view feedback
    public void viewFeedback() {
        if (hasProvidedFeedback) {
            System.out.println(this.getName() + " has provided a feedback: " + feedback);
        } else {
            System.out.println(this.getName() + " hasn't provided any feedbacks.");
        }
    }

}
