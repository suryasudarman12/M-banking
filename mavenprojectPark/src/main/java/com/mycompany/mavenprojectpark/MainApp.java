package com.mycompany.mavenprojectpark;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/styles.css");
        
        stage.setTitle("M-BANK");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
        
    }
    
}
