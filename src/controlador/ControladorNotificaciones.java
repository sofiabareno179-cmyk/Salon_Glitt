/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import java.awt.event.ActionEvent;
import modelo.Notificacion;
/**
 *
 * @author LENOVO
 */
public class ControladorNotificaciones {
    
    public void controlarAccion(ActionEvent evento, Notificacion unaNotificacion){
        String accion = evento.getActionCommand();
        switch(accion){
            case "Insertar":
                unaNotificacion.insertar();
            break;
            case "Modificar":
                unaNotificacion.modificar();
            break;
            case "Eliminar":
                unaNotificacion.eliminar();
            break;
        }
    }
}
