package com.progra.countries.data;

import com.progra.countries.logic.Marca;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarcaDao {
    RelDatabase db;

    public MarcaDao(RelDatabase db){
        this.db= db;
    }
    
    public List<Marca> read() throws Exception {
        List<Marca> resultado = new ArrayList<>();
        try {
            String sql = "select * " +
                    "from " +
                    "Marca e";
            PreparedStatement stm = db.prepareStatement(sql);
            ResultSet rs = db.executeQuery(stm);
            while (rs.next()) {
                resultado.add(from(rs, "e"));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }
    
    public Marca from(ResultSet rs, String alias) {
        try {
            Marca e = new Marca();
            e.setId(rs.getInt(alias + ".id"));
            e.setDescripcion(rs.getString(alias + ".descripcion"));            
            return e;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }
}
