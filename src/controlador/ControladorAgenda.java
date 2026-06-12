/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import java.awt.event.ActionEvent;
import modelo.Agenda;
/**
 *
 * @author LENOVO
 */
public class ControladorAgenda {
    
        public void controlarAccion(ActionEvent evento, Agenda unAgenda){
        String accion = evento.getActionCommand();
        switch(accion){
            case "Insertar":
                unAgenda.insertar();
            break;
            case "Modificar":
                unAgenda.modificar();
            break;
            case "Eliminar":
                unAgenda.eliminar();
            break;
        }
    }
}
