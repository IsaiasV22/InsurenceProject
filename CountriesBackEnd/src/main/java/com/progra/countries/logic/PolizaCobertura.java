package com.progra.countries.logic;

import java.io.Serializable;

public class PolizaCobertura implements Serializable{
    Poliza poliza;
    Cobertura cobertura;

    public PolizaCobertura() {
        poliza = new Poliza();
        cobertura = new Cobertura();
    }

    public PolizaCobertura(Poliza poliza, Cobertura cobertura) {
        this.poliza = poliza;
        this.cobertura = cobertura;
    }
    
    public Poliza getPoliza() {
        return poliza;
    }

    public void setPoliza(Poliza poliza) {
        this.poliza = poliza;
    }

    public Cobertura getCobertura() {
        return cobertura;
    }

    public void setCobertura(Cobertura cobertura) {
        this.cobertura = cobertura;
    }
}
