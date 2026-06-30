/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import java.awt.event.ActionEvent;
import modelo.Recordatorios;
/**
 *
 * @author LENOVO
 */
public class ControladorRecordatorios {
    
     public void controlarAccion(ActionEvent evento, Recordatorios unRecordatorios){
        String accion = evento.getActionCommand();
        switch(accion){
            case "Insertar":
                unRecordatorios.insertar();
            break;
            case "Modificar":
                unRecordatorios.modificar();
            break;
            case "Eliminar":
                unRecordatorios.eliminar();
            break;
        }
    }
}
