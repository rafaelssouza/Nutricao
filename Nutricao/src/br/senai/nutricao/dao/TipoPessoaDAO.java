/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.nutricao.dao;

import br.senai.nutricao.connection.ConnectionFactory;
import br.senai.nutricao.entity.EnderecoBean;
import br.senai.nutricao.entity.TipoPessoa;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabriel_es
 */
public class TipoPessoaDAO {

    private final String INSERT_TIPO_PESSOA = "insert into tipopessoa (nome) values (?)";
    private final String GET_TIPO_PESSOA_BY_ID = "SELECT * FROM tipopessoa WHERE idTipoPessoa = ?";

    public TipoPessoa insert(TipoPessoa tipoPessoa) {

        PreparedStatement stmt = null;
        Connection connection = null;
        try {

            connection = new ConnectionFactory().getConnection();
            // prepared statement para inserção
            stmt = connection.prepareStatement(INSERT_TIPO_PESSOA, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, tipoPessoa.getNome());

            // executa
            stmt.executeUpdate();

            if (stmt.getGeneratedKeys() != null) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    tipoPessoa.setIdTipoPessoa(rs.getInt(1));
                }
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return tipoPessoa;

    }

    public TipoPessoa getById(int id) {
        PreparedStatement stmt = null;
        Connection connection = null;
        TipoPessoa tipoPessoa = null;
        try {

            connection = new ConnectionFactory().getConnection();
            // prepared statement para inserção
            stmt = connection.prepareStatement(GET_TIPO_PESSOA_BY_ID);
            stmt.setInt(1, id);
            
            // executa
            ResultSet rs = stmt.executeQuery();
            
            if ( rs.next() ){
                tipoPessoa = new TipoPessoa();
                tipoPessoa.setIdTipoPessoa(rs.getInt("idTipoPessoa"));
                tipoPessoa.setNome(rs.getString("nome"));
            }
            
            
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return tipoPessoa;

    }

}
