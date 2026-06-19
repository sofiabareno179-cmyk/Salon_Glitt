/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import modelo.Promociones;

/**
 *
 * @author USUARIO
 */
public class ControladorPromociones {
    
        public void controlarAccion(ActionEvent evento, Promociones unaPromocion){
        String accion = evento.getActionCommand();
        switch(accion){
            case "Insertar":
                unaPromocion.insertar();
            break;
            case "Modificar":
                unaPromocion.modificar();
            break;
            case "Eliminar":
                unaPromocion.eliminar();
            break;
        }
    }
}
