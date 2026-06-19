/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import modelo.Catalogo_Precios;

/**
 *
 * @author USUARIO
 */
public class ControladorCatalogo {
    
        public void controlarAccion(ActionEvent evento, Catalogo_Precios unCatalogo){
        String accion = evento.getActionCommand();
        switch(accion){
            case "Insertar":
                unCatalogo.insertar();
            break;
            case "Modificar":
                unCatalogo.modificar();
            break;
            case "Eliminar":
                unCatalogo.eliminar();
            break;
        }
    }
}
