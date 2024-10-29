package com.progra.countries.logic;

import java.io.Serializable;

public class Modelo implements Serializable{
    private int id;
    private String descripcion;
    private Marca marca;

    public Modelo() {
        this.id = 0;
        this.descripcion = "";
        this.marca = new Marca();
    }

    public Modelo(int id, String descripcion, Marca marca) {
        this.id = id;
        this.descripcion = descripcion;
        this.marca = marca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Modelo other = (Modelo) obj;
        return this.id == other.id;
    }
}
