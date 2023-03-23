package com.example.c482project;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;

import static com.example.c482project.c482Project.*;
//TODO add redundancy to ensure fields are populated.
//TODO convert Price to 2 decimal places for appearance
//TODO combine addParts and modifyParts to reduce redundancy of code. Or make a separate class to include reused functions.
public class AddPartController {

    public RadioButton outsourcedRadioBtn;
    public RadioButton inHouseRadioBtn;
    public Text errorText;
    public TextField idTextField;
    public TextField nameTextField;
    public TextField invTextField;
    public TextField price_costTextField;
    public TextField maxTextField;
    public TextField minTextField;
    //TODO Figure Out how to make machineIDTextField interchangeable with the Company name TextField
    public TextField machineID_CompanyNameTextField;
    public Button saveBtn;
    public Label machineID_CompanyNameLabel;

    public void cancelButtonClicked() throws IOException {
        c482Project.changeScene("inventory_Management_system.fxml","Inventory Management System", 1284, 517);
    }
    public void onOutsourcedRadioBtnClicked(ActionEvent actionEvent) {
            machineID_CompanyNameLabel.setText("Company ID");
    }


    public void onSaveBtnClick(ActionEvent actionEvent) throws IOException {

        String name = nameTextField.getText();
        int stock = Integer.parseInt(invTextField.getText());
        double price = Double.parseDouble(price_costTextField.getText());
        int max = Integer.parseInt(maxTextField.getText());
        int min = Integer.parseInt(minTextField.getText());
        errorText.setText("");
        // check to see if stock is within min max bounds
        if (stock < min || stock > max) {
            errorText.setText("Please enter a an Inv number between Min and Max");
        } else if (min > max) {
            errorText.setText("The Minimum value is higher than the Maximum value");
        } else if (max < min) {
            errorText.setText("The Maximum value is lower thant the Minimum value");
        }

        else {

            //If outsourced selected add outsourced part else add inHouse part
            if (outsourcedRadioBtn.isSelected()) {
                String comName = machineID_CompanyNameLabel.getText();
                Outsourced outsourced = new Outsourced(partID, name, price, stock, min, max, comName);
                inventory.addPart(outsourced);
            } else {
                int machineID = Integer.parseInt(machineID_CompanyNameTextField.getText());
                InHouse inHouse = new InHouse(partID, name, price, stock, min, max, machineID);
                inventory.addPart(inHouse);
            }

            partID += 1;
            changeScene("inventory_management_system.fxml", "Inventory Management System", 1284, 517);
        }
    }

    public void onInHouseRadioBtnClick(ActionEvent actionEvent) {
        machineID_CompanyNameLabel.setText("Machine ID");
    }
}
