package com.example.hellofx;
import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class HelloJavaFX extends Application {
    @Override
    public void start(Stage stage) {
        Label message = new Label("PAUL SICHONE");
        Button button = new Button("Start");
        Button resetButton = new Button("Reset");
        button.setOnAction(event ->
                message.setText("Great! You clicked the button.")

        );
        resetButton.setOnAction(event ->
                message.setText("Welcome Paul")

        );
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(message, button, resetButton);
        Scene scene = new Scene(layout, 500, 300);
        stage.setTitle("My First JavaFX Application_202511983");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}