package com.example.c482project;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//TODO finish class
//TODO determine if this class should be initialized or just have values passed.
public class InputVerification {

    public static boolean isMinLessMax(int min, int max, Text errorText) {
        if (min > max) {
            errorText.setText("Min value must be less that Max value");
            return false;
        } else {
            return true;
        }
    }

    public static boolean isStockBetween(int min, int max, int stock, Text errorText) {
        if (stock >= min && stock <= max) {
            return true;
        } else {
            errorText.setText("Stock must be between Min and Max");
            return false;
        }
    }

    public static void resetErrorText(List<Text> errorTextList) {
        for (Text text : errorTextList) {
            text.setText("");
        }
    }

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
