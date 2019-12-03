
package com.mycompany.mavenprojectpark;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
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


public class SetorController implements Initializable {
     String namauser;
     private Connection conn = null;
    @FXML
    private TextField jumlah;
    String od;
    int sans;
    String salsal;
    @FXML
    private Font x1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void getData(String namausername) throws SQLException {
         namauser = namausername;        
        String url = "jdbc:sqlite:Banking.db";
        conn = DriverManager.getConnection(url);

        Statement stmt = (Statement) conn.createStatement();

        ResultSet query = stmt.executeQuery("SELECT * FROM Bankuser where Username = '" + namausername + "' ");
          salsal = query.getString("Saldo");
         
        query.close();
        conn.close();
        stmt.close();
        
        
      

         
        
        
        
    }

    @FXML
    private void ubahcuy(MouseEvent event) throws SQLException {
        String dodo = jumlah.getText();
        int dod = Integer.parseInt(dodo);
        int sasa = Integer.parseInt(salsal);
        
        int has = sasa+dod;
        sasa = has;
        od = Integer.toString(has);
        String url = "jdbc:sqlite:Banking.db";
        conn = DriverManager.getConnection(url);

        Statement stmt = (Statement)conn.createStatement();
     String query = "UPDATE Bankuser Set Saldo= '" +od+ "' WHERE Username = '" + namauser + "' ";
        stmt.executeUpdate(query);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information!");
                alert.setHeaderText("Success");
                alert.setContentText("Saldo anda telah ditambahkan!");
                alert.showAndWait();
                
        
        conn.close();
        stmt.close();
       
        
        
    }

    @FXML
    private void back_btn(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/Menu.fxml"));

        Parent Main = loader.load();
        MenuController menurController = loader.getController();
        menurController.getData(namauser);
        Scene scene = new Scene(Main);
        scene.getStylesheets().add("/styles/styles.css");
        Stage Primarystage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Primarystage.setScene(scene);
        Primarystage.show();
    }

   
    
}
