package com.mycompany.mavenprojectpark;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
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

public class SignupController implements Initializable {

    private Connection conn = null;
    @FXML
    private TextField conf_password;

    @FXML
    private TextField isi_password;
    int p = 0;
    @FXML
    private Font x1;
    @FXML
    private Font x2;
    @FXML
    private Font x3;
    @FXML
    private TextField isi_username;
    @FXML
    private Font x4;
    @FXML
    private TextField isi_pin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void signup(MouseEvent event) throws SQLException {
        String username = isi_username.getText();
        String pin = isi_pin.getText();
        String passuser = isi_password.getText();
        String passconfirm = conf_password.getText();
        int saldo = 0;

        for (char c : username.toCharArray()) {
            if (Character.isDigit(c)) {
                p++;
            } else if (Character.isSpaceChar(c)) {
                p++;
            } else if (!Character.isLetter(c)) {
                p++;
            }
        }
        if (username.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning!");
            alert.setHeaderText("Failed");
            alert.setContentText("Masukkan username terlebih dahulu!");
            alert.showAndWait();
        } else if (p != 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning!");
            alert.setHeaderText("Failed");
            alert.setContentText("Username tidak boleh mengandung angka, simbol, atau spasi!");
            alert.showAndWait();
        } else if (passuser.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning!");
            alert.setHeaderText("Failed");
            alert.setContentText("Masukkan Password terlebih dahulu!");
            alert.showAndWait();
        } else if (passuser.length() < 8) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning!");
            alert.setHeaderText("Failed");
            alert.setContentText("Panjang password tidak sesuai!");
            alert.showAndWait();
        } else if (!passconfirm.equals(passuser)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning!");
            alert.setHeaderText("Failed");
            alert.setContentText("Konfirmasi password yang anda masukkan salah!");
            alert.showAndWait();

        } else if (isi_pin.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning!");
            alert.setHeaderText("Failed");
            alert.setContentText("No rekening tidak boleh kosong!!");
            alert.showAndWait();
        } else if (isi_pin.getText().trim().length() != 8) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning!");
            alert.setHeaderText("Failed");
            alert.setContentText("Panjang no rekening tidak sesuai!!");
            alert.showAndWait();
        } else {
            String url = "jdbc:sqlite:Banking.db";
            conn = DriverManager.getConnection(url);

            Statement stmt = (Statement) conn.createStatement();

            String query = "INSERT INTO Bankuser VALUES ('" + username + "','" + passuser + "','" + pin + "','" + saldo + "')";
            stmt.executeUpdate(query);

//            query = "CREATE TABLE IF NOT EXISTS '" + username + "'("
//                    + "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
//                    + "User_nama VARCHAR(10) NOT NULL, "
//                    + "Tanggal_update VARCHAR(20) NOT NULL, "
//                    + "Jam_update VARCHAR(20) NOT NULL, "
//                    + "Jumlah_Saldo VARCHAR(20) NOT NULL "
//                    + ")";
//
//            stmt.execute(query);

            conn.close();
            stmt.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information!");
            alert.setHeaderText("Success");
            alert.setContentText("Akun telah ditambahkan!");
            alert.showAndWait();
        }

    }

    @FXML
    private void go_login(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/Login.fxml"));

        Parent Main = loader.load();
        Scene scene = new Scene(Main);
        scene.getStylesheets().add("/styles/styles.css");
        Stage Primarystage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Primarystage.setScene(scene);
        Primarystage.show();
    }

}
