package com.example.c482project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AddProductController {
    public TextField productSearchTextField;
    public TextField idTextField;
    public TextField nameTextField;
    public TextField invTextField;
    public TextField price_costTextField;
    public TextField maxTextField;
    public TextField minTextField;
    public TableView partsTableView;
    public TableColumn partIDTableColumn;
    public TableColumn partNameTableColumn;
    public TableColumn partInventoryLevelTableColumn;
    public TableColumn partPricePerUnitTableColumn;
    public TableView associatedPartTableView;
    public TableColumn associatedPartIDTableColumn;
    public TableColumn associatedPartNameTableColumn;
    public TableColumn associatedPartInventoryLevelTableColumn;
    public TableColumn associatedPartPricePerUnitTableColumn;
    public Button addBtn;
    public Button removeAssociatedPartBtn;
    public Button saveBtn;
    public Button cancelBtn;
}
