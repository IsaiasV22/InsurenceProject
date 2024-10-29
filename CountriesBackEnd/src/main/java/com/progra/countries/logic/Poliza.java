package com.progra.countries.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Poliza implements Serializable{
    private int id;
    private String num_Placa;
    private Marca marca;
    private Modelo modelo;
    private Integer anio;
    private Integer valor_asegurado;
    private String plazos_pago;
    private String fecha;
    private Cliente cliente;
    private List<Cobertura> coberturas;

    public Poliza() {
        this.id = 0;
        this.num_Placa = "";
        this.marca = new Marca();
        this.modelo = new Modelo();
        this.anio = 0;
        this.valor_asegurado = 0;
        this.plazos_pago = "";
        this.fecha = "";
        this.cliente = new Cliente();
        this.coberturas = new ArrayList<>();
    }

    public Poliza(int id, String num_Placa, Marca marca, Modelo modelo, Integer anio, Integer valor_asegurado, String plazos_pago, String fecha, Cliente cliente, List<Cobertura> coberturas) {
        this.id = id;
        this.num_Placa = num_Placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.valor_asegurado = valor_asegurado;
        this.plazos_pago = plazos_pago;
        this.fecha = fecha;
        this.cliente = cliente;
        this.coberturas = coberturas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNum_Placa() {
        return num_Placa;
    }

    public void setNum_Placa(String num_Placa) {
        this.num_Placa = num_Placa;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getValor_asegurado() {
        return valor_asegurado;
    }

    public void setValor_asegurado(Integer valor_asegurado) {
        this.valor_asegurado = valor_asegurado;
    }

    public String getPlazos_pago() {
        return plazos_pago;
    }

    public void setPlazos_pago(String plazos_pago) {
        this.plazos_pago = plazos_pago;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cobertura> getCoberturas() {
        return coberturas;
    }

    public void setCoberturas(List<Cobertura> coberturas) {
        this.coberturas = coberturas;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
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
        final Poliza other = (Poliza) obj;
        return this.id == other.id;
    }
}
