/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.nutricao.dao;

import br.senai.nutricao.connection.ConnectionFactory;
import br.senai.nutricao.entity.EnderecoBean;
import br.senai.nutricao.entity.PacienteBean;
import br.senai.nutricao.entity.TipoPessoa;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jorge_filho
 */
public class PacienteDao {

    private Connection connection;
    
    public PacienteDao() {
              this.connection = new ConnectionFactory().getConnection();

    }

    //Metodo provisorio Teste insert Rafael Souza
    public void adicionaTeste(PacienteBean pacienteBean) {

        String sql = "insert into paciente "
                + "(nome, cpf, rg)"
                + " values (?,?,?)";
        
        PreparedStatement stmt = null;
        try {
            //Cria conexão
             // prepared statement para inserção
            stmt = connection.prepareStatement(sql);

            // seta os valores
            stmt.setString(1, pacienteBean.getNome());
            stmt.setString(2, pacienteBean.getCpf());
            stmt.setString(3, pacienteBean.getRg());
            //stmt.setString(4, pacienteBean.getSexo());

            // executa
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                //Fecha conexão e PreparedStatement
                stmt.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    //Teste consulta por cpf ou id Rafael Souza
    public PacienteBean getPacienteByCpf(PacienteBean paciente) {

        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            //Cria conexão
            connection = new ConnectionFactory().getConnection();
            stmt = connection.prepareStatement("select nome,cpf from paciente where cpf = ? or id = ?");

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

    public void adicionaPaciente(PacienteBean pacienteBean) {
     
        String sql = "insert into pessoa "
                + "(nome, cpf, idade, sexo, fk_tipopessoa, fk_endereco, telefoneFixo,"
                + " telefoneCel, email, dataNascimento, "
                + " login, senha)"
                + " values (?,?,?,?,?,?,?,?,?,?,?,?)";
        
      
        PreparedStatement stmt = null;
        try {
            // prepared statement para inserção
            stmt = connection.prepareStatement(sql);

            // seta os valores
                       
            EnderecoBean endereco = pacienteBean.getEndereco();
            endereco = adicionaEndereco(endereco);
            pacienteBean.setEndereco(endereco);
            
            
            TipoPessoa tipo = pacienteBean.getTipo();
            tipo = adicionaTipo(tipo);
            pacienteBean.setTipo(tipo);
            
               
            stmt.setString(1, pacienteBean.getNome());
            stmt.setString(2, pacienteBean.getCpf());
            stmt.setInt(3, pacienteBean.getIdade());
            stmt.setString(4, pacienteBean.getSexo());
            stmt.setInt(5, pacienteBean.getEndereco().getIdEndereco());
            stmt.setInt(6, pacienteBean.getTipo().getIdTipoPessoa());
            stmt.setString(7, pacienteBean.getTelefoneResidencial());
            stmt.setString(8, pacienteBean.getTelefoneCelular());
            stmt.setString(9, pacienteBean.getEmail());
            stmt.setDate(10, new Date(pacienteBean.getDataNascimento()
					.getTimeInMillis()));
            stmt.setString(11, pacienteBean.getLogin());
            stmt.setString(12, pacienteBean.getSenha());
            
            // executa
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    
       public synchronized TipoPessoa adicionaTipo(TipoPessoa tipo) {

        String sql = "insert into tipopessoa "
                + "(nome)"
                + " values (?)";
        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setString(1, tipo.getNome());

            // executa
            stmt.executeUpdate();

            if (stmt.getGeneratedKeys() != null) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()){
                    tipo.setIdTipoPessoa(rs.getInt(1));
                }
            }

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tipo;
    }
    
    
    public synchronized EnderecoBean adicionaEndereco(EnderecoBean endereco) {

        String sql = "insert into endereco "
                + "(rua, bairro, cidade, cep, numero)"
                + " values (?,?,?,?,?)";
        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setString(1, endereco.getRua());
            stmt.setString(2, endereco.getBairro());
            stmt.setString(3, endereco.getCidade());
            stmt.setString(4, endereco.getCep());
            stmt.setInt(5, endereco.getNumero());

            // executa
            stmt.executeUpdate();

            if (stmt.getGeneratedKeys() != null) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()){
                    endereco.setIdEndereco(rs.getInt(1));
                }
            }

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return endereco;
    }
    
    
    
    //Ainda não testado.... 
    public PacienteBean getPesquisaPaciente(PacienteBean pacienteBean) {
        
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            PacienteBean paciente = pacienteBean;
            stmt = connection.prepareStatement("select * from paciente where cpf=?");

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

    //Não testado...
    public List<PacienteBean> getListaPacientes() {
        
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            List<PacienteBean> listPacientes = new ArrayList();
            stmt = connection.prepareStatement("select * from paciente");
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
              //  paciente.setDataNascimento(rs.getDate("dtNascimento"));
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

    //Não testado...
    public void altera(PacienteBean paciente) {
        String sql = "update paciente set nome=?, cpf=?, rg=?, sexo=?, uf=?"
                + ", cep=?, bairro=?, telefoneResidencial=? "
                + ", telefoneAlternativo=?, telefoneCelular=?, email=?, dtNascimento=?"
                + ", numeroCadastro=?, nm_responsavel , where id=?";

        Connection connection = null;
        PreparedStatement stmt = null;
        
        try {
            stmt = connection.prepareStatement(sql);
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
          //  stmt.setDate(12, (Date) paciente.getDataNascimento());
            stmt.setInt(13, paciente.getNumeroCadastro());
            stmt.setString(14, paciente.getResponsavel());
            stmt.setInt(15, paciente.getId());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int remove(PacienteBean paciente) {
        int linha = 0;
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("delete from paciente where id=?");
            stmt.setLong(1, paciente.getId());
            linha = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return linha;
    }
     
}
