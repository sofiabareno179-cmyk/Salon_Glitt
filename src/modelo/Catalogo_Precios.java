/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author USUARIO
 */
public class Catalogo_Precios {
    private int idcatalogo;
    private  String nombre;
    private  String descripcion;
    private double precio;
    private String categoria;
    private LocalDateTime fecha_creacion;

    public int getIdcatalogo() {
        return idcatalogo;
    }

    public void setIdcatalogo(int idcatalogo) {
        this.idcatalogo = idcatalogo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDateTime getFechaCreacion() {
        return fecha_creacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fecha_creacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return "Catalog_Precios{" + "nombre=" + nombre + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Catalogo_Precios other = (Catalogo_Precios) obj;
        return this.idcatalogo == other.idcatalogo;
    }
    
  public Iterator<Catalogo_Precios> listar(){
    ArrayList<Catalogo_Precios> losCatalogo = new ArrayList<>();
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName());
        ResultSet rs = sql.executeQuery();
        Catalogo_Precios unCatalogo;
        while (rs.next()) {
            unCatalogo = new Catalogo_Precios();
            unCatalogo.setIdcatalogo(rs.getInt("idcatalogo"));
            unCatalogo.setNombre(rs.getString("nombre"));
            unCatalogo.setDescripcion(rs.getString("descripcion"));
            unCatalogo.setPrecio(rs.getFloat("precio"));
            unCatalogo.setCategoria(rs.getString("categoria"));
            unCatalogo.setFechaCreacion(rs.getObject("fecha_creacion",LocalDateTime.class));
            losCatalogo.add(unCatalogo);
        }
    } catch (SQLException ex) {
        System.err.println("Error al listar el catalogo de los precios : " + ex.getMessage());
    }
    if (losCatalogo.isEmpty()){
        Catalogo_Precios elcatalogo  = new Catalogo_Precios();
        elcatalogo.setNombre("No existe");
        losCatalogo.add(elcatalogo);
    }
    return losCatalogo.iterator();
}
   
    public void insertar(){
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("INSERT INTO "
                + this.getClass().getSimpleName() + " (nombre, descripcion, precio, categoria, fecha_creacion) VALUES(?,?,?,?,?)");
        sql.setString(1, this.getNombre());
        sql.setString(2, this.getDescripcion());
        sql.setDouble(3, this.getPrecio());
        sql.setString(4, this.getCategoria());
        sql.setObject(5, this.getFechaCreacion());
        sql.executeUpdate();
        System.out.println(this.getClass().getSimpleName() + " insertado correctamente");
    } catch (SQLException ex) {
        System.err.println("Error al insertar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
}

public void modificar(){
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("UPDATE " + this.getClass().getSimpleName() + 
                " SET nombre = ?, descripcion = ?, precio = ? ,categoria = ?,fecha_creacion = ? WHERE idcatalogo = ?");
       sql.setString(1, this.getNombre());
        sql.setString(2, this.getDescripcion());
        sql.setDouble(3, this.getPrecio());
        sql.setString(4, this.getCategoria());
        sql.setObject(5, this.getFechaCreacion());
        sql.executeUpdate();
        System.out.println(this.getClass().getSimpleName() + " modificado correctamente");
    } catch (SQLException ex) {
        System.err.println("Error al modificar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
}
public void eliminar(){
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("DELETE FROM "
                + this.getClass().getSimpleName() + " WHERE idcatalogo = ?");
        sql.setInt(1, this.getIdcatalogo());
        sql.executeUpdate();
        System.out.println(this.getClass().getSimpleName() + " eliminado correctamente");
    } catch (SQLException ex) {
        System.err.println("Error al eliminar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
}

public Iterator<Catalogo_Precios> buscar(String busqueda){
    ArrayList<Catalogo_Precios> loscatalogos = new ArrayList<>();
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName()
                + " WHERE nombre LIKE ? OR descripcion LIKE ? OR precio::text LIKE ? OR categoria LIKE ? OR fecha_creacion::text LIKE ?");
        sql.setString(1, "%" + busqueda + "%");
        sql.setString(2, "%" + busqueda + "%");
        sql.setString(3, "%" + busqueda + "%");
        sql.setString(4, "%" + busqueda + "%");
        sql.setString(5, "%" + busqueda + "%");
        ResultSet rs = sql.executeQuery();
        Catalogo_Precios unCatalogo;
        while (rs.next()) {
          unCatalogo = new Catalogo_Precios();
            unCatalogo.setIdcatalogo(rs.getInt("idcatalogo"));
            unCatalogo.setNombre(rs.getString("nombre"));
            unCatalogo.setDescripcion(rs.getString("descripcion"));
            unCatalogo.setPrecio(rs.getFloat("precio"));
            unCatalogo.setCategoria(rs.getString("categoria"));
            unCatalogo.setFechaCreacion(rs.getObject("fecha_creacion",LocalDateTime.class));
            loscatalogos.add(unCatalogo);
        }
    } catch (SQLException ex) {
        System.err.println("Error al buscar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
    return loscatalogos.iterator();
}
public Catalogo_Precios buscarPorId(int elId){
    Catalogo_Precios unCatalogo = new Catalogo_Precios();
    unCatalogo.setNombre(" no existe");
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName() + " WHERE idcatalogo = ?");
        sql.setInt(1, elId);
        ResultSet rs = sql.executeQuery();
        while (rs.next()) {
            unCatalogo = new Catalogo_Precios();
            unCatalogo.setIdcatalogo(rs.getInt("idcatalogo"));
            unCatalogo.setNombre(rs.getString("nombre"));
            unCatalogo.setDescripcion(rs.getString("descripcion"));
            unCatalogo.setPrecio(rs.getFloat("precio"));
            unCatalogo.setCategoria(rs.getString("categoria"));
            unCatalogo.setFechaCreacion(rs.getObject("fecha_creacion",LocalDateTime.class));
        }
    } catch (SQLException ex) {
        System.err.println("Error al buscar por Id " + ex.getMessage());
    }
    return unCatalogo;
}

    
}
