package com.example.c482project;


import static com.example.c482project.c482Project.*;

/**
 * Manages the deletePopup
 * @author Caleb
 */
public class ConfirmDeletePopup {
    /**
     * If clicked confirmDelete set to true
     */
    public void onYesBtnClick() {
        confirmDelete = true;
        confirmPopup.hide();
    }

    /**
     * If clicked confirmDelete set to false
     */
    public void onNoBtnClick() {
        confirmDelete = false;
        confirmPopup.hide();
    }
}
