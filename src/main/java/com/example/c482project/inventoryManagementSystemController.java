package com.example.c482project;

import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
    
    public static TableView<Product> productTableView = new TableView<>();
    public static TableColumn<Product, String> productIDTableColumn = new TableColumn<>();
    public static TableColumn<Product, String> productNameTableColumn= new TableColumn<>();
    public static TableColumn<Product, String> productInventoryLevelTableView= new TableColumn<>();
    public static TableColumn<Product, String> productPricePerUnitTableColumn= new TableColumn<>();
    public TextField productsTextField;
    public Button productsAddBtn;
    public Button productsModifyBtn;
    public Button productsDeleteBtn;
    public TextField partsTextfield;
    @FXML
    public static TableColumn<InHouse, String> partNameTableColumn = new TableColumn("Part Name");


    @FXML
    public static TableColumn<InHouse, String> partIDTableColumn = new TableColumn("Part ID");
    @FXML
    public static TableColumn<InHouse, String> inventoryLevelTableColumn = new TableColumn("Inventory Level");
    @FXML
    public static TableColumn<InHouse, String> pricePerUnitTableColumn = new TableColumn("Price/Cost per Unit");

    @FXML
    public static TableView<Part> partsTableView = new TableView<>();

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


    public TableColumn getPartIDTableColumn() {
        return partIDTableColumn;
    }

    public void setPartIDTableColumn(TableColumn partIDTableColumn) {
        this.partIDTableColumn = partIDTableColumn;
    }

   public static void initialize() {
        productIDTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));
        productNameTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getStock())));
       productInventoryLevelTableView.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        productPricePerUnitTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrice())));
   //    inventoryLevelTableColumn.setCellFactory(new PropertyValueFactory<Inventory, String>("stock"));
   //    partNameTableColumn.setCellFactory(new PropertyValueFactory<Inventory, String>("name"));
       //pricePerUnitTableColumn.setCellFactory(new PropertyValueFactory<Inventory, String>("cost"));
       productTableView.getColumns().addAll(productIDTableColumn,productNameTableColumn,productInventoryLevelTableView,productPricePerUnitTableColumn);
       productTableView.setItems(c482Project.allProducts);
   }

}