/**
 * @author Jamilano and Silva [S12A] CCPROG3
 * MP Pair Group 8
 */

import java.util.Scanner;

/**
 * The RegularVendingMachine class is a subclass of VendingMachine that represents a regular vending machine.
 * It adds initial items to the vending machine and provides methods for dispensing items and updating prices.
 */
public class RegularVendingMachine extends VendingMachine {
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

    /**
     * Constructs a new RegularVendingMachine object with the specified capacity and adds initial items to it.
     *
     * @param capacity the capacity of the vending machine
     */
    public RegularVendingMachine(int capacity) {
        super(capacity);
        addInitialItems();
    }

    /**
     * Adds initial items to the vending machine using user input for setting their prices.
     * The method prompts the user to enter the price for each initial item and then adds the items to the vending machine.
     * The initial items are defined by the 'initialItemNames', 'initialItemQuantity', and 'initialItemCalories' arrays.
     * Each item's name, price, initial quantity, and calories are taken as input from the user.
     * After adding all the initial items, a success message is displayed.
     */
    private void addInitialItems() {
        Scanner scanner = new Scanner(System.in);

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
     * Adds an item to the vending machine with the specified name, price, quantity, and calories.
     *
     * @param name     the name of the item
     * @param price    the price of the item
     * @param quantity the quantity of the item
     * @param calories the number of calories in the item
     */
    @Override
    public void addItem(String name, double price, int quantity, int calories) {
        Item item = new Item(name, price, quantity, calories);
        getItems().add(item);
    }

    /**
     * Updates the price of an item in the vending machine.
     *
     * @param itemNameToUpdate the name of the item to update the price for
     * @param newPrice         the new price for the item
     */
    public void updatePrice(String itemNameToUpdate, double newPrice) {
        for (Item item : getItems()) {
            if (item.getName().equalsIgnoreCase(itemNameToUpdate)) {
                item.setPrice(newPrice);
                System.out.println("Price updated successfully.");
                return; // Exit the method after updating the price
            }
        }
        System.out.println("Item not found. Price update failed.");
    }

    /**
     * Gets the description of the regular vending machine.
     *
     * @return The description of the regular vending machine as a string.
     */
    @Override
    public String getDescription() {
        return "Regular Vending Machine";
    }

    /**
     * Gets the maintenance options available for the regular vending machine.
     *
     * @return The maintenance options as a formatted string containing a list of actions.
     * Each action is represented by a number followed by its corresponding description.
     * The actions include checking inventory, adding inventory, loading money, collecting money,
     * checking balance, viewing purchase history, updating item prices, and exiting maintenance mode.
     * Example return value:
     * "[1] Check Inventory\n[2] Add Inventory\n[3] Load Money\n[4] Collect Money\n[5] Check Balance\n[6] Purchase History\n[7] Update Price\n[8] Exit"
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

    /**
     * The makePurchase() method is overridden in RegularVendingMachine to handle the purchase of items.
     * It calculates the change and deducts the price and change from the balance.
     *
     * @param slotNumber The slot number of the item to purchase.
     * @param amount     The amount of money entered by the user.
     * @return The name of the purchased item if successful, null otherwise.
     */
    @Override
    public String makePurchase(int slotNumber, double amount) {
        if (slotNumber < 1 || slotNumber > getItems().size()) {
            return null; // Invalid slot number
        }

        Item item = getItems().get(slotNumber - 1);
        if (item.getQuantity() == 0) {
            return null; // Item out of stock
        }

        if (amount < item.getPrice()) {
            return null; // Insufficient amount
        }

        // Calculate change
        double change = amount - item.getPrice();
        if (change > getBalance()) {
            return null; // Not enough balance to provide change
        }

        // Update the inventory after dispensing the item
        item.setQuantity(item.getQuantity() - 1);

        // Deduct the price from the balance
        setBalance(getBalance() - item.getPrice());

        // Update the denominations of bills
        updateDenominations(amount - change);

        String purchaseResult = "Item dispensed: " + item.getName() + ", Price: PhP" + item.getPrice();
        if (change > 0) {
            purchaseResult += ", Change: PhP" + String.format("%.2f", change);
        }

        addToPurchaseHistory(purchaseResult);
        return item.getName();
    }
}

