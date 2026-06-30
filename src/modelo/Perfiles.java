/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author USUARIO
 */
public class Perfiles {
    private int id;
    private String nombre;
    private String apellido;
    private String bio;
    private int idusuario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public String toString() {
        return "Perfiles{" + "nombre=" + nombre + '}';
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
        final Perfiles other = (Perfiles) obj;
        return this.id == other.id;
    }
     public Iterator<Perfiles> listar(){
    ArrayList<Perfiles> losPerfiles = new ArrayList<>();
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName());
        ResultSet rs = sql.executeQuery();
        Perfiles unPerfil;
        while (rs.next()) {
            unPerfil = new Perfiles();
            unPerfil.setId(rs.getInt("id"));
            unPerfil.setNombre(rs.getString("nombre"));
            unPerfil.setApellido(rs.getString("apellido"));
            unPerfil.setBio(rs.getString("bio"));
            losPerfiles.add(unPerfil);
        }
    } catch (SQLException ex) {
        System.err.println("Error al listar los perfiles : " + ex.getMessage());
    }
    if (losPerfiles.isEmpty()){
        Perfiles miPerfil = new Perfiles();
        miPerfil.setNombre("este perfil no existe");
        losPerfiles.add(miPerfil);
    }
    return losPerfiles.iterator();
}
   
    public void insertar(){
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("INSERT INTO "
                + this.getClass().getSimpleName() + " (nombre, apellido, bio) VALUES(?,?,?)");
        sql.setString(1, this.getNombre());
        sql.setString(2, this.getApellido());
        sql.setString(3, this.getBio());
        sql.executeUpdate();
        System.out.println(this.getClass().getSimpleName() + " insertado correctamente");
    } catch (SQLException ex) {
        System.err.println("Error al insertar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
}

public void modificar(){
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("UPDATE " + this.getClass().getSimpleName() + 
                " SET nombre = ?, apellido = ?, bio = ?  WHERE id = ?");
        sql.setString(1, this.getNombre());
        sql.setString(2, this.getApellido());
        sql.setString(3, this.getBio());
        sql.setInt(4, this.getId());
        sql.executeUpdate();
        System.out.println(this.getClass().getSimpleName() + " modificado correctamente");
    } catch (SQLException ex) {
        System.err.println("Error al modificar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
}
public void eliminar(){
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("DELETE FROM "
                + this.getClass().getSimpleName() + " WHERE id = ?");
        sql.setInt(1, this.getId());
        sql.executeUpdate();
        System.out.println(this.getClass().getSimpleName() + " eliminado correctamente");
    } catch (SQLException ex) {
        System.err.println("Error al eliminar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
}

public Iterator<Perfiles> buscar(String busqueda){
    ArrayList<Perfiles> losPerfiles = new ArrayList<>();
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName()
                + " WHERE nombre LIKE ? OR apellido LIKE ? OR bio LIKE ?");
        sql.setString(1, "%" + busqueda + "%");
        sql.setString(2, "%" + busqueda + "%");
        sql.setString(3, "%" + busqueda + "%");
        ResultSet rs = sql.executeQuery();
        Perfiles unPerfil;
        while (rs.next()) {
            unPerfil = new Perfiles();
            unPerfil.setId(rs.getInt("id"));
            unPerfil.setNombre(rs.getString("nombre"));
            unPerfil.setApellido(rs.getString("apellido"));
            unPerfil.setBio(rs.getString("bio"));
            losPerfiles.add(unPerfil);
        }
    } catch (SQLException ex) {
        System.err.println("Error al buscar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
    return losPerfiles.iterator();
}
public Perfiles buscarPorId(int elId){
    Perfiles unPerfil = new Perfiles();
    unPerfil.setNombre(" no hay disponiblidad de ese servicio");
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName() + " WHERE id = ?");
        sql.setInt(1, elId);
        ResultSet rs = sql.executeQuery();
        while (rs.next()) {
            unPerfil.setId(rs.getInt("id"));
            unPerfil.setNombre(rs.getString("nombre"));
            unPerfil.setApellido(rs.getString("apellido"));
            unPerfil.setBio(rs.getString("bio"));
        }
    } catch (SQLException ex) {
        System.err.println("Error al buscar por Id " + ex.getMessage());
    }
    return unPerfil;
}

}

