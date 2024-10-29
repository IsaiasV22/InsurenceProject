package com.progra.countries.data;

import com.progra.countries.logic.Poliza;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PolizaDao {
    RelDatabase db;

    public PolizaDao(RelDatabase db){
        this.db= db;
    }
    
    public Poliza read(int id) throws Exception {
        String sql = "select " +
                "* " +
                "from Poliza e inner join Cliente c on e.cliente=c.cedula " +
                "              inner join Marca m on e.marca=m.id " +
                "              inner join Modelo mo on e.modelo=mo.id " +
                "where e.id=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = db.executeQuery(stm);
        ClienteDao clienteDao = new ClienteDao(db);
        MarcaDao marcaDao = new MarcaDao(db);
        ModeloDao modeloDao = new ModeloDao(db);
        Poliza c;
        if (rs.next()) {
            c = from(rs, "e");
            c.setCliente(clienteDao.from(rs, "c"));
            c.setMarca(marcaDao.from(rs, "m"));
            c.setModelo(modeloDao.from(rs, "mo"));
            return c;
        } else {
            throw new Exception("Poliza no Existe");
        }
    }
    
    public List<Poliza> findByCliente(String clienteCedula) {
        List<Poliza> resultado = new ArrayList<>();
        Poliza poliza;
        ModeloDao modeloDao = new ModeloDao(db);
        try {
            String sql = "select * " +
                    "from " +
                    "Poliza e inner join Modelo m on e.modelo=m.id " +
                    "where e.cliente=?";
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setString(1, clienteCedula);
            ResultSet rs = db.executeQuery(stm);
            while (rs.next()) {
                poliza = from(rs,"e");
                poliza.setModelo(modeloDao.from(rs, "m"));
                resultado.add(poliza);
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }
    
    public Poliza from(ResultSet rs, String alias) {
        try {
            Poliza e = new Poliza();
            e.setId(rs.getInt(alias + ".id"));
            e.setNum_Placa(rs.getString(alias + ".num_Placa"));
            e.setAnio(rs.getInt(alias + ".anio"));
            e.setValor_asegurado(rs.getInt(alias + ".valor_asegurado"));
            e.setPlazos_pago(rs.getString(alias + ".plazos_pago"));
            e.setFecha(rs.getString(alias + ".fecha"));
            return e;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public int add(Poliza poliza) throws Exception{
        String sql = "insert into Poliza (num_Placa,marca,modelo,anio,valor_asegurado,plazos_pago,fecha,cliente) values (?,?,?,?,?,?,?,?) ";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, poliza.getNum_Placa());
        stm.setInt(2, poliza.getMarca().getId());
        stm.setInt(3, poliza.getModelo().getId());
        stm.setInt(4, poliza.getAnio());
        stm.setInt(5, poliza.getValor_asegurado());
        stm.setString(6, poliza.getPlazos_pago());
        stm.setString(7, poliza.getFecha());
        stm.setString(8, poliza.getCliente().getCedula());
        int count = db.executeUpdate(stm);
        ResultSet keys = stm.getGeneratedKeys();
        keys.next();
        int key = keys.getInt(1);
        System.out.println(key);
        return key;
    }
}
