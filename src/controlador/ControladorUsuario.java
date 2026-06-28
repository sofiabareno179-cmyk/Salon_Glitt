/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import java.awt.event.ActionEvent;
import modelo.Usuario;
/**
 *
 * @author LENOVO
 */
public class ControladorUsuario {
    
    public void controlarAccion(ActionEvent evento, Usuario unUsuario){
        String accion = evento.getActionCommand();
        switch(accion){
            case "Insertar":
            case "Crear Cuenta":
                unUsuario.insertar();
            break;
            case "Modificar":
                unUsuario.modificar();
            break;
            case "Eliminar":
                unUsuario.eliminar();
            break;
        }
    }
    
}
