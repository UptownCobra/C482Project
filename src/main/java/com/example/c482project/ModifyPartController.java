package com.example.c482project;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.c482project.c482Project.*;
/**
 * @author Caleb
 */
public class ModifyPartController {

    public RadioButton outsourcedRadioBtn;
    public RadioButton inHouseRadioBtn;
    public TextField idTextField;
    public TextField nameTextField;
    public TextField invTextField;
    public TextField price_costTextField;
    public TextField maxTextField;
    public TextField minTextField;
    public TextField machineID_CompanyNameTextField;
    public Button saveBtn;
    public Text errorTextMinMax;
    public Text errorTextInv;
    public Text errorTextName;
    public Text errorTextCost;
    public Text errorTextMachineID_CompanyName;
    @FXML
    Label machineID_CompanyNameLabel;
    @FXML

    private static Part modifyPart;
    public List<Text> errorTextList = new ArrayList<>();

    /**
     * Opens the Inventory management system window
     * @throws IOException needed for Scene changes
     */
    public void cancelButtonClicked() throws IOException {
        changeScene("inventory_Management_system.fxml","Inventory Management System", 1284, 517);
    }

    /**
     * Updates the text for machineID_CompanyNameTextField to Company Name
     */
    public void onOutsourcedRadioBtnClicked() {
        machineID_CompanyNameLabel.setText("Company Name");
    }

    /**
     * Verifies that all fields contain the correct type. If a field does not have correct type shows error text
     * Verifies that min is less than max and inv is between min and max
     * If all fields contain the correct type we check which radio btn is selected to esure we add the correct Part.
     * @throws IOException needed for Scene changes
     */
    @FXML
    public void onSaveBtnClick() throws IOException {

    String comName = "";
    int machineID = -1;

    if (outsourcedRadioBtn.isSelected()) {
        if (InputVerification.isTextFieldString(machineID_CompanyNameTextField, errorTextMachineID_CompanyName, "Company Name")) {
            comName = machineID_CompanyNameTextField.getText();
        }
    } else {
        if (InputVerification.isTextFieldInt(machineID_CompanyNameTextField, errorTextMachineID_CompanyName, "Machine ID")) {
            machineID = Integer.parseInt(machineID_CompanyNameTextField.getText());
        }
    }

    if (InputVerification.isTextFieldInputValid(nameTextField, invTextField, price_costTextField, maxTextField, minTextField, errorTextName, errorTextInv, errorTextCost, errorTextMinMax)) {
        String name = nameTextField.getText();
        int stock = Integer.parseInt(invTextField.getText());
        double price = Double.parseDouble(price_costTextField.getText());
        int max = Integer.parseInt(maxTextField.getText());
        int min = Integer.parseInt(minTextField.getText());

        InputVerification.resetErrorText(errorTextList);

        if (InputVerification.isMinLessMax(min, max, errorTextMinMax) & InputVerification.isStockBetween(min, max, stock, errorTextInv)) {
            if (outsourcedRadioBtn.isSelected()) {
                Outsourced outsourced = new Outsourced(partID, name, price, stock, min, max, comName);
                inventory.updatePart(inventoryManagementSystemController.getModifyPartIndex(), outsourced);
            } else {
                InHouse inHouse = new InHouse(partID, name, price, stock, min, max, machineID);
                inventory.updatePart(inventoryManagementSystemController.getModifyPartIndex(), inHouse);
            }

            changeScene("inventory_management_system.fxml", "Inventory Management System", 1284, 517);
        }
    }
}

    /**
     * Sets the machineID_CompanyNameLabel text to Machine ID
     */
    public void onInHouseRadioBtnClick() {
        machineID_CompanyNameLabel.setText("Machine ID");
    }

    /**
     * Sets modifyPart
     * @param part new Part
     */
    public static void setModifyPart(Part part){
        modifyPart = part;
    }

    /**
     * Adds all ErrorText to errorTextList so all errors can be cleared later
     * Populates all TextFields with corresponding modifyPart Attributes.
     * Checks if modifyPart is Outsourced or In-House to ensure we use the correct function
     */
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
            machineID_CompanyNameLabel.setText("Company Name");
            machineID_CompanyNameTextField.setText(((Outsourced) modifyPart).companyName);
        } else {
            inHouseRadioBtn.setSelected(true);
            machineID_CompanyNameLabel.setText("Machine ID");
            machineID_CompanyNameTextField.setText(String.valueOf(((InHouse) modifyPart).machineId));
        }
        errorTextList.addAll(Arrays.asList(errorTextMinMax,errorTextInv,errorTextCost,errorTextName,errorTextMachineID_CompanyName));
    }

}
