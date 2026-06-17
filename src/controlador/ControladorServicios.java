/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import java.awt.event.ActionEvent;
import modelo.Servicios;
/**
 *
 * @author LENOVO
 */
public class ControladorServicios {
    
    public void controlarAccion(ActionEvent evento, Servicios unServicio){
        String accion = evento.getActionCommand();
        switch(accion){
            case "Insertar":
                unServicio.insertar();
            break;
            case "Modificar":
                unServicio.modificar();
            break;
            case "Eliminar":
                unServicio.eliminar();
            break;
        }
    }
}
