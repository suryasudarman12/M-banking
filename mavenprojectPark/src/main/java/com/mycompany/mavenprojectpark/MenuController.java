package com.mycompany.mavenprojectpark;



import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;




public class MenuController implements Initializable {
    String namausername;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void open_info(MouseEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/Minfo.fxml"));

            Parent Main = loader.load();
            MInfoController minfoController = loader.getController();
            minfoController.getData(namausername);
            Scene scene = new Scene(Main);
            scene.getStylesheets().add("/styles/styles.css");
            Stage Primarystage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Primarystage.setScene(scene);
            Primarystage.show();
    }

    @FXML
    private void open_transfer(MouseEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/Transferuang.fxml"));
            Parent Main = loader.load();      
            TransferuangController transferController = loader.getController();
            transferController.getData(namausername);
            Scene scene = new Scene(Main);
            scene.getStylesheets().add("/styles/styles.css");
            Stage Primarystage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Primarystage.setScene(scene);
            Primarystage.show();
    }

    @FXML
    private void open_setor(MouseEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/Setor.fxml"));

            Parent Main = loader.load();
            SetorController setorController = loader.getController();
            setorController.getData(namausername);
            Scene scene = new Scene(Main);
            scene.getStylesheets().add("/styles/styles.css");
            Stage Primarystage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Primarystage.setScene(scene);
            Primarystage.show();
    }

    @FXML
    private void logout(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/Login.fxml"));

            Parent Main = loader.load();
            Scene scene = new Scene(Main);
            scene.getStylesheets().add("/styles/styles.css");
            Stage Primarystage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Primarystage.setScene(scene);
            Primarystage.show();
    }

    void getData(String user) {
     namausername = user;
    }
    
}
