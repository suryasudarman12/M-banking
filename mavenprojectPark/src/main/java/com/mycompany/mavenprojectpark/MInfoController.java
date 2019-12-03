package com.mycompany.mavenprojectpark;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MInfoController implements Initializable {

    String namausername;
    private Connection conn = null;
    @FXML
    private Label isi_saldo;
    private AnchorPane signuppage;
    @FXML
    private Font x1;
    @FXML
    private Label lbl_date;
    @FXML
    private Label lbl_time;
    @FXML
    private Font x2;

    void getData(String user) throws SQLException {
        namausername = user;

        String url = "jdbc:sqlite:Banking.db";
        conn = DriverManager.getConnection(url);

        ResultSet query;
        String saldo;
        Statement stmt = (Statement) conn.createStatement();
        query = stmt.executeQuery("SELECT * FROM Bankuser where Username = '" + namausername + "' ");
        saldo = query.getString("Saldo");
        
        
        stmt.close();
        conn.close();
        
        
        isi_saldo.setText(saldo);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        lbl_date.setText(dtf.format(localDate));

        DateTimeFormatter tm = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime localTime = LocalTime.now();
        lbl_time.setText(tm.format(localTime));

        

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
}
