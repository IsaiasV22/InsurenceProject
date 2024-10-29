package com.progra.countries.resources;

import com.progra.countries.logic.Categoria;
import com.progra.countries.logic.Service;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/categorias")
@PermitAll
public class Categorias {
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"1","2"})
    public List<Categoria> find() { 
        try {
            return Service.instance().categoriasFindAll();
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
}
