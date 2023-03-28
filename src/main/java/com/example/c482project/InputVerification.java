package com.example.c482project;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//TODO finish class
//TODO determine if this class should be initialized or just have values passed.
public abstract class InputVerification {
    private TextField nameTextField;
    private TextField invTextField;
    private TextField priceTextField;
    private TextField minTextField;
    private TextField maxTextField;
    private Text nameText;
    private Text invText;
    private Text priceText;
    private Text minText;
    private Text maxText;
    private List<Text> errorTextList = new ArrayList<>();

    public InputVerification(TextField nameTF, TextField invTF, TextField priceTF, TextField minTF, TextField maxTF, Text nameT, Text invT, Text priceT, Text minT, Text maxT) {
        this.nameTextField = nameTF;
        this.invTextField = invTF;
        this.priceTextField = priceTF;
        this.minTextField = minTF;
        this.maxTextField = maxTF;
        this.nameText = nameT;
        this.invText = invT;
        this.priceText = priceT;
        this.minText = minT;
        this.maxText = maxT;
        this.errorTextList.addAll(Arrays.asList(this.nameText,this.invText,this.priceText,this.minText,this.maxText));
    }

  /*  public static boolean isMinLessMax() {
     if (getInt(minTextField) > max) {
         errorText.setText("Min value must be less that Max value");
         return false;
     } else {
         return true;
     }
    }*/
    public static boolean isStockBetween(int min,int max, int stock, Text errorText) {
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
    public static boolean isTextFieldInputValid(TextField name, TextField inv, TextField price, TextField max, TextField min) {
        boolean isValid = true;
        try {
            int i = Integer.parseInt(name.getText());
            name.setText("Name Field cannot be Int");
            isValid = false;
        } catch (Exception e) {
            name.clear();

        }
        return isValid;
    }

    private static int getInt(TextField tf) {
        return Integer.parseInt(tf.getText());
    }
}
