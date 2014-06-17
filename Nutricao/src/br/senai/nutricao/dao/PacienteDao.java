/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.nutricao.dao;

import br.senai.nutricao.connection.ConnectionFactory;
import br.senai.nutricao.entity.PacienteBean;
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
public class PacienteDao {

    private Connection connection;

    public PacienteDao() {
        this.connection = new ConnectionFactory().getConnection();

    }
    //Teste insert Rafael Souza
    public void adicionaTeste(PacienteBean pacienteBean) {

        String sql = "insert into paciente "
                + "(nome, cpf, rg, sexo)"
                + " values (?,?,?,?)";
        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql);

            // seta os valores
            
            stmt.setString(1, pacienteBean.getNome());
            stmt.setString(2, pacienteBean.getCpf());
            stmt.setString(3, pacienteBean.getRg());
            stmt.setString(4, pacienteBean.getSexo());
            
            // executa
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        //Teste consulta por cpf ou id Rafael Souza
        public PacienteBean getPacienteByCpf(PacienteBean paciente) {
        
            try {
           
            PreparedStatement stmt = this.connection.
                    prepareStatement("select nome,cpf from paciente where cpf = ? or id = ?");
               
            stmt.setString(1, paciente.getCpf());
            stmt.setInt(2, paciente.getId());
            ResultSet rs = stmt.executeQuery();
                       
            while (rs.next()) {
                // criando o objeto Contato
                paciente = new PacienteBean();
                paciente.setNome(rs.getString("nome"));
                paciente.setCpf(rs.getString("cpf"));
               
                }

            rs.close();
            stmt.close();
            return paciente;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    
    
    
    
    public void adiciona(PacienteBean pacienteBean) {

        String sql = "insert into paciente "
                + "(id, nome, cpf, rg, sexo, endereco, uf, cep, bairro, telefoneResidencial,"
                + " telefoneAlternativo, celular, email, dataNascimento, "
                + " login, senha, numeroCadastro, nm_responsavel)"
                + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql);

            // seta os valores
            stmt.setInt(1, pacienteBean.getId());
            stmt.setString(2, pacienteBean.getNome());
            stmt.setString(3, pacienteBean.getCpf());
            stmt.setString(4, pacienteBean.getRg());
            stmt.setString(5, pacienteBean.getSexo());
            stmt.setString(6, pacienteBean.getEndereco());
            stmt.setString(7, pacienteBean.getUf());
            stmt.setString(8, pacienteBean.getCep());
            stmt.setString(9, pacienteBean.getBairro());
            stmt.setString(10, pacienteBean.getTelefoneResidencial());
            stmt.setString(11, pacienteBean.getTelefoneAlternativo());
            stmt.setString(12, pacienteBean.getTelefoneCelular());
            stmt.setString(13, pacienteBean.getEmail());
            stmt.setDate(14, (Date) pacienteBean.getDataNascimento());
            stmt.setString(15, pacienteBean.getLogin());
            stmt.setString(16, pacienteBean.getSenha());
            stmt.setInt(17, pacienteBean.getNumeroCadastro());
            stmt.setString(18, pacienteBean.getResponsavel());

            // executa
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public PacienteBean getPesquisaPaciente(PacienteBean pacienteBean) {
        try {
            PacienteBean paciente = pacienteBean;
            PreparedStatement stmt = this.connection.
                    prepareStatement("select * from paciente where cpf=?");

            stmt.setString(1, paciente.getCpf());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // criando o objeto Contato
                PacienteBean resultPesquisa = new PacienteBean();
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
                resultPesquisa.setResponsavel(rs.getString("nm_responsavel"));
            }

            rs.close();
            stmt.close();
            return paciente;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PacienteBean> getListaPacientes() {
        try {
            List<PacienteBean> listPacientes = new ArrayList<PacienteBean>();
            PreparedStatement stmt = this.connection.
                    prepareStatement("select * from paciente");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // criando o objeto Contato
                PacienteBean paciente = new PacienteBean();
                paciente.setId(rs.getInt("id"));
                paciente.setNome(rs.getString("nome"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setRg(rs.getString("rg"));
                paciente.setSexo(rs.getString("sexo"));
                paciente.setUf(rs.getString("uf"));
                paciente.setCep(rs.getString("cep"));
                paciente.setBairro(rs.getString("bairro"));
                paciente.setTelefoneResidencial(rs.getString("telefoneResidencial"));
                paciente.setTelefoneAlternativo(rs.getString("telefoneAlternativo"));
                paciente.setTelefoneCelular(rs.getString("telefoneCelular"));
                paciente.setEmail(rs.getString("email"));
                paciente.setDataNascimento(rs.getDate("dtNascimento"));
                paciente.setNumeroCadastro(rs.getInt("numeroCadastro"));
                paciente.setResponsavel(rs.getString("nm_responsavel"));

                // adicionando o objeto à lista
                listPacientes.add(paciente);
            }
            rs.close();
            stmt.close();
            return listPacientes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void altera(PacienteBean paciente) {
        String sql = "update paciente set nome=?, cpf=?, rg=?, sexo=?, uf=?"
                + ", cep=?, bairro=?, telefoneResidencial=? "
                + ", telefoneAlternativo=?, telefoneCelular=?, email=?, dtNascimento=?"
                + ", numeroCadastro=?, nm_responsavel , where id=?";

        try {
            PreparedStatement stmt = connection
                    .prepareStatement(sql);
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf());
            stmt.setString(3, paciente.getRg());
            stmt.setString(4, paciente.getSexo());
            stmt.setString(5, paciente.getUf());
            stmt.setString(6, paciente.getCep());
            stmt.setString(7, paciente.getBairro());
            stmt.setString(8, paciente.getTelefoneResidencial());
            stmt.setString(9, paciente.getTelefoneAlternativo());
            stmt.setString(10, paciente.getTelefoneCelular());
            stmt.setString(11, paciente.getEmail());
            stmt.setDate(12, (Date) paciente.getDataNascimento());
            stmt.setInt(13, paciente.getNumeroCadastro());
            stmt.setString(14, paciente.getResponsavel());
            stmt.setInt(15, paciente.getId());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(PacienteBean paciente) {
        try {
            PreparedStatement stmt = connection
                    .prepareStatement("delete from paciente where id=?");
            stmt.setLong(1, paciente.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
