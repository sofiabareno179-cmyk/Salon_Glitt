/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author USUARIO
 */
public class Inventario {
   private int idinventario;
   private int stock ;
  private LocalDate fecha;
  private int idproductos;

    public int getIdinventario() {
        return idinventario;
    }

    public void setIdinventario(int idinventario) {
        this.idinventario = idinventario;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getIdproductos() {
        return idproductos;
    }

    public void setIdproductos(int idproductos) {
        this.idproductos = idproductos;
    }

    @Override
    public String toString() {
        return "Inventario{" + "stock=" + stock + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Inventario other = (Inventario) obj;
        return this.idinventario == other.idinventario;
    }
    public Iterator<Inventario> listar(){
    ArrayList<Inventario> elInventario = new ArrayList<>();
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName());
        ResultSet rs = sql.executeQuery();
        Inventario unInventario;
        while (rs.next()) {
            unInventario = new Inventario();
            unInventario.setIdinventario(rs.getInt("idinventario"));
            unInventario.setStock(rs.getInt("stock"));
            unInventario.setFecha(rs.getObject("fecha",LocalDate.class));
            elInventario.add(unInventario);
        }
    } catch (SQLException ex) {
        System.err.println("Error al listar el inventario : " + ex.getMessage());
    }
    if (elInventario.isEmpty()){
        Inventario inventory = new Inventario();
        inventory.setStock(0);
        elInventario.add(inventory);
    }
    return elInventario.iterator();
}
   
    public void insertar(){
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("INSERT INTO "
                + this.getClass().getSimpleName() + " VALUES(NULL,?,?)");
        sql.setInt(1, this.getStock());
        sql.setObject(2, this.getFecha());
        sql.executeUpdate();
        System.out.println(this.getClass().getSimpleName() + " insertado correctamente");
    } catch (SQLException ex) {
        System.err.println("Error al insertar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
}

public void modificar(){
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("UPDATE " + this.getClass().getSimpleName() + 
                " SET stock = ?, fecha = ? WHERE idinventario = ?");
        sql.setInt(1, this.getStock());
        sql.setObject(2, this.getFecha());
        sql.executeUpdate();
        System.out.println(this.getClass().getSimpleName() + " modificado correctamente");
    } catch (SQLException ex) {
        System.err.println("Error al modificar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
}
public void eliminar(){
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("DELETE FROM "
                + this.getClass().getSimpleName() + " WHERE idinventario = ?");
        sql.setInt(1, this.getIdinventario());
        sql.executeUpdate();
        System.out.println(this.getClass().getSimpleName() + " eliminado correctamente");
    } catch (SQLException ex) {
        System.err.println("Error al eliminar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
}

public Iterator<Inventario> buscar(String busqueda){
    ArrayList<Inventario> elInventario = new ArrayList<>();
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName()
                + " WHERE stock LIKE ? OR fecha LIKE ? ");
        sql.setString(1, "%" + busqueda + "%");
        sql.setString(2, "%" + busqueda + "%");
        ResultSet rs = sql.executeQuery();
        Inventario unInventario;
        while (rs.next()) {
            unInventario = new Inventario();
            unInventario.setIdinventario(rs.getInt("idinventario"));
            unInventario.setStock(rs.getInt("stock"));
            unInventario.setFecha(rs.getObject("fecha",LocalDate.class));
            elInventario.add(unInventario);
        }
    } catch (SQLException ex) {
        System.err.println("Error al buscar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
    return elInventario.iterator();
}
public Inventario buscarPorId(int elId){
    Inventario unInventario = new Inventario();
    unInventario.setStock(0);
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName() + " WHERE idinventario = ?");
        sql.setInt(1, elId);
        ResultSet rs = sql.executeQuery();
        while (rs.next()) {
                      unInventario = new Inventario();
            unInventario.setIdinventario(rs.getInt("idinventario"));
            unInventario.setStock(rs.getInt("stock"));
            unInventario.setFecha(rs.getObject("fecha",LocalDate.class));
        }
    } catch (SQLException ex) {
        System.err.println("Error al buscar por Id " + ex.getMessage());
    }
   return unInventario;
}

    
}
