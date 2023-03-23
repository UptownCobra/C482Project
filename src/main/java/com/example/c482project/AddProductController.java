package com.example.c482project;

import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.function.Predicate;

import static com.example.c482project.c482Project.inventory;

//TODO•   The user should not delete a product that has a part associated with it.
//TODO The application auto-generates a unique product ID. The product IDs can be, but do not need to be, contiguous.
//
// TODO The product ID text field must be disabled and cannot be edited or changed.
//
//TODO   The user should be able to enter a product name, inventory level or stock, a price, and maximum and minimum values.
//
//TODO   The user can search for parts (top table) by ID or name (partial or full name). If the part or parts are found, the application highlights a single part or filters multiple parts. If the part or parts are not found, the application displays an error message in the UI or in a dialog box.
//
//TODO   If the search field is set to empty, the table should be repopulated with all available parts.
//
//TODO   The top table should be identical to the Parts TableView in the Main form.
//
//TODO   The user can select a part from the top table. The user then clicks the Add button, and the part is copied to the bottom table. (This associates one or more parts with a product.)
//
//TODO   The Remove Associated Part button removes a selected part from the bottom table. (This dissociates or removes a part from a product.)
//
//TODO   After saving the data, the user is automatically redirected to the Main form.
//
//TODO   Canceling or exiting this form redirects users to the Main form.
public class AddProductController {
    public TextField productSearchTextField;
    public TextField idTextField;
    public TextField nameTextField;
    public TextField invTextField;
    public TextField price_costTextField;
    public TextField maxTextField;
    public TextField minTextField;
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
    TableView<Part> associatedPartsTableView = new TableView<>();
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
    private Product newProduct;


    public void addBtnClick() {
        Part part = partsTableView.getFocusModel().getFocusedItem();
       newProduct.addAssociatedPart(part);
       System.out.println(newProduct);
    }

    public void removeAssociatedPartBtnClick() {

    }
    public void saveBtnClick() {

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

    public void initialize() {
        newProduct = new Product(-1,null,-1,-1,-1,-1);
        // Init top table
        partsTableView.setItems(partSearchFilteredList);
        partIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLevelTableColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPricePerUnitTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        //TODO botom table not working
        // Init bottom table
        associatedPartsTableView.setItems(newProduct.getAllAssociatedParts());
        associatedPartIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInventoryLevelTableColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPricePerUnitTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        //init Search bar
        productSearchTextField.textProperty().addListener((observable, oldValue, newValue) ->
                partSearchFilteredList.setPredicate(createPartPredicate(newValue)));
    }
}
