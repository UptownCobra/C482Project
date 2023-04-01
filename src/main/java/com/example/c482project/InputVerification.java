package com.example.c482project;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.util.List;

/**
 * Class provides functions to verify data in text input fields
 * @author Caleb
 */
public class InputVerification {
    /**
     * Checks to see if min is less than max. If min is > max errorText is populated with error text. If min < max return false.
     * @param min int min value from textField
     * @param max int max value from textField
     * @param errorText Text error text to populate with warning error text
     * @return true if min is less than Max, false if max is less than min
     */
    public static boolean isMinLessMax(int min, int max, Text errorText) {
        if (min > max) {
            errorText.setText("Min value must be less that Max value");
            return false;
        } else {
            return true;
        }
    }

    /**
     * If stock is between min & max return True. If stock is not between min & max then errorText is populated with
     * an error and false is returned.
     * @param min int min value from textField
     * @param max int max value from textField
     * @param stock int stock from textField
     * @param errorText Text that will be populated with error
     * @return true if stock is between min & max. else returns false
     */
    public static boolean isStockBetween(int min, int max, int stock, Text errorText) {
        if (stock >= min && stock <= max) {
            return true;
        } else {
            errorText.setText("Stock must be between Min and Max");
            return false;
        }
    }

    /**
     * Resets all Text within errorTextList to ""
     * @param errorTextList list of Text that need to be cleared
     */
    public static void resetErrorText(List<Text> errorTextList) {
        for (Text text : errorTextList) {
            text.setText("");
        }
    }

    /**
     * Checks the input for the text fields and ensures all fields are filled with the correct input type
     * @param name TextField
     * @param inv TextField
     * @param price TextField
     * @param max TextField
     * @param min TextField
     * @param nameErrorText Text
     * @param invErrorText Text
     * @param priceErrorText Text
     * @param minMaxErrorText Text
     * @return true is all TextFields have the correct type. Returns false if any checks fail
     */
    public static boolean isTextFieldInputValid(TextField name, TextField inv, TextField price, TextField max, TextField min, Text nameErrorText, Text invErrorText, Text priceErrorText, Text minMaxErrorText) {
        boolean isValid = true;
        try {
            int i = Integer.parseInt(name.getText());
            nameErrorText.setText("Name Field cannot be Int");
            isValid = false;
        } catch (Exception e) {
            nameErrorText.setText("");
        }
        try {
            int i = Integer.parseInt(inv.getText());
            invErrorText.setText("");
        } catch (Exception e) {
            invErrorText.setText("Inv must be an Integer");
            isValid = false;
        }
        try {
            double i = Double.parseDouble(price.getText());
            priceErrorText.setText("");
        } catch (Exception e){
            priceErrorText.setText("Price/Cost must be a decimal");
            isValid = false;
        }
        try {
            int i = Integer.parseInt(max.getText());
            minMaxErrorText.setText("");
        } catch (Exception e) {
            minMaxErrorText.setText("Max must be an Integer");
            isValid = false;
        }
        try {
            int i = Integer.parseInt(min.getText());
            minMaxErrorText.setText("");
        } catch (Exception e) {
            minMaxErrorText.setText("Min must be an Integer");
            isValid = false;
        }

        return isValid;
    }

    /**
     * Checks if a textField is an int
     * @param tf textField to check
     * @param errorText Text to populate if false
     * @param fieldName String name of the textField being checked
     * @return true is tf is an int else return false
     */
    public static boolean isTextFieldInt(TextField tf, Text errorText, String fieldName) {
        try {
            int i = Integer.parseInt(tf.getText());
            errorText.setText("");
            return true;
        } catch (Exception e) {
            errorText.setText(fieldName + " must be an Integer");
            return false;
        }
    }

    /**
     * Checks if tf contains a String
     * @param tf textField to check
     * @param errorText Text to populate if false
     * @param fieldName String name of the textField being checked
     * @return true if tf is a String else false
     */
    public static boolean isTextFieldString(TextField tf, Text errorText, String fieldName) {
        try {
            int i = Integer.parseInt(tf.getText());
            errorText.setText(fieldName + " must be a String");
            return true;
        } catch (Exception e) {
            errorText.setText("");
            return false;
        }
    }

}
