/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.nutricao.entity;

import java.util.Date;

/**
 *
 * @author Josue
 */
public class AgendamentoBean {
    
    private String nomePaciente;
    private String fonePaciente;
    private String emailPaciente;
    private Date dataConsulta;
    private String horaConsulta;
    private boolean consultaReconsulta;
    private String profissional;

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getFonePaciente() {
        return fonePaciente;
    }

    public void setFonePaciente(String fonePaciente) {
        this.fonePaciente = fonePaciente;
    }

    public String getEmailPaciente() {
        return emailPaciente;
    }

    public void setEmailPaciente(String emailPaciente) {
        this.emailPaciente = emailPaciente;
    }

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getHoraConsulta() {
        return horaConsulta;
    }

    public void setHoraConsulta(String horaConsulta) {
        this.horaConsulta = horaConsulta;
    }

    public boolean isConsultaReconsulta() {
        return consultaReconsulta;
    }

    public void setConsultaReconsulta(boolean consultaReconsulta) {
        this.consultaReconsulta = consultaReconsulta;
    }

    public String getProfissional() {
        return profissional;
    }

    public void setProfissional(String profissional) {
        this.profissional = profissional;
    }

    
    
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

