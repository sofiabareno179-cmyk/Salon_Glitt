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
     public void controlarAccion(ActionEvent evento, Citas unaCitas){
        String accion = evento.getActionCommand();
        switch(accion){
            case "Insertar":
                unaCitas.insertar();
            break;
            case "Modificar":
                unaCitas.modificar();
            break;
            case "Eliminar":
                unaCitas.eliminar();
            break;
        }
    }
}
