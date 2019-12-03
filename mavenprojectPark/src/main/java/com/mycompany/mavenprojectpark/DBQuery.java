package com.mycompany.mavenprojectpark;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public abstract class DBQuery {
    Connection conn;
    PreparedStatement ps;
    Statement st;
    ResultSet rs, sr;
    
    public abstract void connect();
}
