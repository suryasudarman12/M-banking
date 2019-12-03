package com.mycompany.mavenprojectpark;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    
    @FXML
    private TextField tf_password;
    private DatabaseLogin db;
    @FXML
    private Font x1;
    @FXML
    private Font x2;
    @FXML
    private Font x3;
    @FXML
    private Font x4;
    @FXML
    private TextField tf_username;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = new DatabaseLogin();
        db.connect();
    }

    @FXML
    private void login(ActionEvent event) throws SQLException, IOException {
        String user = tf_username.getText();
        String pass = tf_password.getText();
        ResultSet rs = db.logquery(user, pass);
        if(rs.next()) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/Menu.fxml"));

            Parent Main = loader.load();
            MenuController menuController = loader.getController();
            menuController.getData(user);
            Scene scene = new Scene(Main);
            scene.getStylesheets().add("/styles/styles.css");
            Stage Primarystage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Primarystage.setScene(scene);
            Primarystage.show();
            
            rs.close();

        } else {
            if (tf_username.getText().trim().isEmpty() || tf_password.getText().trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Warning!");
                alert.setHeaderText("Failed");
                alert.setContentText("textfield tidak boleh kosong!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Warning!");
                alert.setHeaderText("Failed");
                alert.setContentText("Username atau Password salah!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void open_signup(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/Signup.fxml"));

            Parent Main = loader.load();
            Scene scene = new Scene(Main);
            scene.getStylesheets().add("/styles/styles.css");
            Stage Primarystage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Primarystage.setScene(scene);
            Primarystage.show();
    }
}
