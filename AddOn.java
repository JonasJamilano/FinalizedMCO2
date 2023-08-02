/**
 * @author Jamilano and Silva [S12A] CCPROG3
 * MP Pair Group 8
 */

/**
 * The AddOn class represents an additional item that can be added to a vending machine item,
 * such as customizations or extras.
 */
public class AddOn {
    private String name; // The name of the add-on
    private double price; // The price of the add-on in PhP

    /**
     * Constructs a new AddOn object with the specified name and price.
     *
     * @param name  The name of the add-on.
     * @param price The price of the add-on in PhP.
     */
    public AddOn(String name, double price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Gets the name of the add-on.
     *
     * @return The name of the add-on.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the price of the add-on in PhP.
     *
     * @return The price of the add-on.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns a string representation of the AddOn object.
     *
     * @return A string representation of the add-on in the format "name pricePhP".
     */
    @Override
    public String toString() {
        return name + " " + price + "PhP";
    }
}

