package amusementpark;

import java.util.Scanner;

public class AdminUI {
    
    // Main menu for Admin
    public static void displayAdminMenu(){
        System.out.println("Admin Menu:");
        System.out.println("1. Manage Rides");
        System.out.println("2. Manage Stores");
        System.out.println("3. Show visitor feedback");
        System.out.println("4. Show employee reports");
        System.out.println("5. Check Park metrics");
        System.out.println("Enter your choice: ");
    }

    // Main function to control the UI
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(true){
            displayAdminMenu();
            int choice = scanner.nextInt();

            switch (choice){
                case 1:
                manageRides(scanner);
                break;

                case 2:
                manageStores(scanner);
                break;

                case 3:
                showFeedback(scanner);
                break;

                case 4:
                showReports(scanner);
                break;

                case 5:
                checkMetrics(scanner);

                default:
                    System.out.println("Invalid choice. Try again.");

            }
        }
    }

    
}
