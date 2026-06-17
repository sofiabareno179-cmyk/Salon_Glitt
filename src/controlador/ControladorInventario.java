/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import java.awt.event.ActionEvent;
import modelo.Inventario;
/**
 *
 * @author LENOVO
 */
public class ControladorInventario {
    
    public void controlarAccion(ActionEvent evento, Inventario unInventario){
        String accion = evento.getActionCommand();
        switch(accion){
            case "Insertar":
                unInventario.insertar();
            break;
            case "Modificar":
                unInventario.modificar();
            break;
            case "Eliminar":
                unInventario.eliminar();
            break;
        }
    }
}
