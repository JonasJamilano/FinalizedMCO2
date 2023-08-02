/**
 * @author Jamilano and Silva [S12A] CCPROG3
 * MP Pair Group 8
 */

/**
 * The Item class represents an item that can be stored and purchased in a vending machine.
 */
public class Item {

    private String name; // The name of the item
    private double price; // The price of the item in PhP
    private int quantity; // The quantity of the item in stock
    private int calories; // The number of calories in the item

    /**
     * Constructs a new Item object with the specified name, price, quantity, and calories.
     *
     * @param name     The name of the item.
     * @param price    The price of the item in PhP.
     * @param quantity The quantity of the item in stock.
     * @param calories The number of calories in the item.
     */
    public Item(String name, double price, int quantity, int calories) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.calories = calories;
    }

    /**
     * Gets the name of the item.
     *
     * @return The name of the item.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the price of the item in PhP.
     *
     * @return The price of the item.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the item.
     *
     * @param price The new price of the item in PhP.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the quantity of the item in stock.
     *
     * @return The quantity of the item.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the item in stock.
     *
     * @param quantity The new quantity of the item.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the number of calories in the item.
     *
     * @return The number of calories in the item.
     */
    public int getCalories() {
        return calories;
    }

    /**
     * Decreases the quantity of the item by 1.
     * If the quantity is already 0, no further decrease occurs.
     */
    public void decreaseQuantity() {
        if (quantity > 0) {
            quantity--;
        }
    }
}