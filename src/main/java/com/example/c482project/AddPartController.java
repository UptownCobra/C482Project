package com.example.c482project;

import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.c482project.c482Project.*;
/**
 * @author Caleb
 */
public class AddPartController {

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
    public Label machineID_CompanyNameLabel;
    public Text errorTextMinMax;
    public Text errorTextInv;
    public Text errorTextName;
    public Text errorTextCost;
    public Text errorTextMachineID_CompanyName;

    public List<Text> errorTextList = new ArrayList<>();

    /**
     * Opens the Inventory management system window
     * @throws IOException needed for Scene changes
     */
    public void cancelButtonClicked() throws IOException {
        c482Project.changeScene("inventory_Management_system.fxml","Inventory Management System", 1284, 550);
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
     * If all fields contain the correct type we check which radio btn is selected to ensure we add the correct Part.
     * @throws IOException needed for Scene changes
     */
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
                        inventory.addPart(outsourced);

                } else {
                        InHouse inHouse = new InHouse(partID, name, price, stock, min, max, machineID);
                        inventory.addPart(inHouse);

                }


                partID += 1;
                changeScene("inventory_management_system.fxml", "Inventory Management System", 1284, 550);
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
     * Initializes errorTextList with all the errorText fields
     */
    public void initialize() {
        errorTextList.addAll(Arrays.asList(errorTextMinMax,errorTextInv,errorTextCost,errorTextName,errorTextMachineID_CompanyName));
    }

}
