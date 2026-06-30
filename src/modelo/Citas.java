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
public class Citas {
    private int idcitas;
    private LocalDateTime fechahora;
    private String estado;
    private int idusuario;
    private String servicio;

    public int getIdcitas() {
        return idcitas;
    }

    public void setIdcitas(int idcitas) {
        this.idcitas = idcitas;
    }

    public LocalDateTime getFechahora() {
        return fechahora;
    }

    public void setFechahora(LocalDateTime fechahora) {
        this.fechahora = fechahora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    @Override
    public String toString() {
        return "Cita{" + "servicio=" + servicio + '}';
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
        final Citas other = (Citas) obj;
        return this.idcitas == other.idcitas;
    }
     public Iterator<Citas> listar(){
    ArrayList<Citas> lasCitas = new ArrayList<>();
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName());
        ResultSet rs = sql.executeQuery();
        Citas unaCita;
        while (rs.next()) {
            unaCita = new Citas();
            unaCita.setIdcitas(rs.getInt("idcitas"));
            unaCita.setFechahora(rs.getObject("fechahora",LocalDateTime.class));
            unaCita.setEstado(rs.getString("estado"));
            unaCita.setServicio(rs.getString("servicio"));
            unaCita.setIdusuario(rs.getInt("idusuario"));
            lasCitas.add(unaCita);
        }
    } catch (SQLException ex) {
        System.err.println("Error al listar las citas : " + ex.getMessage());
    }
    if (lasCitas.isEmpty()){
        Citas misCitas = new Citas();
        misCitas.setServicio("No hay disponibilidad");
        lasCitas.add(misCitas);
    }
    return lasCitas.iterator();
}
   
    public void insertar(){
    try {
        String query = "INSERT INTO " + this.getClass().getSimpleName() + " (fechahora, estado, idusuario,servicio) VALUES(?,?,?,?)";
        PreparedStatement sql = ConexionBD.conexion.prepareStatement(query);
        sql.setObject(1, this.getFechahora());
        sql.setString(2, this.getEstado());
        sql.setInt(3, this.getIdusuario());
        sql.setString(4, this.getServicio());
        sql.executeUpdate();
        System.out.println(this.getClass().getSimpleName() + " insertado correctamente");
    } catch (SQLException ex) {
        System.err.println("Error al insertar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
}

public void modificar(){
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("UPDATE " + this.getClass().getSimpleName() + 
                " SET fechahora = ?, estado = ?, servicio = ?, idusuario = ? WHERE idcitas = ?");
        sql.setObject(1, this.getFechahora());
        sql.setString(2, this.getEstado());
        sql.setString(3, this.getServicio());
        sql.setInt(4, this.getIdusuario());
        sql.setInt(5, this.getIdcitas());
        sql.executeUpdate();
        System.out.println(this.getClass().getSimpleName() + " modificado correctamente");
    } catch (SQLException ex) {
        System.err.println("Error al modificar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
}
public void eliminar(){
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("DELETE FROM "
                + this.getClass().getSimpleName() + " WHERE idcitas = ?");
        sql.setInt(1, this.getIdcitas());
        sql.executeUpdate();
        System.out.println(this.getClass().getSimpleName() + " eliminado correctamente");
    } catch (SQLException ex) {
        System.err.println("Error al eliminar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
}

public Iterator<Citas> buscar(String busqueda){
    ArrayList<Citas> lasCitas = new ArrayList<>();
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName()
                + " WHERE fechahora::text LIKE ? OR estado LIKE ? OR servicio LIKE ?");
        sql.setString(1, "%" + busqueda + "%");
        sql.setString(2, "%" + busqueda + "%");
        sql.setString(3, "%" + busqueda + "%");
        ResultSet rs = sql.executeQuery();
        Citas unaCita;
        while (rs.next()) {
            unaCita = new Citas();
            unaCita.setIdcitas(rs.getInt("idcitas"));
            unaCita.setFechahora(rs.getObject("fechahora",LocalDateTime.class));
            unaCita.setEstado(rs.getString("estado"));
            unaCita.setServicio(rs.getString("servicio"));
            lasCitas.add(unaCita);
        }
    } catch (SQLException ex) {
        System.err.println("Error al buscar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
    return lasCitas.iterator();
}
public Iterator<Citas> buscarPorUsuario(String busqueda, int idusuario){
    ArrayList<Citas> lasCitas = new ArrayList<>();
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName()
                + " WHERE idusuario = ? AND (fechahora::text LIKE ? OR estado LIKE ? OR servicio LIKE ?)");
        sql.setInt(1, idusuario);
        sql.setString(2, "%" + busqueda + "%");
        sql.setString(3, "%" + busqueda + "%");
        sql.setString(4, "%" + busqueda + "%");
        ResultSet rs = sql.executeQuery();
        Citas unaCita;
        while (rs.next()) {
            unaCita = new Citas();
            unaCita.setIdcitas(rs.getInt("idcitas"));
            unaCita.setFechahora(rs.getObject("fechahora",LocalDateTime.class));
            unaCita.setEstado(rs.getString("estado"));
            unaCita.setServicio(rs.getString("servicio"));
            lasCitas.add(unaCita);
        }
    } catch (SQLException ex) {
        System.err.println("Error al buscar citas por usuario: " + ex.getMessage());
    }
    return lasCitas.iterator();
}
public Iterator<Citas> listarPorUsuario(int idusuario){
    ArrayList<Citas> lasCitas = new ArrayList<>();
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName() + " WHERE idusuario = ?");
        sql.setInt(1, idusuario);
        ResultSet rs = sql.executeQuery();
        Citas unaCita;
        while (rs.next()) {
            unaCita = new Citas();
            unaCita.setIdcitas(rs.getInt("idcitas"));
            unaCita.setFechahora(rs.getObject("fechahora",LocalDateTime.class));
            unaCita.setEstado(rs.getString("estado"));
            unaCita.setServicio(rs.getString("servicio"));
            unaCita.setIdusuario(rs.getInt("idusuario"));
            lasCitas.add(unaCita);
        }
    } catch (SQLException ex) {
        System.err.println("Error al listar citas por usuario: " + ex.getMessage());
    }
    return lasCitas.iterator();
}

public Citas buscarPorId(int elId){
    Citas unaCita = new Citas();
    unaCita.setServicio(" no hay disponibilidad para ese servicio");
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName() + " WHERE idcitas = ?");
        sql.setInt(1, elId);
        ResultSet rs = sql.executeQuery();
        while (rs.next()) {
            unaCita.setIdcitas(rs.getInt("idcitas"));
            unaCita.setFechahora(rs.getObject("fechahora",LocalDateTime.class));
            unaCita.setEstado(rs.getString("estado"));
            unaCita.setServicio(rs.getString("servicio"));
        }
    } catch (SQLException ex) {
        System.err.println("Error al buscar por Id " + ex.getMessage());
    }
    return unaCita;
}

    
}


