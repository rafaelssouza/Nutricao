/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.nutricao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jorge_filho
 */
public class ConnectionFactory {
        
    
    public Connection getConnection() {
        
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost/test", "root", "mysql");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
