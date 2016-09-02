package com.lucas.account.model;

import java.util.List;

/**
 * Created by maquina0 on 16/08/2016.
 */
public class Vendedor {

    private String nombre;
    private Double totalHormicon;
    private Double totalInsucon;
    private Double totalEste;
    private Double totalUco;
    private Double montoTotal;
    private List<Comprobante> comprobantes;

    public Vendedor() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public List<Comprobante> getComprobantes() {
        return comprobantes;
    }

    public void setComprobantes(List<Comprobante> comprobantes) {
        this.comprobantes = comprobantes;
    }

    public Double getTotalHormicon() {
        return totalHormicon;
    }

    public void setTotalHormicon(Double totalHormicon) {
        this.totalHormicon = totalHormicon;
    }

    public Double getTotalInsucon() {
        return totalInsucon;
    }

    public void setTotalInsucon(Double totalInsucon) {
        this.totalInsucon = totalInsucon;
    }

    public Double getTotalEste() {
        return totalEste;
    }

    public void setTotalEste(Double totalEste) {
        this.totalEste = totalEste;
    }

    public Double getTotalUco() {
        return totalUco;
    }

    public void setTotalUco(Double totalUco) {
        this.totalUco = totalUco;
    }
}
