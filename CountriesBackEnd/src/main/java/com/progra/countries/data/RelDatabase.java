package com.progra.countries.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RelDatabase {
    Connection cnx;
    public RelDatabase(){
        cnx=this.getConnection();            
    }
    public Connection getConnection(){
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String server = "localhost";
            String port = "3306";
            String user = "root";
            String password = "root";
            String database = "Seguros";
            
            String URL_conexion="jdbc:mysql://"+ server+":"+port+"/"+database+"?user="+user+"&password="+
                    password+"&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            Class.forName(driver).newInstance();
            return DriverManager.getConnection(URL_conexion);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        } 
        return null;
    }
    
    public PreparedStatement prepareStatement(String statement) throws SQLException {
        return cnx.prepareStatement(statement, Statement.RETURN_GENERATED_KEYS);
    }
    
    public int executeUpdate(PreparedStatement statement) {
        try {
            statement.executeUpdate();
            return statement.getUpdateCount();
        } catch (SQLException ex) {
            return 0;
        }
    }
    
    public ResultSet executeQuery(PreparedStatement statement){
        try {
            return statement.executeQuery();
        } catch (SQLException ex) {
        }
        return null;
    }
}
