package com.progra.countries.resources;

import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import com.progra.countries.logic.Country;
import com.progra.countries.logic.Service;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PathParam;

@Path("/countries")
@PermitAll
public class Countries {
    
    @GET
    @Path("{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public Country read(@PathParam("name") String name) {
        try {
            return Service.instance().read(name);
        } catch (Exception ex) {
            throw new NotFoundException(); 
        }
    } 
    
    @DELETE
    @Path("{name}")
    public void delete(@PathParam("name") String name) {
        Service.instance().delete(name);
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void add(Country c){
        Service.instance().add(c.getName());
    }
}
