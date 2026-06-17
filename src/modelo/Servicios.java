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
public class Servicios {
    private int idservicio;
    private String nombre;
    private BigDecimal precio;
    private String duracion;
    private String categoria;
    private String imagen;

    public int getIdservicio() {
        return idservicio;
    }

    public void setIdservicio(int idservicio) {
        this.idservicio = idservicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Servicios{" + "nombre=" + nombre + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Servicios other = (Servicios) obj;
        return this.idservicio == other.idservicio;
    }
    
     public Iterator<Servicios> listar(){
    ArrayList<Servicios> losServicios = new ArrayList<>();
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName());
        ResultSet rs = sql.executeQuery();
        Servicios unServicio;
        while (rs.next()) {
            unServicio = new Servicios();
            unServicio.setIdservicio(rs.getInt("idProductos"));
            unServicio.setNombre(rs.getString("nombre"));
            unServicio.setPrecio(rs.getBigDecimal("precio"));
            unServicio.setDuracion(rs.getString("duracion"));
            unServicio.setCategoria(rs.getString("categoria"));
            unServicio.setImagen(rs.getString("imagen"));
            losServicios.add(unServicio);
        }
    } catch (SQLException ex) {
        System.err.println("Error al listar los productos : " + ex.getMessage());
    }
    if (losServicios.isEmpty()){
        Servicios misServicios = new Servicios();
        misServicios.setNombre("No hay disponibilidad para ese serrvicios");
        losServicios.add(misServicios);
    }
    return losServicios.iterator();
}
   
    public void insertar(){
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("INSERT INTO "
                + this.getClass().getSimpleName() + " VALUES(NULL,?,?,?)");
        sql.setString(1, this.getNombre());
        sql.setObject(2, this.getPrecio());
        sql.setString(3, this.getDuracion());
        sql.setString(4, this.getCategoria());
        sql.setString(5, this.getImagen());
        sql.executeUpdate();
        System.out.println(this.getClass().getSimpleName() + " insertado correctamente");
    } catch (SQLException ex) {
        System.err.println("Error al insertar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
}

public void modificar(){
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("UPDATE " + this.getClass().getSimpleName() + 
                " SET nombre = ?, precio = ?, duracion = ? ,categoria = ?,imagen = ? WHERE idservicio = ?");
        sql.setString(1, this.getNombre());
        sql.setObject(2, this.getPrecio());
        sql.setString(3, this.getDuracion());
        sql.setString(4, this.getCategoria());
        sql.setString(5, this.getImagen());
        sql.executeUpdate();
        System.out.println(this.getClass().getSimpleName() + " modificado correctamente");
    } catch (SQLException ex) {
        System.err.println("Error al modificar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
}
public void eliminar(){
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("DELETE FROM "
                + this.getClass().getSimpleName() + " WHERE idservicio = ?");
        sql.setInt(1, this.getIdservicio());
        sql.executeUpdate();
        System.out.println(this.getClass().getSimpleName() + " eliminado correctamente");
    } catch (SQLException ex) {
        System.err.println("Error al eliminar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
}

public Iterator<Servicios> buscar(String busqueda){
    ArrayList<Servicios> losServicios = new ArrayList<>();
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName()
                + " WHERE nombre LIKE ? OR precio LIKE ? OR duracion LIKE ? 0R categoria LIKE ? OR imagen LIKE ?");
        sql.setString(1, "%" + busqueda + "%");
        sql.setString(2, "%" + busqueda + "%");
        sql.setString(3, "%" + busqueda + "%");
        sql.setString(4, "%" + busqueda + "%");
        sql.setString(5, "%" + busqueda + "%");
        ResultSet rs = sql.executeQuery();
        Servicios unServicio;
        while (rs.next()) {
            unServicio = new Servicios();
            unServicio.setIdservicio(rs.getInt("idservicio"));
            unServicio.setNombre(rs.getString("nombre"));
            unServicio.setPrecio(rs.getBigDecimal("precio"));
            unServicio.setDuracion(rs.getString("duracion"));
            unServicio.setCategoria(rs.getString("categoria"));
            unServicio.setImagen(rs.getString("imagen"));
            losServicios.add(unServicio);
        }
    } catch (SQLException ex) {
        System.err.println("Error al buscar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
    return losServicios.iterator();
}
public Servicios buscarPorId(int elId){
    Servicios unServicio = new Servicios();
    unServicio.setNombre(" no hay disponiblidad de ese servicio");
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName() + " WHERE idservicio = ?");
        sql.setInt(1, elId);
        ResultSet rs = sql.executeQuery();
        while (rs.next()) {
            unServicio.setIdservicio(rs.getInt("idservicio"));
            unServicio.setNombre(rs.getString("nombre"));
            unServicio.setPrecio(rs.getBigDecimal("precio"));
            unServicio.setDuracion(rs.getString("duracion"));
            unServicio.setCategoria(rs.getString("categoria"));
            unServicio.setImagen(rs.getString("imagen"));
        }
    } catch (SQLException ex) {
        System.err.println("Error al buscar por Id " + ex.getMessage());
    }
    return unServicio;
}

}
