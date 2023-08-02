/**
 * @author Jamilano and Silva [S12A] CCPROG3
 * MP Pair Group 8
 */

import java.util.ArrayList;
import java.util.List;

/**
 * The VendingMachine class represents a generic vending machine that can hold and dispense items.
 * It also keeps track of the balance and denominations for change.
 */
public class VendingMachine {

    private List<Item> items;
    private double balance;
    public int numTens;
    public int numTwenties;
    public int numFifties;
    public int numHundreds;
    public List<String> purchaseHistory;

    /**
     * Constructs a new VendingMachine object with the specified capacity.
     *
     * @param capacity the capacity of the vending machine
     */
    public VendingMachine(int capacity) {
        this.items = new ArrayList<>();
        this.balance = 0.00;
        this.numTens = 0;
        this.numTwenties = 0;
        this.numFifties = 0;
        this.numHundreds = 0;
        this.purchaseHistory = new ArrayList<>();
    }

    /**
     * Adds the given item to the purchase history list.
     *
     * @param item The item to be added to the purchase history list.
     */
    protected void addToPurchaseHistory(String item) {
        purchaseHistory.add(item);
    }

    /**
     * Displays the purchase history of the vending machine.
     * If no items have been purchased yet, it displays a message indicating that.
     */
    public void displayPurchaseHistory() {
        System.out.println("\nPurchase History:");
        if (purchaseHistory.isEmpty()) {
            System.out.println("No items purchased yet.");
        } else {
            for (String item : purchaseHistory) {
                System.out.println(item);
            }
        }
    }

    /**
     * Adds a new item to the vending machine with the specified details.
     *
     * @param name     the name of the item
     * @param price    the price of the item
     * @param quantity the quantity of the item
     * @param calories the number of calories in the item
     */
    public void addItem(String name, double price, int quantity, int calories) {
        Item item = new Item(name, price, quantity, calories);
        items.add(item);
    }

    /**
     * Gets the purchase history of the vending machine.
     *
     * @return the list of purchased items in the purchase history
     */
    public List<String> getPurchaseHistory() {
        return purchaseHistory;
    }

    /**
     * Records the purchase details in the purchase history list.
     *
     * @param purchaseDetails A string containing the details of the purchase to be recorded.
     */
    public void recordPurchase(String purchaseDetails) {
        purchaseHistory.add(purchaseDetails);
    }

    /**
     * Gets the current balance of the vending machine.
     *
     * @return the current balance of the vending machine
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Gets the list of items available in the vending machine.
     *
     * @return the list of items in the vending machine
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Displays the available items in the vending machine along with their details.
     * The details include the slot number, name, price, quantity, and calories of each item.
     */
    public void displayAvailableItemsWithSlots() {
        System.out.println("Available Items:");
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            System.out.printf("Slot [%d] %s - Price: PhP%.2f, Quantity: %d, Calories: %d%n",
                    i + 1, item.getName(), item.getPrice(), item.getQuantity(), item.getCalories());
        }
        System.out.println();
    }

    /**
     * Gets the description of the vending machine.
     *
     * @return the description of the vending machine
     */
    public String getDescription() {
        return "Generic Vending Machine";
    }

    /**
     * Retrieves the item from the vending machine based on the specified slot number.
     *
     * @param slotNumber The slot number of the item to retrieve. The slot numbers start from 1.
     * @return The Item object representing the item in the specified slot, or null if the slot number is out of range.
     */
    public Item getItemBySlotNumber(int slotNumber) {
        if (slotNumber < 1 || slotNumber > items.size()) {
            return null; // Slot number is out of range
        }

        return items.get(slotNumber - 1);
    }

    /**
     * Gets the maintenance description for the vending machine.
     * This description includes various maintenance options that can be performed by the operator.
     *
     * @return the maintenance description for the vending machine
     */
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

    // Method to dispense an item and decrease its quantity
    protected void setBalance(double newBalance) {
        this.balance = newBalance;
    }

    /**
     * Displays the starting inventory of the vending machine.
     * It shows the name, price, and quantity of each item in the inventory.
     */
    public void displayStartingInventory() {
        System.out.println("Starting Inventory:");
        for (Item item : items) {
            System.out.println(item.getName() + " - Price: PhP" + item.getPrice() + ", Quantity: " + item.getQuantity());
        }
        System.out.println();
    }

    /**
     * Adds inventory to an existing item in the vending machine.
     *
     * @param itemName the name of the item to which inventory is to be added
     * @param quantity the quantity of the item to add to the inventory
     */
    public void addInventory(String itemName, int quantity) {
        for (Item item : items) {
            if (item.getName().equals(itemName)) {
                item.setQuantity(item.getQuantity() + quantity);
                System.out.println("Inventory added: " + itemName + ", Quantity: " + quantity);
                return;
            }
        }
        System.out.println("Item not found: " + itemName);
    }

    /**
     * Loads money into the vending machine.
     * It updates the balance and denominations of the vending machine based on the amount loaded.
     *
     * @param amount the amount of money to load into the vending machine
     */
    public void loadMoney(double amount) {
        if (amount == 10) {
            numTens++;
        } else if (amount == 20) {
            numTwenties++;
        } else if (amount == 50) {
            numFifties++;
        } else if (amount == 100) {
            numHundreds++;
        }

        balance += amount;
        System.out.println("Money loaded: PhP" + amount);
    }

    /**
     * Checks the current balance and denominations of the vending machine.
     * It displays the current balance and the number of each denomination available.
     */
    public void checkBalance() {
        System.out.println("Current Balance: PhP" + this.balance);
        System.out.printf("Number of 10s: %d%n", numTens);
        System.out.printf("Number of 20s: %d%n", numTwenties);
        System.out.printf("Number of 50s: %d%n", numFifties);
        System.out.printf("Number of 100s: %d%n", numHundreds);
        double total = (numTens * 10) + (numTwenties * 20) + (numFifties * 50) + (numHundreds * 100);
        System.out.printf("Total Denomination: PhP%.2f%n", total);
    }

    /**
     * Deducts the given amount from the vending machine's balance and updates the denominations.
     *
     * @param amount the amount to deduct from the balance
     */
    public void deductBalance(double amount) {
        balance -= amount;
        while (amount > 0) {
            if (numHundreds > 0 && amount >= 100) {
                amount -= 100;
                numHundreds--;
            } else if (numFifties > 0 && amount >= 50) {
                amount -= 50;
                numFifties--;
            } else if (numTwenties > 0 && amount >= 20) {
                amount -= 20;
                numTwenties--;
            } else if (numTens > 0 && amount >= 10) {
                amount -= 10;
                numTens--;
            } else {
                // If there are not enough denominations to deduct, break out of the loop.
                break;
            }
        }
        balanceToDenominations(); // Move this line outside of the while loop
    }

    /**
     * Adds a purchase to the purchase history list.
     *
     * @param name  the name of the purchased item
     * @param price the price of the purchased item
     */
    public void addToPurchaseHistory(String name, double price) {
        purchaseHistory.add(name + " - Price: PhP " + price);
    }

    /**
     * Handles a purchase made by the user.
     * It processes the purchase, deducts the balance, and updates the item's quantity.
     *
     * @param slotNumber the slot number of the item to purchase
     * @param amount     the amount of money entered for the purchase
     * @return the name of the purchased item or null if the purchase failed
     */
    public String makePurchase(int slotNumber, double amount) {
        // Adjust the slotNumber to be 0-based index
        int adjustedSlotNumber = slotNumber - 1;

        if (adjustedSlotNumber < 0 || adjustedSlotNumber >= items.size()) {
            // Invalid slot number
            return null;
        }

        Item item = items.get(adjustedSlotNumber);

        if (item.getPrice() > amount) {
            // Insufficient funds
            return null;
        }

        if (item.getQuantity() <= 0) {
            // Item is out of stock
            return null;
        }

        // Process the purchase
        double change = amount - item.getPrice();
        if (change > 0) {
            deductBalance(change); // Update the balance to return the change
        }

        item.setQuantity(item.getQuantity() - 1); // Decrease the quantity of the purchased item
        addToPurchaseHistory(item.getName(), item.getPrice()); // Add to purchase history

        return item.getName(); // Return the name of the purchased item for displaying in the GUI
    }

    /**
     * Updates the denominations in the vending machine after returning change to the user.
     * This method is used to convert the remaining change amount into the fewest number of bills.
     *
     * @param change the change amount in pesos
     */
    protected void updateDenominations(double change) {
        int remainingChange = (int) (change * 100); // Convert to cents
        while (remainingChange > 0) {
            if (numHundreds > 0 && remainingChange >= 10000) {
                remainingChange -= 10000;
                numHundreds--;
            } else if (numFifties > 0 && remainingChange >= 5000) {
                remainingChange -= 5000;
                numFifties--;
            } else if (numTwenties > 0 && remainingChange >= 2000) {
                remainingChange -= 2000;
                numTwenties--;
            } else if (numTens > 0 && remainingChange >= 1000) {
                remainingChange -= 1000;
                numTens--;
            } else {
                // If there are not enough denominations to deduct, break out of the loop.
                break;
            }
        }
        balanceToDenominations();
    }

    /**
     * Updates the denominations in the vending machine based on the current balance.
     * This method is used to convert the balance amount into the fewest number of bills.
     * It should be called whenever the balance changes to keep the denominations up to date.
     */
    public void balanceToDenominations() {
        numHundreds = (int) (balance / 100);
        double remainingBalance = balance % 100;
        numFifties = (int) (remainingBalance / 50);
        remainingBalance %= 50;
        numTwenties = (int) (remainingBalance / 20);
        remainingBalance %= 20;
        numTens = (int) (remainingBalance / 10);
    }

    /**
     * Updates the price of an item in the vending machine.
     *
     * @param itemNameToUpdate the name of the item to update
     * @param newPrice         the new price to set for the item
     */
    public void updatePrice(String itemNameToUpdate, double newPrice) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemNameToUpdate)) {
                item.setPrice(newPrice);
                System.out.println("Price updated successfully.");
                return;
            }
        }
        System.out.println("Item not found. Price update failed.");
    }

    /**
     * Gets the number of ten-peso bills in the vending machine.
     *
     * @return the number of ten-peso bills
     */
    public int getNumTens() {
        return numTens;
    }

    /**
     * Gets the number of twenty-peso bills in the vending machine.
     *
     * @return the number of twenty-peso bills
     */
    public int getNumTwenties() {
        return numTwenties;
    }

    /**
     * Gets the number of fifty-peso bills in the vending machine.
     *
     * @return the number of fifty-peso bills
     */
    public int getNumFifties() {
        return numFifties;
    }

    /**
     * Gets the number of one-hundred-peso bills in the vending machine.
     *
     * @return the number of one-hundred-peso bills
     */
    public int getNumHundreds() {
        return numHundreds;
    }

}

