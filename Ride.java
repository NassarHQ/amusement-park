package CS151_07_amusement_park_2024;

import java.util.LinkedList;
import java.util.Queue;

class Ride implements ParkInteractables {

    // Declaring variables needed for rides
    private String rideName;
    private String rideID;
    private int rideCapacity;
    private int rideDuration;
    private int rideMinHeight;
    private int rideMaxWeight;
    private boolean isOperational; // Track if the ride is currently running
    private boolean hasStarted; // Track if the ride has started
    private Queue<Visitor> rideVisitorQueue; // Queue to manage visitors in line
    private List<Visitor> onRide; // To keep track of visitors currently on the ride
    

    // Parametarized Constructor for Ride class
    public Ride(String rideName, String rideID, int rideCapacity,
        int rideDuration, int rideMinHeight, int rideMaxWeight) {

            this.rideName = rideName;
            this.rideID = rideID;
            this.rideCapacity = rideCapacity;
            this.rideDuration = rideDuration;
            this.rideMinHeight = rideMinHeight;
            this.rideMaxWeight = rideMaxWeight;
            this.isOperational = false;
            this.hasStarted = false;
            this.rideVisitorQueue = new LinkedList<>();
            this.onRide = new LinkedList<>();
    }

    // Getters and Setters for rideName
    public String getRideName() {
        return rideName;
    }

    public void setRideName(String rideName) {
        this.rideName = rideName;
    }

    // Getters and Setters for rideID
    public String getRideID() {
        return rideID;
    }

    public void setRideID(String rideID) {
        this.rideID = rideID;
    }

    // Getters and Setters for rideCapacity
    public int getRideCapacity() {
        return rideCapacity;
    }

    public void setRideCapacity(int rideCapacity) {
        this.rideCapacity = rideCapacity;
    }

    // Getters and Setters for rideDuration
    public int getRideDuration() {
        return rideDuration;
    }

    public void setRideDuration(int rideDuration) {
        this.rideDuration = rideDuration;
    }

    // Getters and Setters for rideMinHeight
    public int getRideMinHeight() {
        return rideMinHeight;
    }

    public void setRideMinHeight(int rideMinHeight) {
        this.rideMinHeight = rideMinHeight;
    }

    // Getters and Setters for rideMaxWeight
    public int getRideMaxWeight() {
        return rideMaxWeight;
    }

    public void setRideMaxWeight(int rideMaxWeight) {
        this.rideMaxWeight = rideMaxWeight;
    }

    // Getter for rideVisitorQueue
    public Queue<Visitor> getRideVisitorQueue() {
        return rideVisitorQueue;
    }

    public void setRideVisitorQueue(Queue<Visitor> rideVisitorQueue) {
        this.rideVisitorQueue = rideVisitorQueue;
    }

    // Getter for onRide
    public Queue<Visitor> getOnRide() {
        return onRide;
    }
    
    // Method to load passengers onto the ride from the queue
    public void loadPassengers() {
        if (isRunning) {
            // if ride is running. Give an error cannot load passengers
            System.out.println("Ride is currently running. Cannot load passnegers");
            return;
        }
        while(!visitorQueue.isEmpty() && onRide.size() < rideCapacity){
            Visitor visitor = visitorQueue.poll();
            if(visitor.getHeight() >= minHeight && visitor.getWeight() <= maxWeight){
                onRide.offer(visitor);
                System.out.println(visitor.getName() + " has boarded the ride.");
            } else {
                System.out.println(visitor.getName() + " doesn't meet the ride requirements");
            }
        }
        // Automatically start the ride if passengers are loaded
        if (onRide.size() > 0) {
            startUse();
        } else {
            System.out.println("No suitable passengers to load");
        }
    }

    // Method to unload passengers of a ride
    public void unloadPassengers() {
        if (!isRunning) {
            System.out.println("Ride is not Running. No passengers to Unload");
            return;
        }
        while (!onRide.isEmpty()){
            Visitor visitor = onRide.poll();
            System.out.println(visitor.getName() + " has finished the ride and is leaving");
        }
        stopUse(); //After unloading, stop the ride
    }

    // Method to count visitors in line
    public int countVisitorsInLine() {
        return visitorQueue.size();
    }

    // Method to check if the ride is available
    public boolean isAvailable() {
        return !isRunning && onRide.size() < rideCapacity;
    }

    // Interface to start ride
    @Override
    public void startUse() {
        if (isRunning) {
            System.out.println("Ride is already running.");
            return;
        }
        System.out.println("Ride is starting.");
        isRunning = true;
    }

    // Interface to stop ride
    @Override
    public void stopUse() {
        if (!isRunning) {
            System.out.println("Ride is already stopped.");
            return;
        }
        System.out.println("Ride is stopping.");
        isRunning = false;
    }
}