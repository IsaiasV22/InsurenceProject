package com.progra.countries.logic;

import com.progra.countries.data.CategoriaDao;
import com.progra.countries.data.ClienteDao;
import com.progra.countries.data.CoberturaDao;
import com.progra.countries.data.MarcaDao;
import com.progra.countries.data.ModeloDao;
import com.progra.countries.data.PolizaCoberturaDao;
import com.progra.countries.data.PolizaDao;
import com.progra.countries.data.RelDatabase;
import com.progra.countries.data.UsuarioDao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Service {
    private static Service uniqueInstance;
    
    public static Service instance(){
        if (uniqueInstance == null){
            uniqueInstance = new Service();
        }
        return uniqueInstance; 
    }
    
    RelDatabase relDatabase;
    UsuarioDao usuarioDao;
    ClienteDao clienteDao;
    MarcaDao marcaDao;
    ModeloDao modeloDao;
    PolizaDao polizaDao;
    CategoriaDao categoriaDao;
    CoberturaDao coberturaDao;
    PolizaCoberturaDao polizaCoberturaDao;

    HashMap<String,Country> countries;
    
    private Service(){
        relDatabase = new RelDatabase();
        usuarioDao = new UsuarioDao(relDatabase);
        clienteDao = new ClienteDao(relDatabase);
        marcaDao = new MarcaDao(relDatabase);
        modeloDao = new ModeloDao(relDatabase);
        polizaDao = new PolizaDao(relDatabase);
        categoriaDao = new CategoriaDao(relDatabase);
        coberturaDao = new CoberturaDao(relDatabase);
        polizaCoberturaDao = new PolizaCoberturaDao(relDatabase);
        
        countries = new HashMap();
        Country c;
        c=new Country("Argentina", "Buenos Aires", 43590400, 2780400, new ArrayList<>(Arrays.asList((new Integer[]{-34,-64}))), "https://flagcdn.com/ar.svg");
        countries.put(c.name, c);

        c=new Country("Belize", "Belmopan", 370300, 22966, new ArrayList<>(Arrays.asList(new Integer[]{17,-88})), "https://flagcdn.com/bo.svg");
        countries.put(c.name, c);
    }
    
    public Usuario usuarioFind(String id) throws Exception{
        return usuarioDao.read(id);
    }
    
    public void usuarioAdd(Usuario usuario) throws Exception{
        usuarioDao.add(usuario);
    }
    
    public Cliente clienteFind(String id) throws Exception{
        return clienteDao.read(id);
    }
    
    public void clienteUpdate(Cliente cliente) throws Exception{
        clienteDao.update(cliente);
    }
    
    public void clienteAdd(Cliente cliente) throws Exception{
        clienteDao.add(cliente);
    }
    
    public List<Marca> marcasFind() throws Exception{
        return marcaDao.read();
    }
    
    public Modelo modeloFindId(int idModelo) throws Exception{
        return modeloDao.readId(idModelo);
    }
    
    public List<Modelo> modelosFind() throws Exception{
        return modeloDao.read();
    }
    
    public Poliza polizaFind(int id) throws Exception{
        return polizaDao.read(id);
    }
    
    public List<Poliza> polizasFind(String cedula) throws Exception{
        return polizaDao.findByCliente(cedula);
    }
    
    public int polizaAdd(Poliza poliza) throws Exception{
        return polizaDao.add(poliza);
    }
    
    public List<Categoria> categoriasFindAll() throws Exception{
        return categoriaDao.findAll();
    }
    
    public Cobertura coberturaFinf(int idCobertura) throws Exception{
        return coberturaDao.read(idCobertura);
    }
    
    public List<Cobertura> coberturasFindAll() throws Exception{
        return coberturaDao.findAll();
    }
    
    public List<Cobertura> coberturasFind(int idPoliza) throws Exception{
        return polizaCoberturaDao.findCoberturas(idPoliza);
    }
    
    public void polizaCoberturaAdd(int key,List<Cobertura> coberturas) throws Exception{
        polizaCoberturaDao.add(key,coberturas);
    }

    public Country read(String name)throws Exception{
        Country c = countries.get(name);
        if (c!=null) return c;
        else throw new Exception("Country does not exist");
    }

    public List<Poliza> find(String patron, String cedula)throws Exception{
        List<Poliza> polizas = this.polizasFind(cedula);
        HashMap<String,Poliza> mapPolizas = new HashMap();
        
        polizas.forEach(p->mapPolizas.put(String.valueOf(p.getId()), p));
        
        return mapPolizas.values().stream().
                filter( p-> p.getNum_Placa().contains(patron)).
                collect(Collectors.toList());
    }
    
    public void delete(String name){
        countries.remove(name);
    }
    
    public void add(String name){
        Country c;
        c=new Country(name, "", 0, 0, new ArrayList<>(Arrays.asList((new Integer[]{0,0}))), "");
        countries.put(c.name, c);
    }
}
