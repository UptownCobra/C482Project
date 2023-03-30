package com.example.c482project;


import static com.example.c482project.c482Project.*;

public class ConfirmDeletePopup {
    public void onYesBtnClick() {
        confirmDelete = true;
        confirmPopup.hide();
    }

    public void onNoBtnClick() {
        confirmDelete = false;
        confirmPopup.hide();
    }
}
