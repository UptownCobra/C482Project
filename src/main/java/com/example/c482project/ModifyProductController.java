package com.example.c482project;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static com.example.c482project.c482Project.inventory;
import static com.example.c482project.c482Project.productID;

//TODO•   The user should not delete a product that has a part associated with it.

public class ModifyProductController {
    public TextField productSearchTextField;
    public TextField idTextField;
    public TextField nameTextField;
    public TextField invTextField;
    public TextField price_costTextField;
    public TextField maxTextField;
    public TextField minTextField;
    public Text nameErrorText;
    public Text invErrorText;
    public Text priceErrorText;
    public Text minMaxErrorText;

    public List<Text> errorTextList = new ArrayList<>();

    @FXML
    TableView<Part> partsTableView = new TableView<>();
    @FXML
    TableColumn<Part, String> partNameTableColumn = new TableColumn<>();
    @FXML
    TableColumn<Part, Integer> partIDTableColumn = new TableColumn<>();
    @FXML
    TableColumn<Part, Integer> partInventoryLevelTableColumn = new TableColumn<>();
    @FXML
    TableColumn<Part, Double> partPricePerUnitTableColumn = new TableColumn<>();

    @FXML
    TableView<Part> associatedPartTableView = new TableView<>();
    @FXML
    TableColumn<Part, String> associatedPartNameTableColumn = new TableColumn<>();
    @FXML
    TableColumn<Part, Integer> associatedPartIDTableColumn = new TableColumn<>();
    @FXML
    TableColumn<Part, Integer> associatedPartInventoryLevelTableColumn = new TableColumn<>();
    @FXML
    TableColumn<Part, Double> associatedPartPricePerUnitTableColumn = new TableColumn<>();
    public Button addBtn;
    public Button removeAssociatedPartBtn;
    public Button saveBtn;
    public Button cancelBtn;
    private FilteredList<Part> partSearchFilteredList = new FilteredList<>(inventory.getAllParts());
    private static Product modifyProduct;


    public void addBtnClick() {
        Part part = partsTableView.getFocusModel().getFocusedItem();
        modifyProduct.addAssociatedPart(part);
    }

    public void removeAssociatedPartBtnClick() {
        modifyProduct.deleteAssociatedPart(associatedPartTableView.getFocusModel().getFocusedItem());

    }
    public void saveBtnClick() throws IOException {
        InputVerification.resetErrorText(errorTextList);
        if (InputVerification.isTextFieldInputValid(nameTextField,invTextField,price_costTextField,maxTextField,minTextField,nameErrorText,invErrorText,priceErrorText,minMaxErrorText)) {

            String name = nameTextField.getText();
            double price = Double.parseDouble(price_costTextField.getText());
            int min = Integer.parseInt(minTextField.getText());
            int max = Integer.parseInt(maxTextField.getText());
            int inv = Integer.parseInt(invTextField.getText());

            if (InputVerification.isMinLessMax(min, max, minMaxErrorText) & InputVerification.isStockBetween(min, max, inv, invErrorText)) {
                modifyProduct.setName(name);
                modifyProduct.setPrice(price);
                modifyProduct.setMin(min);
                modifyProduct.setMax(max);
                modifyProduct.setStock(inv);
                inventory.updateProduct(inventoryManagementSystemController.getModifyProductIndex(),modifyProduct);
                c482Project.changeScene("inventory_management_system.fxml", "Inventory Management System", 1300, 550);
            }


        }
    }
    public void cancelBtnClick() throws IOException {
        c482Project.changeScene("inventory_management_system.fxml", "Inventory Management System", 1300,550);
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

    public static void setModifyProduct(Product product) {
        modifyProduct = product;
    }
    public void initialize() {
        errorTextList.addAll(Arrays.asList(nameErrorText,invErrorText,priceErrorText,minMaxErrorText));
        idTextField.setText(String.valueOf(modifyProduct.getId()));
        nameTextField.setText(modifyProduct .getName());
        invTextField.setText(String.valueOf(modifyProduct.getStock()));
        price_costTextField.setText(String.valueOf(modifyProduct.getPrice()));
        maxTextField.setText(String.valueOf(modifyProduct.getMax()));
        minTextField.setText(String.valueOf(modifyProduct.getMin()));

        // Init top table
        partsTableView.setItems(partSearchFilteredList);
        partIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLevelTableColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPricePerUnitTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        // Init bottom table
        associatedPartTableView.setItems(modifyProduct.getAllAssociatedParts());
        associatedPartIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInventoryLevelTableColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPricePerUnitTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        //init Search bar
        productSearchTextField.textProperty().addListener((observable, oldValue, newValue) ->
                partSearchFilteredList.setPredicate(createPartPredicate(newValue)));
    }
}
