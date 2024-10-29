package com.progra.countries.data;

import com.progra.countries.logic.Cobertura;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PolizaCoberturaDao {
    RelDatabase db;

    public PolizaCoberturaDao(RelDatabase db){
        this.db= db;
    }
    
    public List<Cobertura> findCoberturas(int polizaId) throws Exception {
        List<Cobertura> resultado = new ArrayList<>();
        Cobertura cobertura;
        CoberturaDao coberturaDao = new CoberturaDao(db);
        
        try {
            String sql = "select " +
                "* " +
                "from PolizaCobertura pc inner join Cobertura c on pc.cobertura=c.id " +
                "where pc.poliza=?";
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setInt(1, polizaId);
            ResultSet rs = db.executeQuery(stm);
            while (rs.next()) {
                cobertura = coberturaDao.from(rs,"c");
                resultado.add(cobertura);
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }
    
    public void add(int key,List<Cobertura> coberturas) throws Exception{
        for(Cobertura c:coberturas){
            String sql = "insert into PolizaCobertura (poliza,cobertura) values (?,?) ";
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setInt(1, key);
            stm.setInt(2, c.getId());
            db.executeUpdate(stm);
        }
    }
}
