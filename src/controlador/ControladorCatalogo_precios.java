/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import java.awt.event.ActionEvent;
import modelo.Catalogo_Precios;
/**
 *
 * @author LENOVO
 */
public class ControladorCatalogo_precios {
    public void controlarAccion(ActionEvent evento, Catalogo_Precios unCatalogo_precios){
        String accion = evento.getActionCommand();
        switch(accion){
            case "Insertar":
                unCatalogo_precios.insertar();
            break;
            case "Modificar":
                unCatalogo_precios.modificar();
            break;
            case "Eliminar":
                unCatalogo_precios.eliminar();
            break;
        }
    }
}
