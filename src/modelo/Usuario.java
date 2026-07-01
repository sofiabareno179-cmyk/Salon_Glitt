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
import org.mindrot.jbcrypt.BCrypt;
/**
 *
 * @author LENOVO
 */
public class Usuario {
    private int idUsuario;
    private String nombreuser;
    private String email;
    private String password_hash;
    private String telefono;
    private String rol;
            
    
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

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
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
                unUsuario.setPassword_hash(rs.getString("password_hash"));
                unUsuario.setTelefono(rs.getString("telefono"));
                unUsuario.setRol(rs.getString("rol"));
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
    public int insertar(){
        try{
            String nombreTabla = this.getClass().getSimpleName().toLowerCase();
            String query = "INSERT INTO " + nombreTabla + " (nombreuser, email,password_hash, telefono,rol) VALUES(?, ?, ?,?,'cliente') RETURNING idUsuario";
            PreparedStatement sql = ConexionBD.conexion.prepareStatement(query);
            sql.setString(1, this.getNombreuser());
            sql.setString(2, this.getEmail());
            String textoPlanoPassword = this.getPassword_hash();
            String passwordConHash = BCrypt.hashpw(textoPlanoPassword, BCrypt.gensalt());
            sql.setString(3,passwordConHash);
            sql.setString(4, this.getTelefono());
            ResultSet rs = sql.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                System.out.println(this.getClass().getSimpleName() + " insertado correctamente con id=" + id);
                return id;
            }
        } catch(SQLException ex){
            System.err.println("Error al insertar " + this.getClass().getSimpleName() + ":" + ex.getMessage());
        }
        return -1;
    }
    public void modificar(){
        try{
            String passwordHash = this.getPassword_hash();
            if (passwordHash == null || passwordHash.isEmpty()) {
                PreparedStatement sql = ConexionBD.conexion.prepareStatement("UPDATE " + this.getClass().getSimpleName() + " SET nombreuser = ?, email = ?, telefono = ? WHERE idUsuario = ?");
                sql.setString(1, this.getNombreuser());
                sql.setString(2, this.getEmail());
                sql.setString(3, this.getTelefono());
                sql.setInt(4, this.getIdUsuario());
                sql.executeUpdate();
            } else {
                PreparedStatement sql = ConexionBD.conexion.prepareStatement("UPDATE " + this.getClass().getSimpleName() + " SET nombreuser = ?, email = ?, telefono = ?, password_hash = ? WHERE idUsuario = ?");
                sql.setString(1, this.getNombreuser());
                sql.setString(2, this.getEmail());
                sql.setString(3, this.getTelefono());
                sql.setString(4, BCrypt.hashpw(passwordHash, BCrypt.gensalt()));
                sql.setInt(5, this.getIdUsuario());
                sql.executeUpdate();
            }
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
                unUsuario.setIdUsuario(rs.getInt("idUsuario"));
                unUsuario.setNombreuser(rs.getString("nombreuser"));
                unUsuario.setEmail(rs.getString("email"));
                unUsuario.setPassword_hash(rs.getString("password_hash"));
                unUsuario.setTelefono(rs.getString("telefono"));
                unUsuario.setRol(rs.getString("rol"));
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
                unUsuario.setIdUsuario(rs.getInt("idUsuario"));
                unUsuario.setNombreuser(rs.getString("nombreuser"));
                unUsuario.setEmail(rs.getString("email"));
                unUsuario.setTelefono(rs.getString("telefono"));
                unUsuario.setRol(rs.getString("rol"));
            }
        } catch (SQLException ex) {
            System.err.println("Error al buscar por Id " + ex.getMessage());
        }
        return unUsuario;
    }

    public static Usuario autenticar(String identificador, String password) {
        try {
            PreparedStatement sql = ConexionBD.conexion.prepareStatement(
                "SELECT * FROM Usuario WHERE email = ? OR nombreuser = ?");
            sql.setString(1, identificador);
            sql.setString(2, identificador);
            ResultSet rs = sql.executeQuery();
            if (rs.next()) {
                String hash = rs.getString("password_hash");
                if (hash == null || !hash.matches("^\\$2[aby]\\$\\d{2}\\$[A-Za-z0-9./]{53}$")) {
                    System.err.println("Hash inválido en BD para usuario: " + identificador);
                    return null;
                }
                if (BCrypt.checkpw(password, hash)) {
                    Usuario u = new Usuario();
                    u.setIdUsuario(rs.getInt("idUsuario"));
                    u.setNombreuser(rs.getString("nombreuser"));
                    u.setEmail(rs.getString("email"));
                    u.setTelefono(rs.getString("telefono"));
                    u.setPassword_hash(hash);
                    u.setRol(rs.getString("rol"));
                    return u;
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error en autenticación: " + ex.getMessage());
        }
        return null;
    }
}
