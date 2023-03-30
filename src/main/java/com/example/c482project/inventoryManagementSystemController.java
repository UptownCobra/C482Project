package com.example.c482project;

/**
 *
 * @RUNTIMEERROR occurred in Initialize. Could not get partTableView to show any data. I went into multiple iterations
 * of getting the data to show including creating my own gets for the setCellFactories. In the end I had the partTableView
 * variable set up wrong.
 *
 * @RUNTIMEERROR onPartsDeleteClick initially had confirmPopup.show() and attempted to use the .wait() and .notify() but
 * was not using correctly so they threw an error. Eventually found the .showAndWait() function.
 * @author Caleb
 */

import javafx.application.Platform;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.function.Predicate;

import static com.example.c482project.c482Project.*;

public class inventoryManagementSystemController {



    public Button exitButton;
    public Button partsAdd;
    public Button partsModify;
    public Button partsDelete;
    public Text partSearchErrorText;
    public Text productSearchErrorText;
    @FXML
    TableView<Product> productTableView = new TableView<>();
    @FXML
    TableColumn<Product, Integer> productIDTableColumn = new TableColumn<>();
    @FXML
    TableColumn<Product, String> productNameTableColumn= new TableColumn<>();
    @FXML
    TableColumn<Product, Integer> productInventoryLevelTableView= new TableColumn<>();
    @FXML
    TableColumn<Product, Double> productPricePerUnitTableColumn= new TableColumn<>();
    public TextField productsTextField;
    public Button productsAddBtn;
    public Button productsModifyBtn;
    public Button productsDeleteBtn;
    @FXML
    TextField partsTextfield;
    @FXML
    TableColumn<Part, String> partNameTableColumn = new TableColumn<>();


    @FXML
    TableColumn<Part, Integer> partIDTableColumn = new TableColumn<>();
    @FXML
    TableColumn<Part, Integer> partInventoryLevelTableColumn = new TableColumn<>();
    @FXML
    TableColumn<Part, Double> partPricePerUnitTableColumn = new TableColumn<>();

    @FXML
    TableView<Part> partsTableView = new TableView<>();
    private FilteredList<Product> productFilteredList = new FilteredList<>(inventory.getAllProducts());
    private FilteredList<Part> partFilteredList = new FilteredList<>(inventory.getAllParts());
    private static int modifyPartIndex;
    private static int modifyProductIndex;



    @FXML
    /**
     * Closes the application when clicked
     */
    private void onExitButtonClick() {
        Platform.exit();
    }

    //Parts Modification Buttons

    /**
     * Changes the scene to the Add Parts Scene
     * @throws IOException not entirely sure why this is needed.
     */
    public void onPartsAddClick() throws IOException {
        c482Project.changeScene("add_Part.fxml","Add Part", 500, 600);
    }

    /**
     * Changes the scene to the Modify Parts scene.
     * Passes the index of the Part to the ModifyPartController.
     * Passes the selected Part to the ModifyPartController
     * @throws IOException needed
     */
    public void onPartsModifyClick() throws IOException {
        ModifyPartController.setModifyPart(partsTableView.getFocusModel().getFocusedItem());
        setModifyPartIndex(inventory.getAllParts().indexOf(partsTableView.getFocusModel().getFocusedItem()));
        c482Project.changeScene("modify_Part.fxml","Modify Part", 500, 600);
    }

    /**
     * Shows ConfirmPopup window to verify user decision
     * if user confirms selected part is deleted.
     * If declined part is not deleted
     */
    public void onPartsDeleteClick() {
        confirmPopup.showAndWait();

        if (confirmDelete) {
            inventory.deletePart(partsTableView.getFocusModel().getFocusedItem());
            confirmDelete = false;
        }
    }

    //Product Modification buttons

    /**
     * Changes the scene to the Modify Product scene.
     * Passes the index of the Part to the ModifyProductController.
     * Passes the selected Part to the ModifyProductController
     * @throws IOException needed when changing scenes
     */
    public void onProductModifyClick() throws IOException {
        ModifyProductController.setModifyProduct(productTableView.getFocusModel().getFocusedItem());
        setModifyProductIndex(inventory.getAllProducts().indexOf(productTableView.getFocusModel().getFocusedItem()));
        c482Project.changeScene("modify_Product.fxml", "Modify Product", 1300,570);

    }

    /**
     * Changes scene to add Product
     * @throws IOException needed for sceneChanges
     */
    public void onProductAddClick() throws IOException {
        c482Project.changeScene("add_Product.fxml", "Add Product", 1300,570);
    }

    /**
     * Shows confirmationPopup to verify users choice
     * verifies if the Product had associated Parts. If it does calls showPopup to inform the user.
     */
    public void onProductDeleteClick() {
        confirmPopup.showAndWait();
        if(confirmDelete) {
            Product product = productTableView.getFocusModel().getFocusedItem();
            if (product.getAllAssociatedParts().isEmpty()) {
                inventory.deleteProduct(product);
            } else {
                c482Project.showPopup();
            }
        }
    }

    //Search functions

    /**
     * Checks to see if the SearchText matches a parts name or ID
     * @param part Part contained in the Parts list in Inventory
     * @param searchText Text from textField
     * @return true if parts name or ID contains searchText
     */
    private boolean searchFindsPart(Part part, String searchText){
        return (part.getName().toLowerCase().contains(searchText.toLowerCase())) ||
                Integer.valueOf(part.getId()).toString().contains(searchText.toLowerCase());
    }

    /**
     * Creates a Predicate that is attached as a listener to the textBox. Allows for filtering of the partsList
     * @param searchText text from search Textbox
     * @return parts that have a name or ID that contains any searchText
     */
    private Predicate<Part> createPartPredicate(String searchText){
        return part -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return searchFindsPart(part, searchText);
        };
    }

    /**
     * Creates a Predicate that is attached as a listener to the textBox. Allows for filtering of the productList
     * @param searchText text from search Textbox
     * @return Product that have a name or ID that contains any searchText
     */
    private Predicate<Product> createProductPredicate(String searchText){
        return product -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return searchFindsProduct(product, searchText);
        };
    }
    /**
     * Checks to see if the SearchText matches a product name or ID
     * @param product product contained in the Parts list in Inventory
     * @param searchText Text from textField
     * @return true if product name or ID contains searchText
     */
    private boolean searchFindsProduct(Product product, String searchText){

        return (product.getName().toLowerCase().contains(searchText.toLowerCase())) ||
                Integer.valueOf(product.getId()).toString().contains(searchText.toLowerCase());
    }


    /**
     * Initializes the productTableView with the productFilteredList and sets the CellFactoryValue for all the tableColumns
     * Initializes the partTableView with the partFilteredList and sets the CellFactoryValue for all the tableColumns
     * Initializes the partTextField with a listener that allows for searching the partTableView
     * Initializes the productTextField with a listener that allows for searching of the productTableView
     * Initializes modifyPartIndex and modifyProductIndex with -1
     */
    @FXML
   public void initialize() {
        //Initializing Product Table View
        productTableView.setItems(productFilteredList);
        productIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryLevelTableView.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPricePerUnitTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

       //Initialize Parts Table
        partsTableView.setItems(partFilteredList);
        partIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLevelTableColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPricePerUnitTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Initialize Search parts
        partsTextfield.textProperty().addListener((observable, oldValue, newValue) ->
            partFilteredList.setPredicate(createPartPredicate(newValue)));



        //Initialize Search Products
        productsTextField.textProperty().addListener((observable, oldValue, newValue) ->
            productFilteredList.setPredicate(createProductPredicate(newValue)));
    setModifyPartIndex(-1);
    setModifyProductIndex(-1);
   }

    /**
     *
     * @param i new Value of modifyPartIndex
     */
    public void setModifyPartIndex(int i) {
        modifyPartIndex = i;
    }

    /**
     * Returns modifyPartIndex
     * @return modifyPartIndex
     */
    public static int getModifyPartIndex() {
        return modifyPartIndex;
    }

    /**
     * sets modifyProductIndex
     * @param i new modifyProductIndex value
     */
    public void setModifyProductIndex(int i) {modifyProductIndex = i;}

    /**
     * Returns modifyProductIndex
     * @return modifyProductIndex
     */
    public static int getModifyProductIndex() {return modifyProductIndex;}

    /**
     * Sets the error text
     * @param errorText Text that needs to be updated
     * @param s String needs to be Parts or Products
     */
    private void setSearchErrorText(Text errorText, String s){
        errorText.setText("Search did not return any " + s);
    }

    /**
     * Checks to see if the Parts list is empty
     * If parts list is empty then display error text.
     */
    public void checkPartsList() {
        if (partFilteredList.isEmpty()) {
            setSearchErrorText(partSearchErrorText, "parts");
        } else {
            partSearchErrorText.setText("");
        }
    }
    /**
     * Checks to see if the Product list is empty
     * If product list is empty then display error text.
     */
    public void checkProductList() {
        if (productFilteredList.isEmpty()) {
            setSearchErrorText(productSearchErrorText, "products");
        } else {
            productSearchErrorText.setText("");
        }
    }
}