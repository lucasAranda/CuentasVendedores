package com.lucas.account.model;

/**
 * Created by maquina0 on 03/10/2016.
 */
public class Adimix {
    private String cliente;
    private String articulo;
    private Double metros;
    private Double precio;
    private String fecha;
    private Double importe;

    public Adimix() {
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public Double getMetros() {
        return metros;
    }

    public void setMetros(Double metros) {
        this.metros = metros;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }
}
