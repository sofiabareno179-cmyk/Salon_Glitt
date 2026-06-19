/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import modelo.Bloqueos;

/**
 *
 * @author USUARIO
 */
public class ControladorBloqueos {
    
        public void controlarAccion(ActionEvent evento, Bloqueos unBloqueo){
        String accion = evento.getActionCommand();
        switch(accion){
            case "Insertar":
                unBloqueo.insertar();
            break;
            case "Modificar":
                unBloqueo.modificar();
            break;
            case "Eliminar":
                unBloqueo.eliminar();
            break;
        }
    }
}
