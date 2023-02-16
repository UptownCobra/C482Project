package com.example.c482project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;

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
    public TextField machineIDTextField;
    public Button saveBtn;

//TODO delete
    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        c482Project.changeScene("inventory_Management_system.fxml","Inventory Management System", 1284, 517);
    }
//TODO Delete
    public void testRadioBtnInHouse(ActionEvent actionEvent) {
        testTextArea.setText("This Totally Worked for In-House.");
    }
//TODO delete
    public void testRadioBtnOutsourced(ActionEvent actionEvent) {
        testTextArea.setText("This also works for Outsourced.");
    }

    public void onSaveBtnClick(ActionEvent actionEvent) {
        String testString = new String();
        testString += idTextField.getText() + "/n" + nameTextField.getText() + "/n";

        testTextArea.setText(testString);
    }
}
