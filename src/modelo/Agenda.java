/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author USUARIO
 */
public class Agenda {
    private int idagenda ;
    private String diasemana;
    private LocalTime horainicio;
    private LocalTime horafin;

    public int getIdagenda() {
        return idagenda;
    }

    public void setIdagenda(int idagenda) {
        this.idagenda = idagenda;
    }

    public String getDiasemana() {
        return diasemana;
    }

    public void setDiasemana(String diasemana) {
        this.diasemana = diasemana;
    }

    public LocalTime getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(LocalTime horainicio) {
        this.horainicio = horainicio;
    }

    public LocalTime getHorafin() {
        return horafin;
    }

    public void setHorafin(LocalTime horafin) {
        this.horafin = horafin;
    }

    @Override
    public String toString() {
        return "Agenda{" + "diasemana=" + diasemana + '}';
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
        final Agenda other = (Agenda) obj;
        return this.idagenda == other.idagenda;
    }
    public Iterator<Agenda> listar(){
    ArrayList<Agenda> laAgenda = new ArrayList<>();
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName());
        ResultSet rs = sql.executeQuery();
        Agenda unaAgenda;
        while (rs.next()) {
            unaAgenda = new Agenda();
            unaAgenda.setIdagenda(rs.getInt("idagenda"));
            unaAgenda.setDiasemana(rs.getString("diasemana"));
            unaAgenda.setHorainicio(rs.getObject("horainicio", LocalTime.class));
            unaAgenda.setHorafin(rs.getObject("horafin", LocalTime.class));
            laAgenda.add(unaAgenda);
        }
    } catch (SQLException ex) {
        System.err.println("Error al listar la agenda: " + ex.getMessage());
    }
    if (laAgenda.isEmpty()){
        Agenda miAgenda = new Agenda();
        miAgenda.setDiasemana("No hay disponibilidad");
        laAgenda.add(miAgenda);
    }
    return laAgenda.iterator();
}
   
    public void insertar(){
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("INSERT INTO "
                + this.getClass().getSimpleName() + " VALUES(NULL,?,?,?)");
        sql.setString(1, this.getDiasemana());
        sql.setObject(2, this.getHorainicio());
        sql.setObject(3, this.getHorafin());
        sql.executeUpdate();
        System.out.println(this.getClass().getSimpleName() + " insertado correctamente");
    } catch (SQLException ex) {
        System.err.println("Error al insertar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
}

public void modificar(){
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("UPDATE " + this.getClass().getSimpleName() + 
                " SET diasemana = ?, horainicio = ?, horafin = ? WHERE idagenda = ?");
        sql.setString(1, this.getDiasemana()); 
        sql.setObject(2, this.getHorainicio());
        sql.setObject(3, this.getHorafin());
        sql.setInt(4, this.getIdagenda());
        sql.executeUpdate();
        System.out.println(this.getClass().getSimpleName() + " modificado correctamente");
    } catch (SQLException ex) {
        System.err.println("Error al modificar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
}
public void eliminar(){
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("DELETE FROM "
                + this.getClass().getSimpleName() + " WHERE idagenda = ?");
        sql.setInt(1, this.getIdagenda());
        sql.executeUpdate();
        System.out.println(this.getClass().getSimpleName() + " eliminado correctamente");
    } catch (SQLException ex) {
        System.err.println("Error al eliminar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
}

public Iterator<Agenda> buscar(String busqueda){
    ArrayList<Agenda> laAgenda = new ArrayList<>();
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName()
                + " WHERE diasemana LIKE ? OR horainicio LIKE ? OR horafin LIKE ?");
        sql.setString(1, "%" + busqueda + "%");
        sql.setString(2, "%" + busqueda + "%");
        sql.setString(3, "%" + busqueda + "%");
        ResultSet rs = sql.executeQuery();
        Agenda unaAgenda;
        while (rs.next()) {
            unaAgenda = new Agenda();
            unaAgenda.setIdagenda(rs.getInt("idagenda"));
            unaAgenda.setDiasemana(rs.getString("diasemana"));
            unaAgenda.setHorainicio(rs.getObject("horainicio", LocalTime.class));
            unaAgenda.setHorafin(rs.getObject("forafin", LocalTime.class));
            laAgenda.add(unaAgenda);
        }
    } catch (SQLException ex) {
        System.err.println("Error al buscar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
    return laAgenda.iterator();
}
public Agenda buscarPorId(int elId){
    Agenda unaAgenda = new Agenda();
    unaAgenda.setDiasemana(" no hay disponibilidad");
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName() + " WHERE idagenda = ?");
        sql.setInt(1, elId);
        ResultSet rs = sql.executeQuery();
        while (rs.next()) {
            unaAgenda.setIdagenda(rs.getInt("idagenda"));
            unaAgenda.setDiasemana(rs.getString("diasemana"));
            unaAgenda.setHorainicio(rs.getObject("horainicio", LocalTime.class));
            unaAgenda.setHorafin(rs.getObject("horafin", LocalTime.class));
        }
    } catch (SQLException ex) {
        System.err.println("Error al buscar por Id " + ex.getMessage());
    }
    return unaAgenda;
}

}
