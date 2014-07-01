/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.senai.nutricao.view;

import br.senai.nutricao.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Rafael
 */
public class TestaConexao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
    Connection connection = new ConnectionFactory().getConnection();
    System.out.println("Conexão aberta!");
    connection.close();
      
        
// TODO code application logic here
    }
    
}
