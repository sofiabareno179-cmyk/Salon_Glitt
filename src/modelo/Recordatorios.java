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
public class Recordatorios {
    private int idrecordatorios;
    private String titulo;
    private String mensaje;
    private LocalDate fecha_recordatorio;
    private int idusuario;
    
    public int getIdrecordatorios() {
        return idrecordatorios;
    }

    public void setIdrecordatorios(int idrecordatorios) {
        this.idrecordatorios = idrecordatorios;
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

    public LocalDate getFecha_recordatorio() {
        return fecha_recordatorio;
    }

    public void setFecha_recordatorio(LocalDate fecha_recordatorio) {
        this.fecha_recordatorio = fecha_recordatorio;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public String toString() {
        return "Rercodatorios{" + "titulo=" + titulo + '}';
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
        final Recordatorios other = (Recordatorios) obj;
        return this.idrecordatorios == other.idrecordatorios;
    }
      public Iterator<Recordatorios> listar(){
    ArrayList<Recordatorios> elRecordatorio = new ArrayList<>();
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName());
        ResultSet rs = sql.executeQuery();
        Recordatorios unRecordatorio;
        while (rs.next()) {
            unRecordatorio = new Recordatorios();
            unRecordatorio.setIdrecordatorios(rs.getInt("idrecordatorios"));
            unRecordatorio.setTitulo(rs.getString("titulo"));
            unRecordatorio.setMensaje(rs.getString("mensaje"));
            unRecordatorio.setFecha_recordatorio(rs.getObject("fecha_recordatorio",LocalDate.class));
            elRecordatorio.add(unRecordatorio);
        }
    } catch (SQLException ex) {
        System.err.println("Error al listar los perfiles : " + ex.getMessage());
    }
    if (elRecordatorio.isEmpty()){
        Recordatorios mirecordatorios = new Recordatorios();
        mirecordatorios.setTitulo("recordatorios inexistente");
        elRecordatorio.add(mirecordatorios);
    }
    return elRecordatorio.iterator();
}
   
    public void insertar(){
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("INSERT INTO "
                + this.getClass().getSimpleName() + " (titulo, mensaje, fecha_recordatorio, idusuario) VALUES(?,?,?,?)");
        sql.setString(1, this.getTitulo());
        sql.setString(2, this.getMensaje());
        sql.setObject(3, this.getFecha_recordatorio());
        sql.setInt(4, this.getIdusuario());
        sql.executeUpdate();
        System.out.println(this.getClass().getSimpleName() + " insertado correctamente");
    } catch (SQLException ex) {
        System.err.println("Error al insertar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
}

public void modificar(){
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("UPDATE " + this.getClass().getSimpleName() + 
                " SET titulo = ?, mensaje = ?, fecha_recordatorio = ? WHERE idrecordatorios = ?");
        sql.setString(1, this.getTitulo());
        sql.setString(2, this.getMensaje());
        sql.setObject(3, this.getFecha_recordatorio());
        sql.setInt(4, this.getIdrecordatorios());
        sql.executeUpdate();
        System.out.println(this.getClass().getSimpleName() + " modificado correctamente");
    } catch (SQLException ex) {
        System.err.println("Error al modificar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
}
public void eliminar(){
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("DELETE FROM "
                + this.getClass().getSimpleName() + " WHERE idrecordatorios = ?");
        sql.setInt(1, this.getIdrecordatorios());
        sql.executeUpdate();
        System.out.println(this.getClass().getSimpleName() + " eliminado correctamente");
    } catch (SQLException ex) {
        System.err.println("Error al eliminar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
}

public Iterator<Recordatorios> buscar(String busqueda){
    ArrayList<Recordatorios> elRecordatorio = new ArrayList<>();
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName()
                + " WHERE titulo LIKE ? OR mensaje LIKE ? OR fecha_recordatorio::text LIKE ?");
        sql.setString(1, "%" + busqueda + "%");
        sql.setString(2, "%" + busqueda + "%");
        sql.setString(3, "%" + busqueda + "%");
        ResultSet rs = sql.executeQuery();
        Recordatorios unRecordatorio;
        while (rs.next()) {
            unRecordatorio = new Recordatorios();
            unRecordatorio.setIdrecordatorios(rs.getInt("idrecordatorios"));
            unRecordatorio.setTitulo(rs.getString("titulo"));
            unRecordatorio.setMensaje(rs.getString("mensaje"));
            unRecordatorio.setFecha_recordatorio(rs.getObject("fecha_recordatorio",LocalDate.class));
            elRecordatorio.add(unRecordatorio);
        }
    } catch (SQLException ex) {
        System.err.println("Error al buscar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
    return elRecordatorio.iterator();
}
public Recordatorios buscarPorId(int elId){
    Recordatorios unRecordatorio = new Recordatorios();
    unRecordatorio.setTitulo("recordatorios inexistente");
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName() + " WHERE idrecordatorios = ?");
        sql.setInt(1, elId);
        ResultSet rs = sql.executeQuery();
        while (rs.next()) {
             unRecordatorio = new Recordatorios();
            unRecordatorio.setIdrecordatorios(rs.getInt("idrecordatorios"));
            unRecordatorio.setTitulo(rs.getString("titulo"));
            unRecordatorio.setMensaje(rs.getString("mensaje"));
            unRecordatorio.setFecha_recordatorio(rs.getObject("fecha_recordatorio",LocalDate.class));
        }
    } catch (SQLException ex) {
        System.err.println("Error al buscar por Id " + ex.getMessage());
    }
    return unRecordatorio;
}

}





