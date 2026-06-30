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
public class Notificacion {
 private int idnotificaciones;   
 private String titulo;
 private String mensaje;
 private int idusuario;
 private boolean leida;
 private LocalDateTime fechaCreacion;

    public int getIdnotificaciones() {
        return idnotificaciones;
    }

    public void setIdnotificaciones(int idnotificaciones) {
        this.idnotificaciones = idnotificaciones;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public boolean isLeida() {
        return leida;
    }

    public void setLeida(boolean leida) {
        this.leida = leida;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return "Notificacion{" + "titulo=" + titulo + '}';
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
        final Notificacion other = (Notificacion) obj;
        return this.idnotificaciones == other.idnotificaciones;
    }
    public Iterator<Notificacion> listar(){
    ArrayList<Notificacion> lasNotificaciones = new ArrayList<>();
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName());
        ResultSet rs = sql.executeQuery();
        Notificacion unaNotificacion;
        while (rs.next()) {
            unaNotificacion = new Notificacion();
            unaNotificacion.setIdnotificaciones(rs.getInt("idnotificaciones"));
            unaNotificacion.setTitulo(rs.getString("titulo"));
            unaNotificacion.setMensaje(rs.getString("mensaje"));
            unaNotificacion.setLeida(rs.getBoolean("leida"));
            unaNotificacion.setIdusuario(rs.getInt("idusuario"));
            unaNotificacion.setFechaCreacion(rs.getObject("fechaCreacion", LocalDateTime.class));
            lasNotificaciones.add(unaNotificacion);
        }
    } catch (SQLException ex) {
        System.err.println("Error al listar las notificaciones : " + ex.getMessage());
    }
    if (lasNotificaciones.isEmpty()){
        Notificacion miNotificacion = new Notificacion();
        miNotificacion.setTitulo("No hay notificaciones");
        lasNotificaciones.add(miNotificacion);
    }
    return lasNotificaciones.iterator();
}
    
    public void insertar(){
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("INSERT INTO "
                + this.getClass().getSimpleName() + " (titulo, mensaje, idusuario, leida, fechaCreacion) VALUES(?,?,?,?,?)");
        sql.setString(1, this.getTitulo());
        sql.setString(2, this.getMensaje());
        sql.setInt(3, this.getIdusuario());
        sql.setBoolean(4, this.isLeida());
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
                " SET titulo = ?, mensaje = ?, leida = ?, fechaCreacion = ? WHERE idnotificaciones = ?");
        sql.setString(1, this.getTitulo());
        sql.setString(2, this.getMensaje());
        sql.setBoolean(3, this.isLeida());
        sql.setObject(4, this.getFechaCreacion());
        sql.setInt(5, this.getIdnotificaciones());
        sql.executeUpdate();
        System.out.println(this.getClass().getSimpleName() + " modificado correctamente");
    } catch (SQLException ex) {
        System.err.println("Error al modificar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
}
public void eliminar(){
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("DELETE FROM "
                + this.getClass().getSimpleName() + " WHERE idnotificaciones = ?");
        sql.setInt(1, this.getIdnotificaciones());
        sql.executeUpdate();
        System.out.println(this.getClass().getSimpleName() + " eliminado correctamente");
    } catch (SQLException ex) {
        System.err.println("Error al eliminar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
}

public Iterator<Notificacion> buscar(String busqueda){
    ArrayList<Notificacion> lasNotificaciones = new ArrayList<>();
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName()
                + " WHERE titulo LIKE ? OR mensaje LIKE ? OR leida::text LIKE ? OR fechaCreacion::text LIKE ?");
        sql.setString(1, "%" + busqueda + "%");
        sql.setString(2, "%" + busqueda + "%");
        sql.setString(3, "%" + busqueda + "%");
        sql.setString(4, "%" + busqueda + "%");
        ResultSet rs = sql.executeQuery();
        Notificacion unaNotificacion;
        while (rs.next()) {
            unaNotificacion = new Notificacion();
            unaNotificacion.setIdnotificaciones(rs.getInt("idnotificaciones"));
            unaNotificacion.setTitulo(rs.getString("titulo"));
            unaNotificacion.setMensaje(rs.getString("mensaje"));
            unaNotificacion.setLeida(rs.getBoolean("leida"));
            unaNotificacion.setIdusuario(rs.getInt("idusuario"));
            unaNotificacion.setFechaCreacion(rs.getObject("fechaCreacion", LocalDateTime.class));
            lasNotificaciones.add(unaNotificacion);
        }
    } catch (SQLException ex) {
        System.err.println("Error al buscar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
    return lasNotificaciones.iterator();
}
public Notificacion buscarPorId(int elId){
    Notificacion unaNotificacion = new Notificacion();
    unaNotificacion.setTitulo("notificacion inexistente");
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName() + " WHERE idnotificaciones = ?");
        sql.setInt(1, elId);
        ResultSet rs = sql.executeQuery();
        while (rs.next()) {
            unaNotificacion.setIdnotificaciones(rs.getInt("idnotificaciones"));
            unaNotificacion.setTitulo(rs.getString("titulo"));
            unaNotificacion.setMensaje(rs.getString("mensaje"));
            unaNotificacion.setLeida(rs.getBoolean("leida"));
            unaNotificacion.setIdusuario(rs.getInt("idusuario"));
            unaNotificacion.setFechaCreacion(rs.getObject("fechaCreacion", LocalDateTime.class));
        }
    } catch (SQLException ex) {
        System.err.println("Error al buscar por Id " + ex.getMessage());
    }
    return unaNotificacion;
}
 
}

