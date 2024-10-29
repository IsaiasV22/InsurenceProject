package com.progra.countries.resources;

import com.progra.countries.logic.Cliente;
import com.progra.countries.logic.Service;
import com.progra.countries.logic.Usuario;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotAcceptableException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;

@Path("/clientes")
@PermitAll
public class Clientes {
    @Context
    HttpServletRequest request;
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"1","2"})
    public Cliente read(@PathParam("id") String id) {
        Usuario user=(Usuario) request.getSession(true).getAttribute("user");
        if(user.getId().equals(id)){
            try {
                return Service.instance().clienteFind(id);
            } catch (Exception ex) {
                throw new NotFoundException(); 
            }
        } else
            throw new NotFoundException();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void add(Cliente c){
        Usuario u = new Usuario();
        u.setId(c.getCedula());
        u.setClave(c.getClave());
        u.setTipo(1);
        c.setUsuario(u);
        try {
            Service.instance().usuarioAdd(u);
            Service.instance().clienteAdd(c);
        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"1","2"})
    public void update(Cliente c){
        Usuario user=(Usuario) request.getSession(true).getAttribute("user");
        if(user.getId().equals(c.getCedula())){
            try {
                Service.instance().clienteUpdate(c);
            } catch (Exception ex) {
                System.out.print(ex);
                throw new NotAcceptableException();
            }
        }else
            throw new NotAcceptableException();
    }
}
