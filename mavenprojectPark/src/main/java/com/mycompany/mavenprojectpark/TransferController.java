package com.mycompany.mavenprojectpark;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TransferController implements Initializable {

    String namausername;
    private final Connection conn = null;
    @FXML
    private Font x1;
    @FXML
    private Button antar_rek;
    @FXML
    private Font x2;
    @FXML
    private Button antar_bank;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btn_back(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/Menu.fxml"));

        Parent Main = loader.load();
        Scene scene = new Scene(Main);
        scene.getStylesheets().add("/styles/styles.css");
        Stage Primarystage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Primarystage.setScene(scene);
        Primarystage.show();
    }

}
