package com.progra.countries.data;

import com.progra.countries.logic.Categoria;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDao {
    RelDatabase db;

    public CategoriaDao(RelDatabase db){
        this.db= db;
    }
    
    public Categoria read(String descripcion) throws Exception {
        String sql = "select " +
                "* " +
                "from Categoria e " +
                "where e.descripcion=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, descripcion);
        ResultSet rs = db.executeQuery(stm);
        if (rs.next()) {
            return from(rs, "e");
        } else {
            throw new Exception("Categoria no Existe");
        }
    }
    
    public List<Categoria> findAll() throws Exception {
        List<Categoria> resultado = new ArrayList<>();
        try {
            String sql = "select * " +
                    "from " +
                    "Categoria e";
            PreparedStatement stm = db.prepareStatement(sql);
            ResultSet rs = db.executeQuery(stm);
            while (rs.next()) {
                resultado.add(from(rs, "e"));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }
    
    public Categoria from(ResultSet rs, String alias) {
        try {
            Categoria e = new Categoria();
            e.setId(rs.getInt(alias + ".id"));
            e.setDescripcion(rs.getString(alias + ".descripcion"));            
            return e;
        } catch (SQLException ex) {
            return null;
        }
    }
}
