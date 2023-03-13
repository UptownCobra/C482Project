package com.example.c482project;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.io.IOException;

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
    public TextField partsTextfield;
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

    @FXML
    private void onExitButtonClick() {
        Platform.exit();
    }

    public void onPartsAddClick(MouseEvent mouseEvent) throws IOException {
        c482Project.changeScene("add_Part.fxml","Add Part", 500, 600);
    }

    public void onPartsModifyClick(MouseEvent mouseEvent) throws IOException {
        c482Project.changeScene("modify_Part.fxml","Modify Part", 500, 600);
    }

    public void onPartsDeleteClick(MouseEvent mouseEvent) {
    }



@FXML
   public void initialize() {
        //Initializing Product Table View
        productTableView.setItems(c482Project.inventory.getAllProducts());
        productIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryLevelTableView.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPricePerUnitTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

       //Initialize Parts Table
        partsTableView.setItems(c482Project.inventory.getAllParts());
        partIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLevelTableColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPricePerUnitTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

   }

}