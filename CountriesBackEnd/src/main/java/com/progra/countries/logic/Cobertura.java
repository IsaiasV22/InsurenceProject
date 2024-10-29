package com.progra.countries.logic;

import java.io.Serializable;

public class Cobertura implements Serializable{
    private int id;
    private String descripcion;
    private int costo_minimo;
    private int costo_porcentual;
    private Categoria categoria;

    public Cobertura() {
        this.id = 0;
        this.descripcion = "";
        this.costo_minimo = 0;
        this.costo_porcentual = 0;
        this.categoria = new Categoria();
    }

    public Cobertura(int id, String descripcion, int costo_minimo, int costo_porcentual, Categoria categoria) {
        this.id = id;
        this.descripcion = descripcion;
        this.costo_minimo = costo_minimo;
        this.costo_porcentual = costo_porcentual;
        this.categoria = categoria;
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

    public int getCosto_minimo() {
        return costo_minimo;
    }

    public void setCosto_minimo(int costo_minimo) {
        this.costo_minimo = costo_minimo;
    }

    public int getCosto_porcentual() {
        return costo_porcentual;
    }

    public void setCosto_porcentual(int costo_porcentual) {
        this.costo_porcentual = costo_porcentual;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public float getPago(int valor){
        float calculo = (float) valor*costo_porcentual/100;
        if(calculo > costo_minimo)
            return calculo;
        else
            return costo_minimo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
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
        final Cobertura other = (Cobertura) obj;
        return this.id == other.id;
    }
}
