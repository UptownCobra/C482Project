package com.example.c482project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//TODO finish documentation for Product class

/**
 *
 * @author Caleb Logan
 */
public class Product {

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();;
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    public Product(int id, String name, double price, int stock, int min, int max) {
       setId(id);
       this.name = name;
       this.price = price;
       this.stock = stock;
       this.min = min;
       this.max = max;
    }

    /**
     * Sets the id of the Product
     * @param id int ID of the Product
     */
    public void setId(int id) {this.id = id;
    }

    /**
     * Sets the name of the Product
     * @param name String name value
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the price of the Product
     * @param price double price value
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the stock of the product
     * @param stock int stock value
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Sets the minimum value of the Product
     * @param min int min value
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Sets the maximum value of the Product
     * @param max int max value
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Returns the Product ID
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the product name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the Product price
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the product Stock
     * @return stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * Returns the Products Minimum value
     * @return min
     */
    public int getMin() {
        return min;
    }

    /**
     * Returns the Products Maximum value
     * @return max
     */
    public int getMax() {
        return max;
    }

    /**
     * Adds a part the AssosciatedParts list
     * @param part The part you would like to add to the Products associated parts list
     */
    public void addAssociatedPart(Part part) {
        this.associatedParts.add(part);
    }

    /**
     * Deletes the selected associated part
     * @param selectedAssociatedPart associated part to be deleted
     * @return true if part exists in associatedParts false if the part does not exist in associatedParts
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        if (associatedParts.contains(selectedAssociatedPart)) {
            this.associatedParts.remove(selectedAssociatedPart);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the list of associatedParts
     * @return associatedParts
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }


}
