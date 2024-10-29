package com.progra.countries.data;

import com.progra.countries.logic.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDao {
    RelDatabase db;
    
    public UsuarioDao(RelDatabase db){
        this.db= db;
    }
    
    public Usuario read(String id) throws Exception {
        String sql = "select " +
                "* " +
                "from  Usuario e " +
                "where e.id=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, id);
        ResultSet rs = db.executeQuery(stm);
        if (rs.next()) {
            return from(rs, "e");
        } else {
            throw new Exception("Empleado no Existe");
        }
    }
    
    public Usuario from(ResultSet rs, String alias) {
        try {
            Usuario e = new Usuario();
            e.setId(rs.getString(alias + ".id"));
            e.setClave(rs.getString(alias + ".clave"));
            e.setTipo(rs.getInt(alias + ".tipo"));            
            return e;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public void add(Usuario usuario) throws Exception{
        String sql = "insert into Usuario (id,clave,tipo) values (?,?,?) ";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, usuario.getId());
        stm.setString(2, usuario.getClave());
        stm.setInt(3, usuario.getTipo());
        db.executeUpdate(stm);
    }
}
