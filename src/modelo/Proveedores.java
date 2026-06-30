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
public class Proveedores {
    private int idproveedores;
    private String nombre_empresa;
    private String contacto_nombre;
    private String telefono;
    private String email;
    private String direccion;

    public int getIdproveedores() {
        return idproveedores;
    }

    public void setIdproveedores(int idproveedores) {
        this.idproveedores = idproveedores;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    public String getContacto_nombre() {
        return contacto_nombre;
    }

    public void setContacto_nombre(String contacto_nombre) {
        this.contacto_nombre = contacto_nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Proveedores{" + "nombre_empresa=" + nombre_empresa + '}';
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
        final Proveedores other = (Proveedores) obj;
        return this.idproveedores == other.idproveedores;
    }
    public Iterator<Proveedores> listar(){
    ArrayList<Proveedores> losProveedores = new ArrayList<>();
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName());
        ResultSet rs = sql.executeQuery();
        Proveedores unProveedores;
        while (rs.next()) {
            unProveedores = new Proveedores();
            unProveedores.setIdproveedores(rs.getInt("idproveedores"));
            unProveedores.setNombre_empresa(rs.getString("nombre_empresa"));
            unProveedores.setContacto_nombre(rs.getString("contacto_nombre"));
            unProveedores.setTelefono(rs.getString("telefono"));
            unProveedores.setEmail(rs.getString("email"));
           unProveedores.setDireccion(rs.getString("direccion"));
            losProveedores.add(unProveedores);
        }
    } catch (SQLException ex) {
        System.err.println("Error al listar los proveedores : " + ex.getMessage());
    }
    if (losProveedores.isEmpty()){
        Proveedores elProveedor = new Proveedores();
        elProveedor.setNombre_empresa("No hay");
        losProveedores.add(elProveedor);
    }
    return losProveedores.iterator();
}
    
    public void insertar(){
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("INSERT INTO "
                + this.getClass().getSimpleName() + " (nombre_empresa, contacto_nombre, telefono, email, direccion) VALUES(?,?,?,?,?)");
        sql.setString(1, this.getNombre_empresa());
        sql.setString(2, this.getContacto_nombre());
        sql.setString(3, this.getTelefono());
        sql.setString(4, this.getEmail());
        sql.setString(5, this.getDireccion());
        sql.executeUpdate();
        System.out.println(this.getClass().getSimpleName() + " insertado correctamente");
    } catch (SQLException ex) {
        System.err.println("Error al insertar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
}

public void modificar(){
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("UPDATE " + this.getClass().getSimpleName() + 
                " SET nombre_empresa = ?, contacto_nombre = ?, telefono = ?, email = ?,direccion=? WHERE idproveedores = ?");
        sql.setString(1, this.getNombre_empresa());
        sql.setString(2, this.getContacto_nombre());
        sql.setString(3, this.getTelefono());
        sql.setString(4, this.getEmail());
        sql.setString(5, this.getDireccion());
        sql.setInt(6, this.getIdproveedores());
        sql.executeUpdate();
        System.out.println(this.getClass().getSimpleName() + " modificado correctamente");
    } catch (SQLException ex) {
        System.err.println("Error al modificar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
}
public void eliminar(){
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("DELETE FROM "
                + this.getClass().getSimpleName() + " WHERE idproveedores = ?");
        sql.setInt(1, this.getIdproveedores());
        sql.executeUpdate();
        System.out.println(this.getClass().getSimpleName() + " eliminado correctamente");
    } catch (SQLException ex) {
        System.err.println("Error al eliminar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
}

public Iterator<Proveedores> buscar(String busqueda){
    ArrayList<Proveedores> losProveedores = new ArrayList<>();
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName()
                + " WHERE nombre_empresa LIKE ? OR contacto_nombre LIKE ? OR telefono LIKE ? OR email LIKE ? OR direccion LIKE ?");
        sql.setString(1, "%" + busqueda + "%");
        sql.setString(2, "%" + busqueda + "%");
        sql.setString(3, "%" + busqueda + "%");
        sql.setString(4, "%" + busqueda + "%");
        sql.setString(5, "%" + busqueda + "%");
        ResultSet rs = sql.executeQuery();
        Proveedores unProveedores;
        while (rs.next()) {
            unProveedores = new Proveedores();
            unProveedores.setIdproveedores(rs.getInt("idproveedores"));
            unProveedores.setNombre_empresa(rs.getString("nombre_empresa"));
            unProveedores.setContacto_nombre(rs.getString("contacto_nombre"));
            unProveedores.setTelefono(rs.getString("telefono"));
            unProveedores.setEmail(rs.getString("email"));
           unProveedores.setDireccion(rs.getString("direccion"));
            losProveedores.add(unProveedores);
        }
    } catch (SQLException ex) {
        System.err.println("Error al buscar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
    return losProveedores.iterator();
}
public Proveedores buscarPorId(int elId){
    Proveedores unProveedores = new Proveedores();
    unProveedores.setNombre_empresa(" no hay");
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName() + " WHERE idproveedores = ?");
        sql.setInt(1, elId);
        ResultSet rs = sql.executeQuery();
        while (rs.next()) {
            unProveedores = new Proveedores();
            unProveedores.setIdproveedores(rs.getInt("idproveedores"));
            unProveedores.setNombre_empresa(rs.getString("nombre_empresa"));
            unProveedores.setContacto_nombre(rs.getString("contacto_nombre"));
            unProveedores.setTelefono(rs.getString("telefono"));
            unProveedores.setEmail(rs.getString("email"));
            unProveedores.setDireccion(rs.getString("direccion"));
        }
    } catch (SQLException ex) {
        System.err.println("Error al buscar por Id " + ex.getMessage());
    }
    return unProveedores;
}
 
}




