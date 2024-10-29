package com.progra.countries.logic;

import java.io.Serializable;

public class Usuario implements Serializable{
    private String id;
    private String clave;
    private Integer tipo;

    public Usuario() {
        id = "";
        clave = "";
        tipo = 0;
    }

    public Usuario(String id, String clave, Integer tipo) {
        this.id = id;
        this.clave = clave;
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
}
