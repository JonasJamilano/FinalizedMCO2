/**
 * @author Jamilano and Silva [S12A] CCPROG3
 * MP Pair Group 8
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class represents the driver program for the Vending Machine Factory application.
 * It allows users to create and interact with vending machines, make purchases, perform maintenance tasks, and more.
 */
public class VendingMachineFactoryDriver {

    /**
     * Displays the purchase history of a vending machine.
     *
     * @param vendingMachine The vending machine whose purchase history is to be displayed.
     */
    private static void displayPurchaseHistory(VendingMachine vendingMachine) {
        vendingMachine.displayPurchaseHistory();
    }

    /**
     * The main method of the Vending Machine Factory application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        RegularVendingMachine regularVendingMachine = null;
        SpecialVendingMachine specialVendingMachine = null;
        VendingMachine vendingMachine = null;
        boolean exit = false;


        while (!exit) {
            System.out.println("\nVending Machine Factory");
            System.out.println("[1] Create a Vending Machine");
            System.out.println("[2] Test a Vending Machine");
            System.out.println("[3] Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\nCreate a Vending Machine");
                    System.out.println("[1] Regular Vending Machine");
                    System.out.println("[2] Special Vending Machine");
                    System.out.print("Enter your choice: ");
                    int vendingMachineChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (vendingMachineChoice == 1) {
                        System.out.println("\nAdd Initial Items for Regular Vending Machine:");
                        regularVendingMachine = new RegularVendingMachine(10);
                        vendingMachine = regularVendingMachine;
                    } else if (vendingMachineChoice == 2) {
                        System.out.println("\nAdd Initial Items for Special Vending Machine:");
                        specialVendingMachine = new SpecialVendingMachine(10);
                        vendingMachine = specialVendingMachine;
                    }

                    System.out.println("---------------------");
                    break;

                case 2:
                    if (vendingMachine == null) {
                        System.out.println("No vending machine created yet. Please create a vending machine first.");
                        break;
                    }

                    if (vendingMachine instanceof RegularVendingMachine) {
                        System.out.println("\nRegular Vending Machine");
                    } else if (vendingMachine instanceof SpecialVendingMachine) {
                        System.out.println("\nSpecial Vending Machine");
                    }

                    System.out.println("[1] Vending Features");
                    System.out.println("[2] Maintenance Features");
                    System.out.print("Enter choice: ");
                    int featuresChoice = scanner.nextInt();
                    scanner.nextLine();

                    switch (featuresChoice) {
                        case 1:
                            System.out.println("\nVending Features Test");
                            System.out.printf("Available balance: PhP%.2f%n", vendingMachine.getBalance());
                            vendingMachine.displayAvailableItemsWithSlots();

                            boolean endVendingFeatures = false;
                            while (!endVendingFeatures) {
                                System.out.println("[1] Make a Purchase and Enter Money");
                                System.out.println("[2] End Vending Features Test");
                                System.out.print("Enter choice: ");
                                int vendingFeaturesChoice = scanner.nextInt();
                                scanner.nextLine();

                                switch (vendingFeaturesChoice) {
                                    case 1:
                                        System.out.print("Enter the slot number of the item you want to purchase: ");
                                        int slotNumber = scanner.nextInt();
                                        scanner.nextLine();

                                        if (slotNumber < 1 || slotNumber > vendingMachine.getItems().size()) {
                                            System.out.println("Invalid slot number");
                                            break;
                                        }

                                        Item item = vendingMachine.getItems().get(slotNumber - 1);
                                        String itemToPurchase = item.getName();
                                        System.out.printf("%s costs PhP%.2f, are you sure you want to purchase this? [Yes/No]: ",
                                                itemToPurchase, item.getPrice());
                                        String confirmation = scanner.nextLine();

                                        if (confirmation.equalsIgnoreCase("Yes")) {
                                            if (vendingMachine instanceof SpecialVendingMachine) {
                                                System.out.print("Would you like to customize your drink? [Yes/No]: ");
                                                String customizeChoice = scanner.nextLine();
                                                if (customizeChoice.equalsIgnoreCase("Yes")) {
                                                    SpecialVendingMachine specialVending = (SpecialVendingMachine) vendingMachine;
                                                    specialVending.displayAddOns();
                                                    System.out.print("Enter the slot numbers of your desired add-ons to your drink: ");
                                                    String addOnsInput = scanner.nextLine();
                                                    String[] addOnsIndexes = addOnsInput.split(" ");
                                                    List<Integer> selectedAddOns = new ArrayList<>();
                                                    for (String index : addOnsIndexes) {
                                                        selectedAddOns.add(Integer.parseInt(index));
                                                    }
                                                    System.out.println();
                                                    specialVending.dispenseSpecialItem(slotNumber - 1, selectedAddOns);
                                                } else {

                                                    System.out.print("Enter Money: ");
                                                    double amount = scanner.nextDouble();
                                                    scanner.nextLine();

                                                    if (amount <= vendingMachine.getBalance()) {
                                                        vendingMachine.makePurchase(slotNumber - 1, amount);
                                                    } else {
                                                        System.out.println("Insufficient balance. Please load more money.");
                                                    }
                                                }
                                            } else {
                                                System.out.print("Enter Money: ");
                                                double amount = scanner.nextDouble();
                                                scanner.nextLine();

                                                if (amount <= vendingMachine.getBalance()) {
                                                    vendingMachine.makePurchase(slotNumber - 1, amount);
                                                } else {
                                                    System.out.println("Insufficient balance. Please load more money.");
                                                }
                                            }
                                        } else {
                                            System.out.println("Purchase canceled.");
                                        }
                                        break;

                                    case 2:
                                        endVendingFeatures = true;
                                        break;

                                    default:
                                        System.out.println("Invalid choice");
                                        break;
                                }
                            }
                            break;

                        case 2:
                            // Maintenance Features
                            boolean endMaintenanceFeatures = false;
                            while (!endMaintenanceFeatures) {
                                System.out.println("\nMaintenance Features:");
                                System.out.println(vendingMachine.getMaintenanceDescription());
                                System.out.print("Enter choice: ");
                                int maintenanceChoice = scanner.nextInt();
                                scanner.nextLine();

                                switch (maintenanceChoice) {
                                    case 1:
                                        // Check Inventory
                                        vendingMachine.displayStartingInventory();
                                        break;

                                    case 2:
                                        // Add Inventory
                                        System.out.print("Enter the name of the item to add inventory: ");
                                        String itemName = scanner.nextLine();
                                        System.out.print("Enter the quantity to add: ");
                                        int quantity = scanner.nextInt();
                                        scanner.nextLine();

                                        vendingMachine.addInventory(itemName, quantity);
                                        System.out.println("Inventory added successfully.");
                                        break;

                                    case 3:
                                        // Load Money
                                        boolean loadMoreMoney = true;
                                        while (loadMoreMoney) {
                                            System.out.println("\nSelect an amount to load:");
                                            System.out.println("[1] 10PhP");
                                            System.out.println("[2] 20PhP");
                                            System.out.println("[3] 50PhP");
                                            System.out.println("[4] 100PhP");
                                            System.out.print("Enter your choice: ");
                                            int moneyChoice = scanner.nextInt();
                                            scanner.nextLine();

                                            double amount;
                                            switch (moneyChoice) {
                                                case 1:
                                                    amount = 10;
                                                    break;
                                                case 2:
                                                    amount = 20;
                                                    break;
                                                case 3:
                                                    amount = 50;
                                                    break;
                                                case 4:
                                                    amount = 100;
                                                    break;
                                                default:
                                                    System.out.println("Invalid choice");
                                                    continue;
                                            }

                                            vendingMachine.loadMoney(amount);
                                            System.out.printf("\nSuccessfully transacted %.2fPhP into the Vending Machine!%n", amount);

                                            System.out.print("Would you like to load another? [Yes/No]: ");
                                            String choiceInput = scanner.nextLine();
                                            if (!choiceInput.equalsIgnoreCase("Yes")) {
                                                loadMoreMoney = false;
                                                System.out.println("Thank you!");
                                            }
                                        }
                                        break;

                                    case 4:
                                        // Collect Money
                                        System.out.print("\nEnter amount of money to be collected: ");
                                        double amountToCollect = scanner.nextDouble();
                                        scanner.nextLine();

                                        if (amountToCollect <= vendingMachine.getBalance()) {
                                            vendingMachine.deductBalance(amountToCollect);
                                            System.out.printf("%.2fPhP was deducted from balance!%n", amountToCollect);
                                        } else {
                                            System.out.println("Insufficient balance. Collection failed.");
                                        }
                                        break;

                                    case 5:
                                        // Check Balance
                                        vendingMachine.checkBalance();
                                        break;
                                    case 6:
                                        // Purchase History
                                        displayPurchaseHistory(vendingMachine);
                                        break;

                                    case 7:
                                        // Update Price
                                        System.out.print("Enter the name of the item to update price: ");
                                        String itemNameToUpdate = scanner.nextLine();
                                        System.out.print("Enter the new price: ");
                                        double newPrice = scanner.nextDouble();
                                        scanner.nextLine();
                                        vendingMachine.updatePrice(itemNameToUpdate, newPrice);
                                        break;

                                    case 8:
                                        endMaintenanceFeatures = true;
                                        break;

                                    default:
                                        System.out.println("Invalid choice");
                                        break;
                                }
                            }
                            break;

                        default:
                            System.out.println("Invalid choice");
                            break;
                    }
                    break;

                case 3:
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
}
