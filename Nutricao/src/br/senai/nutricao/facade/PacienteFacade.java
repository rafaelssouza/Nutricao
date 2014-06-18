/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.senai.nutricao.facade;

import br.senai.nutricao.dao.PacienteDao;
import br.senai.nutricao.entity.PacienteBean;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael
 */
public class PacienteFacade {
    
     public void insertPaciente(PacienteBean paciente) {

            PacienteDao pacienteDAO = new PacienteDao();
            pacienteDAO.adicionaTeste(paciente);
            JOptionPane.showMessageDialog(null,"Salvo com sucesso");
            
    }
      public void updatePaciente(PacienteBean paciente) {

            PacienteDao pacienteDAO = new PacienteDao();
            pacienteDAO.altera(paciente);
            JOptionPane.showMessageDialog(null,"Alterado com sucesso");
            
    }
      
      public boolean removePaciente (PacienteBean paciente) {
          
             PacienteDao pacienteDAO = new PacienteDao();
           int linha = pacienteDAO.remove(paciente);
           // JOptionPane.showMessageDialog(null,"Paciente excluido com sucesso");
            
            if (linha == 1){
                return true;
            }else{
                return false;
            }
      }
     
     public PacienteBean getPesquisaByCpf(PacienteBean paciente){
        
        PacienteBean pb = new PacienteBean();
        PacienteDao dao = new PacienteDao();
        
        pb.setCpf(paciente.getCpf());
        pb.setId(paciente.getId());
        
        return dao.getPacienteByCpf(pb);
     }
     
}
