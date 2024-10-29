package com.progra.countries.data;

import com.progra.countries.logic.Modelo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModeloDao {
    RelDatabase db;

    public ModeloDao(RelDatabase db){
        this.db= db;
    }
    
    public List<Modelo> read() throws Exception {
        List<Modelo> resultado = new ArrayList<>();
        try {
            String sql = "select " +
                "* " +
                "from Modelo e inner join Marca u on e.marca=u.id";
            PreparedStatement stm = db.prepareStatement(sql);
            ResultSet rs = db.executeQuery(stm);
            MarcaDao marcaDao = new MarcaDao(db);
            Modelo c;
            while (rs.next()) {
                c = from(rs, "e");
                c.setMarca(marcaDao.from(rs,"u"));
                resultado.add(c);
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }
    
    public Modelo readId(int idModelo) throws Exception {
        String sql = "select " +
                "* " +
                "from Modelo e inner join Marca u on e.marca=u.id " +
                "where e.id=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setInt(1, idModelo);
        ResultSet rs = db.executeQuery(stm);
        MarcaDao marcaDao = new MarcaDao(db);
        Modelo c;
        if (rs.next()) {
            c = from(rs, "e");
            c.setMarca(marcaDao.from(rs, "u"));
            return c;
        } else {
            throw new Exception("Modelo no Existe");
        }
    }
    
    public Modelo from(ResultSet rs, String alias) {
        try {
            Modelo e = new Modelo();
            e.setId(rs.getInt(alias + ".id"));
            e.setDescripcion(rs.getString(alias + ".descripcion"));
            return e;
        } catch (SQLException ex) {
            return null;
        }
    }
}
