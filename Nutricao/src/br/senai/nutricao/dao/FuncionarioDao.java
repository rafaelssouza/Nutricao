/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.nutricao.dao;

import br.senai.nutricao.connection.ConnectionFactory;
import br.senai.nutricao.entity.FuncionarioBean;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jorge_filho
 */
public class FuncionarioDao {

    private Connection connection;

    public FuncionarioDao() {
        this.connection = new ConnectionFactory().getConnection();

    }

    public void adiciona(FuncionarioBean funcionarioBean) {

        String sql = "insert into funcionario "
                + "(id, nome, cpf, rg, sexo, endereco, uf, cep, bairro, telefoneResidencial,"
                + " telefoneAlternativo, celular, email, dataNascimento, "
                + " login, senha, numeroCadastro, nm_cargo)"
                + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql);

            // seta os valores
            stmt.setInt(1, funcionarioBean.getId());
            stmt.setString(2, funcionarioBean.getNome());
            stmt.setString(3, funcionarioBean.getCpf());
            stmt.setString(4, funcionarioBean.getRg());
            stmt.setString(5, funcionarioBean.getSexo());
            stmt.setString(6, funcionarioBean.getEndereco());
            stmt.setString(7, funcionarioBean.getUf());
            stmt.setString(8, funcionarioBean.getCep());
            stmt.setString(9, funcionarioBean.getBairro());
            stmt.setString(10, funcionarioBean.getTelefoneResidencial());
            stmt.setString(11, funcionarioBean.getTelefoneAlternativo());
            stmt.setString(12, funcionarioBean.getTelefoneCelular());
            stmt.setString(13, funcionarioBean.getEmail());
            stmt.setDate(14, (Date) funcionarioBean.getDataNascimento());
            stmt.setString(15, funcionarioBean.getLogin());
            stmt.setString(16, funcionarioBean.getSenha());
            stmt.setInt(16, funcionarioBean.getNumeroCadastro());
            stmt.setString(17, funcionarioBean.getCargo());

            // executa
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public FuncionarioBean getPesquisaFuncionario(FuncionarioBean funcionarioBean) {
        try {
            FuncionarioBean funcionario = funcionarioBean;
            PreparedStatement stmt = this.connection.
                    prepareStatement("select * from funcionario where cpf=?");

            stmt.setString(1, funcionario.getCpf());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // criando o objeto Contato
                FuncionarioBean resultPesquisa = new FuncionarioBean();
                resultPesquisa.setId(rs.getInt("id"));
                resultPesquisa.setNome(rs.getString("nome"));
                resultPesquisa.setCpf(rs.getString("cpf"));
                resultPesquisa.setRg(rs.getString("rg"));
                resultPesquisa.setSexo(rs.getString("sexo"));
                resultPesquisa.setUf(rs.getString("uf"));
                resultPesquisa.setCep(rs.getString("cep"));
                resultPesquisa.setBairro(rs.getString("bairro"));
                resultPesquisa.setTelefoneResidencial(rs.getString("telefoneResidencial"));
                resultPesquisa.setTelefoneAlternativo(rs.getString("telefoneAlternativo"));
                resultPesquisa.setTelefoneCelular(rs.getString("telefoneCelular"));
                resultPesquisa.setEmail(rs.getString("email"));
                resultPesquisa.setDataNascimento(rs.getDate("dtNascimento"));
                resultPesquisa.setNumeroCadastro(rs.getInt("numeroCadastro"));
                resultPesquisa.setCargo(rs.getString("nm_cargo"));
            }

            rs.close();
            stmt.close();
            return funcionario;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<FuncionarioBean> getListaFuncionarios() {
        try {
            List<FuncionarioBean> listFuncionario = new ArrayList<FuncionarioBean>();
            PreparedStatement stmt = this.connection.
                    prepareStatement("select * from funcionario");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // criando o objeto Contato
                FuncionarioBean funcionario = new FuncionarioBean();
                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setRg(rs.getString("rg"));
                funcionario.setSexo(rs.getString("sexo"));
                funcionario.setUf(rs.getString("uf"));
                funcionario.setCep(rs.getString("cep"));
                funcionario.setBairro(rs.getString("bairro"));
                funcionario.setTelefoneResidencial(rs.getString("telefoneResidencial"));
                funcionario.setTelefoneAlternativo(rs.getString("telefoneAlternativo"));
                funcionario.setTelefoneCelular(rs.getString("telefoneCelular"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setDataNascimento(rs.getDate("dtNascimento"));
                funcionario.setNumeroCadastro(rs.getInt("numeroCadastro"));
                funcionario.setCargo(rs.getString("nm_cargo"));

                // adicionando o objeto à lista
                listFuncionario.add(funcionario);
            }
            rs.close();
            stmt.close();
            return listFuncionario;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void altera(FuncionarioBean funcionario) {
        String sql = "update funcionario set nome=?, cpf=?, rg=?, sexo=?, uf=?"
                + ", cep=?, bairro=?, telefoneResidencial=? "
                + ", telefoneAlternativo=?, telefoneCelular=?, email=?, dtNascimento=?"
                + ", numeroCadastro=?, nm_cargo=? , where id=?";

        try {
            PreparedStatement stmt = connection
                    .prepareStatement(sql);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getRg());
            stmt.setString(4, funcionario.getSexo());
            stmt.setString(5, funcionario.getUf());
            stmt.setString(6, funcionario.getCep());
            stmt.setString(7, funcionario.getBairro());
            stmt.setString(8, funcionario.getTelefoneResidencial());
            stmt.setString(9, funcionario.getTelefoneAlternativo());
            stmt.setString(10, funcionario.getTelefoneCelular());
            stmt.setString(11, funcionario.getEmail());
            stmt.setDate(12, (Date) funcionario.getDataNascimento());
            stmt.setInt(13, funcionario.getNumeroCadastro());
            stmt.setString(14, funcionario.getCargo());
            stmt.setInt(15, funcionario.getId());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(FuncionarioBean funcionario) {
        try {
            PreparedStatement stmt = connection
                    .prepareStatement("delete from funcionario where id=?");
            stmt.setLong(1, funcionario.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
