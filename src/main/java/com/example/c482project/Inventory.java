package com.example.c482project;



import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
/**
 * @author Caleb
 */
public class Inventory {

    private ObservableList<Part> allParts = FXCollections.observableArrayList();
    private ObservableList<Product> allProducts =  FXCollections.observableArrayList();

    public void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * Adds Product to allProducts List
     * @param newProduct new Product to add
     */
    public void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * Allows you to provide a partID and have a part from allParts returned
     * @param partId int Id of the part you are looking up
     * @return part
     */
    public Part lookupPart(int partId) {

        for (Part part: allParts) {
            if (part.getId() == partId) {
                return part;
            }
        }
        return null;
    }

    /**
     * Allows you to provide a partID and have a part from allParts returned
     * @param productId int ID of the product you are looking up
     * @return product
     */
    public Product lookupProduct(int productId) {

        for (Product product: allProducts) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    /**
     * Allows you to provide a partName and have a part from allParts returned
     * @param partName String name of the part you are looking up
     * @return part
     */
    public Part lookupPart(String partName) {

        for (Part part: allParts) {
            if (part.getName().equals(partName)) {
                return part;
            }
        }
        return null;
    }

    /**
     * Allows you to provide a partName and have a part from allParts returned
     * @param productName String name of the product you are looking up
     * @return product
     */
    public Product lookupProduct(String productName) {
        for (Product product: allProducts) {
            if (product.getName().equals(productName)) {
                return product;
            }
        }
        //TODO throw error if it gets here
        //TODO add catch all return
        return null;

    }

    /**
     * Updates part at index with selectedPart
     * @param index int index of the Part to update
     * @param selectedPart Part to replace current part
     */
    public void updatePart(int index, Part selectedPart) {
        this.allParts.set(index, selectedPart);
    }

    /**
     * Updates product at index with selectedProduct
     * @param index int index of the Product to update
     * @param selectedProduct Product to replace current Product
     */
    public void updateProduct(int index, Product selectedProduct) {
        this.allProducts.set(index, selectedProduct);
    }

    /**
     * Deletes selected part from allParts
     * @param selectedPart Part to delete
     * @return true if selectedPart is found and deleted and false if selectedPart is not found
     */
    public boolean deletePart(Part selectedPart) {
        if (allParts.remove(selectedPart)) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Deletes selectedProduct from allProducts
     * @param selectedProduct Product to delete
     * @return true if selectedProduct is found and deleted and false if selectedProduct is not found
     */
    public boolean deleteProduct(Product selectedProduct) {
        if (allProducts.remove(selectedProduct)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns allParts ObservableList
     * @return allParts
     */
    public ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * Returns allProducts ObservableList
     * @return allProducts
     */
    public ObservableList<Product> getAllProducts() {
        return allProducts;
    }

}
