/**
 * @author Jamilano and Silva [S12A] CCPROG3
 * MP Pair Group 8
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpecialVendingMachine extends VendingMachine {
    private final String[] initialItemNames = {
            "Caffe Latte",
            "Vanilla Frappuccino",
            "Caramel Frappuccino",
            "Cold Brew Coffee",
            "Green Tea Matcha Latte",
            "Iced Caramel Macchiato",
            "Hazelnut Latte",
            "Sea Salt Latte"
    };

    private final int initialItemQuantity = 10;

    private final int[] initialItemCalories = {
            100, 150, 120, 180, 80, 140, 90, 110
    };

    private final List<AddOn> addOns;
    private final Scanner scanner;

    /**
     * Constructs a new SpecialVendingMachine object with the specified capacity and adds initial items to it.
     *
     * @param capacity the capacity of the vending machine
     */
    public SpecialVendingMachine(int capacity) {
        super(capacity);
        addOns = new ArrayList<>();
        addOns.add(new AddOn("Whole Milk", 10.00));
        addOns.add(new AddOn("Non-Fat Milk", 10.00));
        addOns.add(new AddOn("Vanilla Syrup", 20.00));
        addOns.add(new AddOn("Caramel Syrup", 20.00));
        addOns.add(new AddOn("Soy Milk", 10.00));
        addOns.add(new AddOn("Oat Milk", 10.00));
        addOns.add(new AddOn("Caramel Drizzle", 30.00));
        addOns.add(new AddOn("Mocha Drizzle", 30.00));
        scanner = new Scanner(System.in);
        addInitialItems();
    }

    /**
     * Performs a purchase of a special item from the vending machine with the specified slot number.
     *
     * @param slotNumber the slot number of the special item to be purchased
     * @param amount     the amount of money entered by the user for the purchase
     * @return the name of the purchased item if the purchase is successful; otherwise, returns null
     */
    public String makePurchase(int slotNumber, double amount) {
        Item item = getItemBySlot(slotNumber);
        if (item == null) {
            return null; // Invalid slot number
        }

        double itemPrice = item.getPrice();
        if (amount < itemPrice) {
            return null; // Insufficient funds
        }

        if (item.getQuantity() <= 0) {
            return null; // Item is out of stock
        }

        // Process the purchase
        double change = amount - itemPrice;
        if (change > 0) {
            deductBalance(change); // Update the balance to return the change
        }

        List<Integer> selectedAddOns = new ArrayList<>();
        dispenseSpecialItem(slotNumber, selectedAddOns);

        return item.getName(); // Return the name of the purchased item
    }

    /**
     * Helper method to get an item from the vending machine based on the slot number.
     *
     * @param slotNumber the slot number of the item to retrieve
     * @return the Item object corresponding to the given slot number; returns null if the slot number is invalid
     */
    private Item getItemBySlot(int slotNumber) {
        int adjustedSlotNumber = slotNumber - 1;
        if (adjustedSlotNumber >= 0 && adjustedSlotNumber < getItems().size()) {
            return getItems().get(adjustedSlotNumber);
        }
        return null;
    }

    /**
     * Adds the initial items to the vending machine by prompting for their prices.
     * This method is called when the SpecialVendingMachine object is created.
     */
    private void addInitialItems() {
        System.out.println("\nAdd Initial Items:");
        for (int i = 0; i < initialItemNames.length; i++) {
            System.out.print("Enter price for " + initialItemNames[i] + ": ");
            double newPrice = scanner.nextDouble();
            scanner.nextLine(); // Consume newline character
            addItem(initialItemNames[i], newPrice, initialItemQuantity, initialItemCalories[i]);
        }
        System.out.println("Initial items added successfully.");
        System.out.println("---------------------");
    }

    /**
     * Displays the available add-ons in the special vending machine.
     */
    public void displayAddOns() {
        System.out.println("\nHere are the Add-ons:");
        for (int i = 0; i < addOns.size(); i++) {
            AddOn addOn = addOns.get(i);
            System.out.printf("[%d] %s %.2fPhP%n", i + 1, addOn.getName(), addOn.getPrice());
        }
    }

    /**
     * Gets the total price of the special item with the selected add-ons.
     *
     * @param itemSlot         the slot number of the special item
     * @param selectedAddOns   the list of selected add-ons represented by their index numbers
     * @return the total price of the special item with the selected add-ons
     */
    public double calculateTotal(int itemSlot, List<Integer> selectedAddOns) {
        double total = getItems().get(itemSlot).getPrice();
        for (int addOnIndex : selectedAddOns) {
            if (addOnIndex >= 1 && addOnIndex <= addOns.size()) {
                total += addOns.get(addOnIndex - 1).getPrice();
            }
        }
        return total;
    }

    /**
     * Dispenses a special item with the selected add-ons based on the slot number and selected add-ons.
     *
     * @param slotNumber       the slot number of the special item to be dispensed
     * @param selectedAddOns   the list of selected add-ons represented by their index numbers
     */
    public void dispenseSpecialItem(int slotNumber, List<Integer> selectedAddOns) {
        Item item = super.getItems().get(slotNumber);
        System.out.println(item.getName() + " " + String.format("%.2f", item.getPrice()) + "PhP");

        Scanner scanner = new Scanner(System.in);

        // Ask the user to add add-ons
        while (true) {
            System.out.println("\nDo you want to add another add-ons?");
            System.out.println("[1] Yes");
            System.out.println("[2] No");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            if (choice == 1) {
                displayAddOns();
                System.out.print("Select an add-on (enter the number): ");
                int addOnNumber = scanner.nextInt();
                selectedAddOns.add(addOnNumber);
            } else if (choice == 2) {
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        double total = item.getPrice() + getAddOnsTotal(selectedAddOns);
        System.out.println("Total: " + String.format("%.2f", total) + "PhP");

        // Ask the user to enter money
        System.out.print("Enter money: ");
        double moneyEntered = scanner.nextDouble();

        // Calculate the change
        double change = moneyEntered - total;
        if (change < 0) {
            System.out.println("Insufficient money. Please try again.");
        } else {
            System.out.println("Change: " + String.format("%.2f", change) + "PhP");

            // Update the inventory after dispensing the item
            int remainingQuantity = item.getQuantity() - 1;
            if (remainingQuantity < 0) {
                System.out.println("Sorry, the item is out of stock.");
            } else {
                item.setQuantity(remainingQuantity);
                System.out.println("Dispensing the item: " + item.getName());
                System.out.println("Remaining quantity: " + remainingQuantity);
            }
            String itemDispensed = "Item dispensed: " + item.getName() + ", Price: PhP" + total + " (incl. add-ons)";
            addToPurchaseHistory(itemDispensed);

            // Display the selected add-ons
            System.out.println("Selected Add-ons:");
            for (int index : selectedAddOns) {
                AddOn addOn = getAddOns().get(index - 1);
                System.out.println(addOn.getName() + " " + String.format("%.2f", addOn.getPrice()) + "PhP");
            }
            for (int index : selectedAddOns) {
                AddOn addOn = getAddOns().get(index - 1);
                String addOnDispensed = addOn.getName() + " " + String.format("%.2f", addOn.getPrice()) + "PhP";
                addToPurchaseHistory(addOnDispensed);
            }
        }
    }

    /**
     * Gets the total price of the selected add-ons.
     *
     * @param selectedAddOns   the list of selected add-ons represented by their index numbers
     * @return the total price of the selected add-ons
     */
    public double getAddOnsTotal(List<Integer> selectedAddOns) {
        double total = 0;
        for (int index : selectedAddOns) {
            total += addOns.get(index - 1).getPrice();
        }
        return total;
    }

    /**
     * Gets the list of available add-ons in the special vending machine.
     *
     * @return the list of available add-ons
     */
    public List<AddOn> getAddOns() {
        return addOns;
    }

    /**
     * Gets the description of the special vending machine.
     *
     * @return the description of the special vending machine
     */
    @Override
    public String getDescription() {
        return "Special Vending Machine";
    }

    /**
     * Gets the maintenance description for the special vending machine.
     * This description includes various maintenance options that can be performed by the operator.
     *
     * @return the maintenance description for the special vending machine
     */
    @Override
    public String getMaintenanceDescription() {
        return "[1] Check Inventory\n" +
                "[2] Add Inventory\n" +
                "[3] Load Money\n" +
                "[4] Collect Money\n" +
                "[5] Check Balance\n" +
                "[6] Purchase History\n" +
                "[7] Update Price\n" +
                "[8] Exit";
    }
}
