package com.example.c482project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;

import static com.example.c482project.c482Project.*;
//TODO add redundancy to ensure fields are populated.
//TODO convert Price to 2 decimal places for appearance
public class AddPartController {

    public RadioButton outsourcedRadioBtn;
    public RadioButton inHouseRadioBtn;
    //TODO Delete
    public TextArea testTextArea;
    public TextField idTextField;
    public TextField nameTextField;
    public TextField invTextField;
    public TextField price_costTextField;
    public TextField maxTextField;
    public TextField minTextField;
    //TODO Figure Out how to make machineIDTextField interchangeable with the Company name TextField
    public TextField machineID_CompanyNameTextField;
    public Button saveBtn;
    public Label machineID_CompanyNameLable;

    //TODO delete
    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        c482Project.changeScene("inventory_Management_system.fxml","Inventory Management System", 1284, 517);
    }
    public void onOutsourcedRadioBtnClicked(ActionEvent actionEvent) {
            machineID_CompanyNameLable.setText("Company ID");
    }


    public void onSaveBtnClick(ActionEvent actionEvent) throws IOException {

        String name = nameTextField.getText();
        String sStock = invTextField.getText();
        int stock = Integer.parseInt(sStock);
        double price = Double.parseDouble(price_costTextField.getText());
        int max = Integer.parseInt(maxTextField.getText());
        int min = Integer.parseInt(minTextField.getText());

        //If outsourced selected add outsourced part else add inHouse part
        if (outsourcedRadioBtn.isSelected()) {
            String comName = machineID_CompanyNameLable.getText();
            Outsourced outsourced = new Outsourced(partID, name, price, stock, min, max, comName);
            inventory.addPart(outsourced);
        } else {
            int machineID = Integer.parseInt(machineID_CompanyNameTextField.getText());
            InHouse inHouse = new InHouse(partID,name, price, stock, min, max, machineID);
            inventory.addPart(inHouse);
        }
        partID += 1;
        changeScene("inventory_management_system.fxml", "Inventory Management System", 1284,517 );
    }

    public void onInHouseRadioBtnClick(ActionEvent actionEvent) {
        machineID_CompanyNameLable.setText("Machine ID");
    }
}
