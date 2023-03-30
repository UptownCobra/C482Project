package com.example.c482project;

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
    private void onExitButtonClick() {
        Platform.exit();
    }

    //Parts Modification Buttons
    public void onPartsAddClick() throws IOException {
        c482Project.changeScene("add_Part.fxml","Add Part", 500, 600);
    }

    public void onPartsModifyClick() throws IOException {
        ModifyPartController.setModifyPart(partsTableView.getFocusModel().getFocusedItem());
        setModifyPartIndex(inventory.getAllParts().indexOf(partsTableView.getFocusModel().getFocusedItem()));
        c482Project.changeScene("modify_Part.fxml","Modify Part", 500, 600);
    }

    public void onPartsDeleteClick() {
        confirmPopup.showAndWait();

        if (confirmDelete) {
            inventory.deletePart(partsTableView.getFocusModel().getFocusedItem());
            confirmDelete = false;
        }
    }

    //Product Modification buttons
    public void onProductModifyClick() throws IOException {
        ModifyProductController.setModifyProduct(productTableView.getFocusModel().getFocusedItem());
        setModifyProductIndex(inventory.getAllProducts().indexOf(productTableView.getFocusModel().getFocusedItem()));
        System.out.println(getModifyProductIndex());
        c482Project.changeScene("modify_Product.fxml", "Modify Product", 1300,570);

    }
    public void onProductAddClick() throws IOException {
        c482Project.changeScene("add_Product.fxml", "Add Product", 1300,570);
    }
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
    private boolean searchFindsPart(Part part, String searchText){
        return (part.getName().toLowerCase().contains(searchText.toLowerCase())) ||
                Integer.valueOf(part.getId()).toString().contains(searchText.toLowerCase());
    }
    private Predicate<Part> createPartPredicate(String searchText){
        return part -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return searchFindsPart(part, searchText);
        };
    }

    private Predicate<Product> createProductPredicate(String searchText){
        return product -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return searchFindsProduct(product, searchText);
        };
    }
    private boolean searchFindsProduct(Product product, String searchText){

        return (product.getName().toLowerCase().contains(searchText.toLowerCase())) ||
                Integer.valueOf(product.getId()).toString().contains(searchText.toLowerCase());
    }



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
    public void setModifyPartIndex(int i) {
        modifyPartIndex = i;
    }
    public static int getModifyPartIndex() {
        return modifyPartIndex;
    }
    public void setModifyProductIndex(int i) {modifyProductIndex = i;}
    public static int getModifyProductIndex() {return modifyProductIndex;}
    private void setSearchErrorText(Text errorText, String s){
        errorText.setText("Search did not return any " + s);
    }

    public void checkPartsList() {
        if (partFilteredList.isEmpty()) {
            setSearchErrorText(partSearchErrorText, "parts");
        } else {
            partSearchErrorText.setText("");
        }
    }

    public void checkProductList() {
        if (productFilteredList.isEmpty()) {
            setSearchErrorText(productSearchErrorText, "products");
        } else {
            productSearchErrorText.setText("");
        }
    }
}