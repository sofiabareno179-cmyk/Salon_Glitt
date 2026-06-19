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
public class Promociones {
    private int idpromocion;
    private String titulo;
    private String descripcion;
    private boolean activa;
    private LocalDateTime updatedAt;

    public int getIdpromocion() {
        return idpromocion;
    }

    public void setIdpromocion(int idpromociones) {
        this.idpromocion = idpromocion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Promociones{" + "titulo=" + titulo + '}';
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
        final Promociones other = (Promociones) obj;
        return this.idpromocion == other.idpromocion;
    }
    
  public Iterator<Promociones> listar(){
    ArrayList<Promociones> lasPromociones = new ArrayList<>();
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName());
        ResultSet rs = sql.executeQuery();
        Promociones unaPromocion;
        while (rs.next()) {
            unaPromocion = new Promociones();
            unaPromocion.setIdpromocion(rs.getInt("idpromocion"));
            unaPromocion.setTitulo(rs.getString("titulo"));
            unaPromocion.setDescripcion(rs.getString("descripcion"));
            unaPromocion.setActiva(rs.getBoolean("estado"));
            unaPromocion.setUpdatedAt(rs.getObject("updateat",LocalDateTime.class));
            lasPromociones.add(unaPromocion);
        }
    } catch (SQLException ex) {
        System.err.println("Error al listar las promociones : " + ex.getMessage());
    }
    if (lasPromociones.isEmpty()){
        Promociones promo = new Promociones();
        promo.setTitulo("No hay promocion existente");
        lasPromociones.add(promo);
    }
    return lasPromociones.iterator();
}
   
    public void insertar(){
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("INSERT INTO "
                + this.getClass().getSimpleName() + " VALUES(NULL,?,?,?,?)");
        sql.setString(1, this.getTitulo());
        sql.setString(2, this.getDescripcion());
        sql.setBoolean(3, this.isActiva());
        sql.setObject(4, this.getUpdatedAt());
        sql.executeUpdate();
        System.out.println(this.getClass().getSimpleName() + " insertado correctamente");
    } catch (SQLException ex) {
        System.err.println("Error al insertar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
}

public void modificar(){
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("UPDATE " + this.getClass().getSimpleName() + 
                " SET titulo = ?, descripcion = ?, activa = ? ,udpadteat = ? WHERE idpromocion = ?");
        sql.setString(1, this.getTitulo());
        sql.setString(2, this.getDescripcion());
        sql.setBoolean(3, this.isActiva());
        sql.setObject(4, this.getUpdatedAt());
        sql.executeUpdate();
        System.out.println(this.getClass().getSimpleName() + " modificado correctamente");
    } catch (SQLException ex) {
        System.err.println("Error al modificar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
}
public void eliminar(){
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("DELETE FROM "
                + this.getClass().getSimpleName() + " WHERE idpromocion = ?");
        sql.setInt(1, this.getIdpromocion());
        sql.executeUpdate();
        System.out.println(this.getClass().getSimpleName() + " eliminado correctamente");
    } catch (SQLException ex) {
        System.err.println("Error al eliminar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
}

public Iterator<Promociones> buscar(String busqueda){
    ArrayList<Promociones> lasPromociones = new ArrayList<>();
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName()
                + " WHERE titulo LIKE ? OR descripcion LIKE ? OR activa LIKE ? OR updateat LIKE ?");
        sql.setString(1, "%" + busqueda + "%");
        sql.setString(2, "%" + busqueda + "%");
        sql.setString(3, "%" + busqueda + "%");
        sql.setString(4, "%" + busqueda + "%");
        ResultSet rs = sql.executeQuery();
        Promociones unaPromocion;
        while (rs.next()) {
            unaPromocion = new Promociones();
            unaPromocion.setIdpromocion(rs.getInt("idpromocion"));
            unaPromocion.setTitulo(rs.getString("titulo"));
            unaPromocion.setDescripcion(rs.getString("descripcion"));
            unaPromocion.setActiva(rs.getBoolean("estado"));
            unaPromocion.setUpdatedAt(rs.getObject("updateat",LocalDateTime.class));
            lasPromociones.add(unaPromocion);
        }
    } catch (SQLException ex) {
        System.err.println("Error al buscar " + this.getClass().getSimpleName() + ": " + ex.getMessage());
    }
    return lasPromociones.iterator();
}
public Promociones buscarPorId(int elId){
    Promociones unaPromocion = new Promociones();
    unaPromocion.setTitulo(" no hay promocion existente");
    try {
        PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName() + " WHERE idpromocion = ?");
        sql.setInt(1, elId);
        ResultSet rs = sql.executeQuery();
        while (rs.next()) {
            unaPromocion = new Promociones();
            unaPromocion.setIdpromocion(rs.getInt("idpromocion"));
            unaPromocion.setTitulo(rs.getString("titulo"));
            unaPromocion.setDescripcion(rs.getString("descripcion"));
            unaPromocion.setActiva(rs.getBoolean("estado"));
            unaPromocion.setUpdatedAt(rs.getObject("servicio",LocalDateTime.class));
        }
    } catch (SQLException ex) {
        System.err.println("Error al buscar por Id " + ex.getMessage());
    }
    return unaPromocion;
}

    
}
