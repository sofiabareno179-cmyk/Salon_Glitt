/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import modelo.Proveedores;

/**
 *
 * @author USUARIO
 */
public class ControladorProveedores {
      public void controlarAccion(ActionEvent evento, Proveedores unProveedor){
        String accion = evento.getActionCommand();
        switch(accion){
            case "Insertar":
                unProveedor.insertar();
            break;
            case "Modificar":
                unProveedor.modificar();
            break;
            case "Eliminar":
                unProveedor.eliminar();
            break;
        }
    }
}
