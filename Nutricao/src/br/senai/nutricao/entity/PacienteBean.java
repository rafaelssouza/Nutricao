/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.nutricao.entity;

/**
 *
 * @author jorge_filho
 */
public class PacienteBean extends PessoaBean {

    private String responsavel;

 
 public String getPesquisaPacienteNome(){
     
     return   nome;
 }
  public String getPesquisaPacienteCpf(){
     
     return  cpf;
 }
 
 
    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

}
