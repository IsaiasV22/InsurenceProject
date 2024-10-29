package com.progra.countries.resources;

import com.progra.countries.logic.Cobertura;
import com.progra.countries.logic.Service;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/coberturas")
@PermitAll
public class Coberturas {
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"1","2"})
    public List<Cobertura> read(@PathParam("id") int id) {
        try {
            return Service.instance().coberturasFind(id);
        } catch (Exception ex) {
            System.out.print(ex);
            throw new NotFoundException(); 
        }
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"1","2"})
    public List<Cobertura> find() { 
        try {
            return Service.instance().coberturasFindAll();
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
}
