package com.example.c482project;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
//TODO Create documentation for Inventory class
public class Inventory {

    private ObservableList<Part> allParts = FXCollections.observableArrayList();
    private ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     *
     * @param newProduct
     */
    public void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     *
     * @param partId
     * @return
     */
    public Part lookupPart(int partId) {

        for (Part part: allParts) {
            if (part.getId() == partId) {
                return part;
            }
        }
        //TODO create return catch all
    }

    /**
     *
     * @param productId
     * @return
     */
    public Product lookupProduct(int productId) {

        for (Product product: allProducts) {
            if (product.getId() == productId) {
                return product;
            }
        }
        //TODO add return catch all
    }

    /**
     *
     * @param partName
     * @return
     */
    public Part lookupPart(String partName) {

        for (Part part: allParts) {
            if (part.getName().equals(partName)) {
                return part;
            }
        }
        //TODO throw error if code gets here
        //TODO add catch all return
    }

    /**
     *
     * @param productName
     * @return
     */
    public Product lookupProduct(String productName) {
        //TODO add try catch for when there is no product matching productName
        for (Product product: allProducts) {
            if (product.getName().equals(productName)) {
                return product;
            }
        }
        //TODO throw error if it gets here
        //TODO add catch all return

    }

    /**
     *
     * @param index
     * @param selectedPart
     */
    public void updatePart(int index, Part selectedPart) {
        this.allParts.set(index, selectedPart);
    }

    /**
     *
     * @param index
     * @param selectedProduct
     */
    public void updateProduct(int index, Product selectedProduct) {
        //TODO test updateProduct function
        this.allProducts.set(index, selectedProduct);
    }

    /**
     *
     * @param selectedPart
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
     *
     * @param selectedProduct
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
     *
     * @return allParts
     */
    public ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     *
     * @return allProducts
     */
    public ObservableList<Product> getAllProducts() {
        return allProducts;
    }

}
