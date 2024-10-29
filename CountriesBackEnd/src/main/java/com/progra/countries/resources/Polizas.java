package com.progra.countries.resources;

import com.progra.countries.logic.Modelo;
import com.progra.countries.logic.Poliza;
import com.progra.countries.logic.Service;
import com.progra.countries.logic.Usuario;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotAcceptableException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/polizas")
@PermitAll
public class Polizas {
    @Context
    HttpServletRequest request;
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"1","2"})
    public Poliza read(@PathParam("id") int id) {
        Usuario user=(Usuario) request.getSession(true).getAttribute("user");
        Poliza p;
        try {
            p = Service.instance().polizaFind(id);
            if(p.getCliente().getCedula().equals(user.getId())){
                return p;
            }else
                throw new NotFoundException();
        } catch (Exception ex) {
            throw new NotFoundException();
        } 
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"1","2"})
    public List<Poliza> find(@DefaultValue("") @QueryParam("placa") String placa) { 
        Usuario user=(Usuario) request.getSession(true).getAttribute("user");
        try {
                return Service.instance().find(placa, user.getId());
            } catch (Exception ex) {
                throw new NotFoundException(); 
            }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"1","2"})
    public void add(Poliza p){
        p.setFecha(String.valueOf(java.time.LocalDate.now()));
        Modelo modelo;
        try{
            modelo = Service.instance().modeloFindId(p.getModelo().getId());
            p.setMarca(modelo.getMarca());
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new NotAcceptableException();
        }
        
        int key;
        try {
            key = Service.instance().polizaAdd(p);
            Service.instance().polizaCoberturaAdd(key, p.getCoberturas());
        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }
}
