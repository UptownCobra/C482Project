package com.example.c482project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;

import static com.example.c482project.c482Project.*;
//TODO •   The application will not crash when inappropriate user data is entered in the forms; instead, error messages should be generated.
public class ModifyPartController {

    public RadioButton outsourcedRadioBtn;
    public RadioButton inHouseRadioBtn;
    public Text errorText;
    public TextField idTextField;
    public TextField nameTextField;
    public TextField invTextField;
    public TextField price_costTextField;
    public TextField maxTextField;
    public TextField minTextField;
    public TextField machineID_CompanyNameTextField;
    public Button saveBtn;
    @FXML
    Label machineID_CompanyNameLabel;
    @FXML

    private static Part modifyPart;

    public void cancelButtonClicked() throws IOException {
        changeScene("inventory_Management_system.fxml","Inventory Management System", 1284, 517);
    }
    public void onOutsourcedRadioBtnClicked() {
        machineID_CompanyNameLabel.setText("Company ID");
    }

@FXML
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
                inventory.updatePart(inventoryManagementSystemController.getModifyPartIndex(),outsourced);
            } else {
                int machineID = Integer.parseInt(machineID_CompanyNameTextField.getText());
                InHouse inHouse = new InHouse(partID, name, price, stock, min, max, machineID);
                inventory.updatePart(inventoryManagementSystemController.getModifyPartIndex(),inHouse);
            }

            changeScene("inventory_management_system.fxml", "Inventory Management System", 1284, 517);
        }
    }

    public void onInHouseRadioBtnClick(ActionEvent actionEvent) {
        machineID_CompanyNameLabel.setText("Machine ID");
    }
    public static void setModifyPart(Part part){
        modifyPart = part;
    }
    public Part getModifyPart(){
        return modifyPart;
    }
    @FXML
    public void initialize() {
        idTextField.setText(String.valueOf(modifyPart.getId()));
        nameTextField.setText(modifyPart.getName());
        invTextField.setText(String.valueOf(modifyPart.getStock()));
        price_costTextField.setText(String.valueOf(modifyPart.getPrice()));
        maxTextField.setText(String.valueOf(modifyPart.getMax()));
        minTextField.setText(String.valueOf(modifyPart.getMin()));
        if (modifyPart instanceof Outsourced) {
            outsourcedRadioBtn.setSelected(true);
            inHouseRadioBtn.setDisable(true);
            machineID_CompanyNameLabel.setText("Company ID");
            machineID_CompanyNameTextField.setText(((Outsourced) modifyPart).companyName);
        } else {
            inHouseRadioBtn.setSelected(true);
            outsourcedRadioBtn.setDisable(true);
            machineID_CompanyNameLabel.setText("Machine ID");
            machineID_CompanyNameTextField.setText(String.valueOf(((InHouse) modifyPart).machineId));
        }

    }
}
