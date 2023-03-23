package com.example.c482project;

import javafx.application.Platform;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.function.Predicate;

import static com.example.c482project.c482Project.inventory;

public class inventoryManagementSystemController {



    public Button exitButton;
    public Button partsAdd;
    public Button partsModify;
    public Button partsDelete;
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
    private static int modifyPartIndex = -1;


    @FXML
    private void onExitButtonClick() {
        Platform.exit();
    }

    public void onPartsAddClick(MouseEvent mouseEvent) throws IOException {
        c482Project.changeScene("add_Part.fxml","Add Part", 500, 600);
    }

    public void onPartsModifyClick(MouseEvent mouseEvent) throws IOException {
        ModifyPartController.setModifyPart(partsTableView.getFocusModel().getFocusedItem());
        setModifyPartIndex(inventory.getAllParts().indexOf(partsTableView.getFocusModel().getFocusedItem()));
        c482Project.changeScene("modify_Part.fxml","Modify Part", 500, 600);
    }

    public void onPartsDeleteClick(MouseEvent mouseEvent) {
       inventory.deletePart(partsTableView.getFocusModel().getFocusedItem());
    }
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

   }
    public void setModifyPartIndex(int i) {
        modifyPartIndex = i;
    }
    public static int getModifyPartIndex() {
        return modifyPartIndex;
    }

}