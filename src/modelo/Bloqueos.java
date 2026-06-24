/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author USUARIO
 */
public class Bloqueos {
  private int idbloqueo;
    private LocalDate fecha;
    private String hora_inicio;
    private LocalDateTime Fecha_creacion;
    private String motivo;
    private LocalDateTime created_at;
    
    public int getIdbloqueo() {
        return idbloqueo;
    }  

    public void setIdbloqueo(int idbloqueo) {
        this.idbloqueo = idbloqueo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public LocalDateTime getFecha_creacion() {
        return Fecha_creacion;
    }

    public void setFecha_creacion(LocalDateTime Fecha_creacion) {
        this.Fecha_creacion = Fecha_creacion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
   



    @Override
    public String toString() {
        return "Bloqueos{" + "motivo=" + motivo + '}';
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
        final Bloqueos other = (Bloqueos) obj;
        return this.idbloqueo == other.idbloqueo;
    }
        
  public Iterator<Bloqueos> listar(){
    ArrayList<Bloqueos> losBloqueos = new ArrayList<>();
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName());
        ResultSet rs = sql.executeQuery();
        Bloqueos unBloqueo;
        while (rs.next()) {
            unBloqueo = new Bloqueos();
            unBloqueo.setIdbloqueo(rs.getInt("idbloqueo"));
            unBloqueo.setFecha(rs.getObject("fecha",LocalDate.class));
            unBloqueo.setHora_inicio(rs.getString("hora_inicio"));
            unBloqueo.setFecha_creacion(rs.getObject("fecha_creacion",LocalDateTime.class));
            unBloqueo.setMotivo(rs.getString("motivo"));
            unBloqueo.setCreated_at(rs.getObject("created_at",LocalDateTime.class));
            losBloqueos.add(unBloqueo);
        }
    } catch (SQLException ex) {
        System.err.println("Error al listar los bloqueos : " + ex.getMessage());
    }
    if (losBloqueos.isEmpty()){
        Bloqueos elBloqueo = new Bloqueos();
        elBloqueo.setMotivo("No hay bloqueo existente");
        losBloqueos.add(elBloqueo);
    }
    return losBloqueos.iterator();
}
   
    public void insertar(){
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("INSERT INTO "
                + this.getClass().getSimpleName() + " VALUES(NULL,?,?,?,?,?)");
        sql.setObject(1, this.getFecha());
        sql.setString(2, this.getHora_inicio());
        sql.setObject(3, this.getFecha_creacion());
        sql.setString(4, this.getMotivo());
        sql.setObject(5, this.getCreated_at());
        sql.executeUpdate();
        System.out.println(this.getClass().getSimpleName() + " insertado correctamente");
    } catch (SQLException ex) {
        System.err.println("Error al insertar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
}

public void modificar(){
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("UPDATE " + this.getClass().getSimpleName() + 
                " SET fecha = ?, hora_inicio = ?, fecha_creacion = ? ,motivo = ?,created_at = ? WHERE idbloqueo = ?");
        sql.setObject(1, this.getFecha());
        sql.setString(2, this.getHora_inicio());
        sql.setObject(3, this.getFecha_creacion());
        sql.setString(4, this.getMotivo());
        sql.setObject(5, this.getCreated_at());
        sql.executeUpdate();
        System.out.println(this.getClass().getSimpleName() + " modificado correctamente");
    } catch (SQLException ex) {
        System.err.println("Error al modificar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
}
public void eliminar(){
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("DELETE FROM "
                + this.getClass().getSimpleName() + " WHERE idbloqueo = ?");
        sql.setInt(1, this.getIdbloqueo());
        sql.executeUpdate();
        System.out.println(this.getClass().getSimpleName() + " eliminado correctamente");
    } catch (SQLException ex) {
        System.err.println("Error al eliminar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
}

public Iterator<Bloqueos> buscar(String busqueda){
    ArrayList<Bloqueos> losBloqueos = new ArrayList<>();
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName()
                + " WHERE fecha LIKE ? OR hora_inicio LIKE ? OR fecha_creacion LIKE ? OR motivo LIKE ? OR created_at LIKE ?");
        sql.setString(1, "%" + busqueda + "%");
        sql.setString(2, "%" + busqueda + "%");
        sql.setString(3, "%" + busqueda + "%");
        sql.setString(4, "%" + busqueda + "%");
        sql.setString(5, "%" + busqueda + "%");
        ResultSet rs = sql.executeQuery();
        Bloqueos unBloqueo;
        while (rs.next()) {
             unBloqueo = new Bloqueos();
            unBloqueo.setIdbloqueo(rs.getInt("idbloqueo"));
            unBloqueo.setFecha(rs.getObject("fecha",LocalDate.class));
            unBloqueo.setHora_inicio(rs.getString("hora_inicio"));
            unBloqueo.setFecha_creacion(rs.getObject("fecha_creacion",LocalDateTime.class));
            unBloqueo.setMotivo(rs.getString("motivo"));
            unBloqueo.setCreated_at(rs.getObject("created_at",LocalDateTime.class));
            losBloqueos.add(unBloqueo);
        }
    } catch (SQLException ex) {
        System.err.println("Error al buscar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
    return losBloqueos.iterator();
}
public Bloqueos buscarPorId(int elId){
    Bloqueos unBloqueo = new Bloqueos();
    unBloqueo.setMotivo(" no hay bloqueo existente");
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName() + " WHERE idbloqueo = ?");
        sql.setInt(1, elId);
        ResultSet rs = sql.executeQuery();
        while (rs.next()) {
              unBloqueo = new Bloqueos();
            unBloqueo.setIdbloqueo(rs.getInt("idbloqueo"));
            unBloqueo.setFecha(rs.getObject("fecha",LocalDate.class));
            unBloqueo.setHora_inicio(rs.getString("hora_inicio"));
            unBloqueo.setFecha_creacion(rs.getObject("fecha_creacion",LocalDateTime.class));
            unBloqueo.setMotivo(rs.getString("motivo"));
            unBloqueo.setCreated_at(rs.getObject("created_at",LocalDateTime.class));
        }
    } catch (SQLException ex) {
        System.err.println("Error al buscar por Id " + ex.getMessage());
    }
    return unBloqueo;
}

    
    
}
