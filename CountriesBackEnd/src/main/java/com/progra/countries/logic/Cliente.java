package com.progra.countries.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cliente implements Serializable{
    private String cedula;
    private String clave;
    private String nombre;
    private String telefono;
    private String correo;
    private String tarjeta;
    private Usuario usuario;
    private List<Poliza> polizas;

    public Cliente() {
        cedula = "";
        clave = "";
        nombre = "";
        telefono = "";
        correo = "";
        tarjeta = "";
        usuario = new Usuario();
        polizas = new ArrayList();
    }

    public Cliente(String cedula, String clave, String nombre, String telefono, String correo, String tarjeta, Usuario usuario) {
        this.cedula = cedula;
        this.clave = clave;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.tarjeta = tarjeta;
        this.usuario = usuario;
        this.polizas = new ArrayList();
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    

    public List<Poliza> getPolizas() {
        return polizas;
    }

    public void setPolizas(List<Poliza> polizas) {
        this.polizas = polizas;
    }
    
    public void agregarPoliza(Poliza poliza){
        this.polizas.add(poliza);
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
        final Cliente other = (Cliente) obj;
        return Objects.equals(this.cedula, other.cedula);
    }
}
