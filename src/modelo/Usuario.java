/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author LENOVO
 */
public class Usuario {
    private int idUsuario;
    private String nombreuser;
    private String email;
    private String telefono;
    
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreuser() {
        return nombreuser;
    }

    public void setNombreuser(String nombreuser) {
        this.nombreuser = nombreuser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }
    
    @Override
    public String toString() {
        return "Usuario{" + "nombreuser=" + nombreuser + '}';
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
        final Usuario other = (Usuario) obj;
        return this.idUsuario == other.idUsuario;
    }

     public Iterator<Usuario> listar(){
        ArrayList<Usuario> losUsuarios = new ArrayList<>();
        try{
            PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName());
            ResultSet rs = sql.executeQuery();
            Usuario unUsuario;
            while (rs.next()){
                unUsuario = new Usuario();
                unUsuario.setIdUsuario(rs.getInt("idUsuario"));
                unUsuario.setNombreuser(rs.getString("nombreuser"));
                unUsuario.setEmail(rs.getString("email"));
                unUsuario.setTelefono( rs.getString("telefono"));
                losUsuarios.add(unUsuario);
            }
        }catch(SQLException ex){
            System.err.println("Error al listar Usuarios:" + ex.getMessage());
        }
        if(losUsuarios.isEmpty()){
            Usuario miUsuario = new Usuario();
            miUsuario.setNombreuser("No hay nombres");
            losUsuarios.add(miUsuario);
        }
        return losUsuarios.iterator();
    }    
    public void insertar(){
        try{
            PreparedStatement sql = ConexionBD.conexion.prepareStatement("INSERT INTO "
            + this.getClass().getSimpleName() + " VALUES(NULL,?,?,?)");
            sql.setString(1, this.getNombreuser());
            sql.setString(2, this.getEmail());
            sql.setString(3, this.getTelefono());
            sql.executeUpdate();
            System.out.println(this.getClass().getSimpleName() + " insertado correctamente");
        } catch(SQLException ex){
            System.err.println("Error al insertar " + this.getClass().getSimpleName() + ":" + ex.getMessage());
        }
    }
       public void modificar(){
        try{
            PreparedStatement sql = ConexionBD.conexion.prepareStatement("UPDATE " + this.getClass().getSimpleName() + " SET nombreuser = ?, email = ?, telefono = ? WHERE idUsuario = ?");
            sql.setString(1, this.getNombreuser());
            sql.setString(2, this.getEmail());
            sql.setString(3, this.getTelefono());
            sql.setInt(4, this.getIdUsuario());
            sql.executeUpdate();
            System.out.println(this.getClass().getSimpleName() + " modificado correctamente");
        }catch(SQLException ex){
            System.err.println("Error al modificar " + this.getClass().getSimpleName() + ":" + ex.getMessage());
        }
    }
    public void eliminar(){
        try{
            PreparedStatement sql = ConexionBD.conexion.prepareStatement("DELETE FROM " + this.getClass().getSimpleName() + " WHERE idUsuario = ?");
            sql.setInt(1, this.getIdUsuario());
            sql.executeUpdate();
            System.out.println(this.getClass().getSimpleName() + " eliminado correctamente");
        }catch(SQLException ex){
            System.err.println("Error al eliminar " + this.getClass().getSimpleName() + ":" + ex.getMessage());
        }
    }
    public Iterator<Usuario> buscar(String busqueda){
        ArrayList<Usuario> losUsuarios = new ArrayList<>();
        try{
            PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName()
            + " WHERE nombreuser LIKE ? OR email LIKE ? OR telefono LIKE ? ");
            sql.setString(1, "%" + busqueda + "%");
            sql.setString(2, "%" + busqueda + "%");
            sql.setString(3, "%" + busqueda + "%");
            
            ResultSet rs = sql.executeQuery();
            Usuario unUsuario;
            while (rs.next()) {
                unUsuario = new Usuario();
                unUsuario.setIdUsuario(   rs.getInt   ("idUsuario"));
                unUsuario.setNombreuser( rs.getString("nombreuser"));
                unUsuario.setEmail(rs.getString("email"));
                unUsuario.setTelefono(  rs.getString("telefono")); 
                losUsuarios.add(unUsuario);
            }
        } catch (SQLException ex) {
            System.err.println("Error al buscar " + this.getClass().getSimpleName() + ":" + ex.getMessage());
        }
        return losUsuarios.iterator();
    }
    public Usuario buscarPorId(int elId){
        Usuario unUsuario = new Usuario();
        unUsuario.setNombreuser("Nombre de Usuario no existe");
        try {
            PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM "+
                    this.getClass().getSimpleName()+" WHERE idUsuario = ?");
            sql.setInt(1, elId);
            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                unUsuario.setIdUsuario(         rs.getInt   ("idUsuario"));
                unUsuario.setNombreuser(     rs.getString("nombreuser"));
                unUsuario.setEmail(rs.getString("email"));
                unUsuario.setTelefono(   rs.getString("telefono"));
            }
        } catch (SQLException ex) {
            System.err.println("Error al buscar por Id " + ex.getMessage());
        }
        return unUsuario;
    }
 
}
