package com.progra.countries.resources;

import com.progra.countries.logic.Service;
import com.progra.countries.logic.Usuario;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/usuarios")
@PermitAll
public class Usuarios {
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"1","2"})
    public Usuario find(@QueryParam("id") String id) {
        try{
            return Service.instance().usuarioFind(id);
        }catch(Exception ex){
            throw new NotFoundException();
        }
    }
}
