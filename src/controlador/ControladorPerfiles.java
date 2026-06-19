/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import java.awt.event.ActionEvent;
import modelo.Perfiles;
/**
 *
 * @author LENOVO
 */
public class ControladorPerfiles {
    
    public void controlarAccion(ActionEvent evento, Perfiles unPerfil){
        String accion = evento.getActionCommand();
        switch(accion){
            case "Insertar":
                unPerfil.insertar();
            break;
            case "Modificar":
                unPerfil.modificar();
            break;
            case "Eliminar":
                unPerfil.eliminar();
            break;
        }
    }
}
