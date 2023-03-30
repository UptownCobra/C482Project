package com.example.c482project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Objects;

//TODO make a change scene class to remove redundant code
public class  c482Project extends Application {

    private static Stage primaryStage;
    public static Stage popup;
    public static Stage confirmPopup;
    public static Inventory inventory = new Inventory();
    public static int partID = 3;
    public static int productID = 3;
    public static boolean confirmDelete = false;

    @Override
    public void start(Stage stage) throws IOException {



        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("inventory_management_system.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1284, 517);
        stage.setTitle("Inventory System");
        stage.setScene(scene);
        stage.show();

        //Initialize Popup window
        FXMLLoader pupupFXMLLoader = new FXMLLoader(getClass().getResource("popupError.fxml"));
        Scene popupScene = new Scene(pupupFXMLLoader.load());
        popup = new Stage();
        popup.setScene(popupScene);
        popup.setTitle("Product Error");
        primaryStage = stage;

        //Initialize ConfirmDeletePopup
        FXMLLoader confirmDeletePopupFXMLLoader = new FXMLLoader(getClass().getResource("confirmDeletePopup.fxml"));
        Scene confirmDeletePopupScene = new Scene(confirmDeletePopupFXMLLoader.load());
        confirmPopup = new Stage();
        confirmPopup.setScene(confirmDeletePopupScene);
        confirmPopup.setTitle("Confirm Delete");


        //Initialize dummy data
        InHouse part1 = new InHouse(1, "Tire", 249.98,5, 0, 15, 2);
        Outsourced part2 = new Outsourced(2,"Wing", 12349.99, 3,0,13,"Boing");

        Product product1 = new Product(1, "Car", 12000.00,3,0,10);
        product1.addAssociatedPart(part1);
        product1.addAssociatedPart(part1);
        product1.addAssociatedPart(part1);
        product1.addAssociatedPart(part1);
        Product product2 = new Product(2, "Plane", 120000.00,74,0,100);
        product2.addAssociatedPart(part2);
        product2.addAssociatedPart(part2);


        inventory.addProduct(product1);
        inventory.addProduct(product2);
        inventory.addPart(part1);
        inventory.addPart(part2);



    }

    public static void changeScene(String fxml,String title, Integer width, Integer height) throws IOException {
       Parent pane = FXMLLoader.load(Objects.requireNonNull(c482Project.class.getResource(fxml)));
       primaryStage.getScene().setRoot(pane);
        primaryStage.setTitle(title);
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
    }

    public static void showPopup() {
        popup.show();
    }
    public static void main(String[] args) {
        launch();


    }




}