package com.progra.countries.data;

import com.progra.countries.logic.Cobertura;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoberturaDao {
    RelDatabase db;

    public CoberturaDao(RelDatabase db){
        this.db= db;
    }
    
    public Cobertura read(int id) throws Exception {
        String sql = "select " +
                "* " +
                "from Cobertura c " +
                "where c.id=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = db.executeQuery(stm);
        Cobertura c;
        if (rs.next()) {
            c = from(rs, "c");
            return c;
        } else {
            throw new Exception("Cobertura no Existe");
        }
    }
    
    public List<Cobertura> findAll() throws Exception {
        List<Cobertura> resultado = new ArrayList<>();
        try {
            String sql = "select " +
                "* " +
                "from Cobertura e inner join Categoria u on e.categoria=u.id";
            PreparedStatement stm = db.prepareStatement(sql);
            ResultSet rs = db.executeQuery(stm);
            CategoriaDao categoriaDao = new CategoriaDao(db);
            Cobertura c;
            while (rs.next()) {
                c = from(rs, "e");
                c.setCategoria(categoriaDao.from(rs,"u"));
                resultado.add(c);
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }
    
    public Cobertura from(ResultSet rs, String alias) {
        try {
            Cobertura e = new Cobertura();
            e.setId(rs.getInt(alias + ".id"));
            e.setDescripcion(rs.getString(alias + ".descripcion"));
            e.setCosto_minimo(rs.getInt(alias + ".costo_minimo"));
            e.setCosto_porcentual(rs.getInt(alias + ".costo_porcentual"));
            return e;
        } catch (SQLException ex) {
            return null;
        }
    }
}
