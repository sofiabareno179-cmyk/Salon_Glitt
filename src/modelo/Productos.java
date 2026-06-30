/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author USUARIO
 */
public class Productos {
    private int idproductos;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private String categoria;

    public int getIdproductos() {
        return idproductos;
    }

    public void setIdproductos(int idproductos) {
        this.idproductos = idproductos;
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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Productos{" + "nombre=" + nombre + '}';
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
        final Productos other = (Productos) obj;
        return this.idproductos == other.idproductos;
    }
     public Iterator<Productos> listar(){
    ArrayList<Productos> losProductos = new ArrayList<>();
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName());
        ResultSet rs = sql.executeQuery();
        Productos unProducto;
        while (rs.next()) {
            unProducto = new Productos();
            unProducto.setIdproductos(rs.getInt("idproductos"));
            unProducto.setNombre(rs.getString("nombre"));
            unProducto.setDescripcion(rs.getString("descripcion"));
            unProducto.setPrecio(rs.getBigDecimal("precio"));
            unProducto.setCategoria(rs.getString("categoria"));
            losProductos.add(unProducto);
        }
    } catch (SQLException ex) {
        System.err.println("Error al listar los productos : " + ex.getMessage());
    }
    if (losProductos.isEmpty()){
        Productos misProductos = new Productos();
        misProductos.setNombre("No hay ese producto");
        losProductos.add(misProductos);
    }
    return losProductos.iterator();
}
   
    public void insertar(){
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("INSERT INTO "
                + this.getClass().getSimpleName() + " (nombre, descripcion, precio, categoria) VALUES(?,?,?,?)");
        sql.setString(1, this.getNombre());
        sql.setString(2, this.getDescripcion());
        sql.setObject(3, this.getPrecio());
        sql.setString(4, this.getCategoria());
        sql.executeUpdate();
        System.out.println(this.getClass().getSimpleName() + " insertado correctamente");
    } catch (SQLException ex) {
        System.err.println("Error al insertar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
}

public void modificar(){
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("UPDATE " + this.getClass().getSimpleName() + 
                " SET nombre = ?, descripcion = ?, precio = ? ,categoria = ? WHERE idproductos = ?");
        sql.setString(1, this.getNombre());
        sql.setString(2, this.getDescripcion());
        sql.setObject(3, this.getPrecio());
        sql.setString(4, this.getCategoria());
        sql.setInt(5, this.getIdproductos());
        sql.executeUpdate();
        System.out.println(this.getClass().getSimpleName() + " modificado correctamente");
    } catch (SQLException ex) {
        System.err.println("Error al modificar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
}
public void eliminar(){
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("DELETE FROM "
                + this.getClass().getSimpleName() + " WHERE idproductos = ?");
        sql.setInt(1, this.getIdproductos());
        sql.executeUpdate();
        System.out.println(this.getClass().getSimpleName() + " eliminado correctamente");
    } catch (SQLException ex) {
        System.err.println("Error al eliminar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
}

public Iterator<Productos> buscar(String busqueda){
    ArrayList<Productos> losProductos = new ArrayList<>();
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName()
                + " WHERE nombre LIKE ? OR descripcion LIKE ? OR precio::text LIKE ? OR categoria LIKE ?");
        sql.setString(1, "%" + busqueda + "%");
        sql.setString(2, "%" + busqueda + "%");
        sql.setString(3, "%" + busqueda + "%");
        sql.setString(4, "%" + busqueda + "%");
        ResultSet rs = sql.executeQuery();
        Productos unProducto;
        while (rs.next()) {
            unProducto = new Productos();
            unProducto.setIdproductos(rs.getInt("idproductos"));
            unProducto.setNombre(rs.getString("nombre"));
            unProducto.setDescripcion(rs.getString("descripcion"));
            unProducto.setPrecio(rs.getBigDecimal("precio"));
            unProducto.setCategoria(rs.getString("categoria"));
            losProductos.add(unProducto);
        }
    } catch (SQLException ex) {
        System.err.println("Error al buscar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
    return losProductos.iterator();
}
public Productos buscarPorId(int elId){
    Productos unProducto = new Productos();
    unProducto.setNombre(" no hay ese producto");
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName() + " WHERE idproductos = ?");
        sql.setInt(1, elId);
        ResultSet rs = sql.executeQuery();
        while (rs.next()) {
            unProducto.setIdproductos(rs.getInt("idproductos"));
            unProducto.setNombre(rs.getString("nombre"));
            unProducto.setDescripcion(rs.getString("descripcion"));
            unProducto.setPrecio(rs.getBigDecimal("precio"));
            unProducto.setCategoria(rs.getString("categoria"));
        }
    } catch (SQLException ex) {
        System.err.println("Error al buscar por Id " + ex.getMessage());
    }
    return unProducto;
}

    
}

