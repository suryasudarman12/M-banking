
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class TransferuangController implements Initializable {
    private Connection conn = null;
    @FXML
    private Font x1;
    @FXML
    private TextField jumlah;
    @FXML
    private Label biayaadmin;
    @FXML
    private TextField rek_tujuan;
    private DatabaseLogin db;   
    String namausername;
     String salsal;
     String od;
     String dol;
     String daldal;
     String namapenerima;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = new DatabaseLogin();
        db.connect();
    }    

    void getData(String namauser) throws SQLException {
        namausername = namauser;
       
          String url = "jdbc:sqlite:Banking.db";
        conn = DriverManager.getConnection(url);
        
        Statement stmt = (Statement) conn.createStatement();
        
        ResultSet query = stmt.executeQuery("SELECT * FROM Bankuser where Username = '" + namausername + "' ");
          salsal = query.getString("Saldo");
          
        conn.close();
        stmt.close();
        query.close();
        
    }


    @FXML
    private void back_btn(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/Menu.fxml"));

            Parent Main = loader.load();
            MenuController menuController = loader.getController();
            menuController.getData(namausername);
            Scene scene = new Scene(Main);
            scene.getStylesheets().add("/styles/styles.css");
            Stage Primarystage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Primarystage.setScene(scene);
            Primarystage.show();
    }

    @FXML
    private void go_transfer(MouseEvent event) throws SQLException, IOException {
        String id = rek_tujuan.getText();
        ResultSet pk = db.Profilequery(id);
        String dodo = jumlah.getText();       
        
        
        
        if(pk.next()) {
         String url = "jdbc:sqlite:Banking.db";
        conn = DriverManager.getConnection(url);        
        Statement stmt = (Statement) conn.createStatement();
        ResultSet query = stmt.executeQuery("SELECT * FROM Bankuser where Pinuser = '" + rek_tujuan.getText() + "' ");
       daldal = query.getString("Saldo");
       namapenerima = query.getString("Username");
       
       conn.close();
       stmt.close();
       query.close();
       pk.close();
       int dod = Integer.parseInt(dodo);
      
        int sasa = Integer.parseInt(salsal);
        int dada = Integer.parseInt(daldal);
        int tagihan = dod+5000;
//            System.out.println("bb"+tagihan); 
        if(sasa-tagihan<0){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Warning!");
                alert.setHeaderText("Failed!");
                alert.setContentText("Saldo tidak mencukupi!");
                alert.showAndWait();
        }
        else if(jumlah.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Warning!");
                alert.setHeaderText("Failed!");
                alert.setContentText("Jumlah tidak boleh kosong!");
                alert.showAndWait();
        }
        else{
             int has = sasa-tagihan;
        int sah = dada+dod;
        sasa = has;
        dol = Integer.toString(sah);
        od = Integer.toString(has); 
        
        url = "jdbc:sqlite:Banking.db";
        conn = DriverManager.getConnection(url);
        stmt = (Statement) conn.createStatement();
        

        
        String puery = "UPDATE Bankuser Set Saldo= '" +od+ "' WHERE Username = '" + namausername + "' ";
        stmt.executeUpdate(puery);
        
        stmt.close();
        conn.close();
        
        
        conn = DriverManager.getConnection(url);
        stmt = (Statement) conn.createStatement();
        
       
        String suery = "UPDATE Bankuser Set Saldo= '" +dol+ "' WHERE Username= '"+namapenerima +"' ";
        stmt.executeUpdate(suery);
        
            
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Warning!");
                alert.setHeaderText("Success");
                alert.setContentText("Anda telah berhasil melakukan Transfer!");
                alert.showAndWait();
        
        conn.close();
        stmt.close();     
        pk.close();
        }
       
            
    }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Warning!");
                alert.setHeaderText("Failed");
                alert.setContentText("Pengguna tidak ditemukan!");
                alert.showAndWait();
        }

    }
    
}
