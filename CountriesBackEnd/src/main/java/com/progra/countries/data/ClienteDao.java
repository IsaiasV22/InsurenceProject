package com.progra.countries.data;

import com.progra.countries.logic.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDao {
    RelDatabase db;

    public ClienteDao(RelDatabase db){
        this.db= db;
    }
    
    public Cliente read(String cedula) throws Exception {
        String sql = "select " +
                "* " +
                "from Cliente e inner join Usuario u on e.usuario=u.id " +
                "where e.cedula=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, cedula);
        ResultSet rs = db.executeQuery(stm);
        UsuarioDao usuarioDao = new UsuarioDao(db);
        Cliente c;
        if (rs.next()) {
            c= from(rs, "e");
            c.setUsuario(usuarioDao.from(rs,"u"));
            return c;
        } else {
            throw new Exception("Empleado no Existe");
        }
    }
    
    public Cliente from(ResultSet rs, String alias) {
        try {
            Cliente e = new Cliente();
            e.setCedula(rs.getString(alias + ".cedula"));
            e.setClave(rs.getString(alias + ".clave"));
            e.setNombre(rs.getString(alias + ".nombre"));
            e.setTelefono(rs.getString(alias + ".telefono"));
            e.setCorreo(rs.getString(alias + ".correo"));
            e.setTarjeta(rs.getString(alias + ".tarjeta"));
            return e;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public void update(Cliente e) throws Exception {
        String sql = "update " +
                "Cliente set " +
                "nombre=?, " +
                "telefono=?, " +
                "correo=?, " +
                "tarjeta=? " +
                "where cedula=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getNombre());
        stm.setString(2, e.getTelefono());
        stm.setString(3, e.getCorreo());
        stm.setString(4, e.getTarjeta());
        stm.setString(5, e.getCedula());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Cliente no existe");
        }
    }
    
    public void add(Cliente cliente) throws Exception{
        String sql = "insert into Cliente (cedula,clave,nombre,telefono,correo,tarjeta,usuario) values (?,?,?,?,?,?,?) ";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, cliente.getCedula());
        stm.setString(2, cliente.getClave());
        stm.setString(3, cliente.getNombre());
        stm.setString(4, cliente.getTelefono());
        stm.setString(5, cliente.getCorreo());
        stm.setString(6, cliente.getTarjeta());
        stm.setString(7, cliente.getUsuario().getId());
        db.executeUpdate(stm);
    }
}
