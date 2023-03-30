package com.example.c482project;

/**
 * Supplied class Part.java
 * @author Caleb Logan
 */
public abstract class Part {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * Returns Part ID
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets part ID
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Return part Name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the Part Name
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the part price
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the part price
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns part stock
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets Part Stock
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * returns part min
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * Sets the part min
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Returns the part Max
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * Sets the part max
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }


}