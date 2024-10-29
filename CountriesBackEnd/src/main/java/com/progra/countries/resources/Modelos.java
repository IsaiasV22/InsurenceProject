package com.progra.countries.resources;

import com.progra.countries.logic.Modelo;
import com.progra.countries.logic.Service;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/modelos")
@PermitAll
public class Modelos {
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"1","2"})
    public List<Modelo> find() { 
        try {
            return Service.instance().modelosFind();
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
}