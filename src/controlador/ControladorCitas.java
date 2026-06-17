/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import java.awt.event.ActionEvent;
import modelo.Citas;
/**
 *
 * @author LENOVO
 */
public class ControladorCitas {
     public void controlarAccion(ActionEvent evento, Citas unaCita){
        String accion = evento.getActionCommand();
        switch(accion){
            case "Insertar":
                unaCita.insertar();
            break;
            case "Modificar":
                unaCita.modificar();
            break;
            case "Eliminar":
                unaCita.eliminar();
            break;
        }
    }
}
