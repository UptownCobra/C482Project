package com.example.c482project;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Objects;

import static com.example.c482project.inventoryManagementSystemController.*;

public class  c482Project extends Application {

    private static Stage primaryStage;
    public static Inventory inventory = new Inventory();
    public static ObservableList<Product> allProducts = inventory.getAllProducts();
    @Override
    public void start(Stage stage) throws IOException {



        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("inventory_management_system.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1284, 517);
        stage.setTitle("Inventory System");
        stage.setScene(scene);
        stage.show();
        primaryStage = stage;
        Product product1 = new Product(123, "test", 12.99,3,0,10);
        Product product2 = new Product(124, "check", 12.99,3,0,10);

        InHouse part1 = new InHouse(1, "test1", 12.99,5, 0, 5, 2);
        Outsourced part2 = new Outsourced(2,"outsourced", 12.99, 3,0,13,"Test Company");

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
    public static void main(String[] args) {
        launch();


    }




}