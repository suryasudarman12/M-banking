package com.mycompany.mavenprojectpark;

import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseLogin extends DBQuery {

    public DatabaseLogin() {
        this.connect();
    }

    @Override
    public void connect() {
        try {
           
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DatabaseLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
             
            
            conn = DriverManager.getConnection("jdbc:sqlite:Banking.db");
            st = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void queryUp(String query) {
        try {
            ps = conn.prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet queryResult(String query) {
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
        } catch (SQLException E) {
            JOptionPane.showMessageDialog(null, "ERROR");
        }
        return rs;
    }

    public ResultSet logquery(String Username, String Password) {
        try {
            String query = "SELECT * from Bankuser WHERE Username=? AND Password=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, Username);
            ps.setString(2, Password);
            rs = ps.executeQuery();

        } catch (SQLException e) {
            
        }
        return rs;
    }

    public ResultSet Profilequery(String id) {
        try {
            String query = "SELECT * from Bankuser WHERE Pinuser =?";
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Pengguna tidak ditemukan");
        }
        return rs;
    }
}
