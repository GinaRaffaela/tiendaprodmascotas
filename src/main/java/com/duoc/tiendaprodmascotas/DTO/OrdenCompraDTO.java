package com.duoc.tiendaprodmascotas.DTO;

import java.util.List;

public class OrdenCompraDTO {
    
    //Variables
    private int idOrden;
    private String fechaCompra;
    private List<ProductosDTO> producto;
    private int totalCompra;
    private String estado;

    //Constructor
    public OrdenCompraDTO(int idOrden, String fechaCompra, List<ProductosDTO> producto, int totalCompra, String estado) {
        this.idOrden = idOrden;
        this.fechaCompra = fechaCompra;
        this.producto = producto;
        this.totalCompra = totalCompra;
        this.estado = estado;
    }

    //Getters y Setters
    public int getIdOrden() {
        return idOrden;
    }
    public void setIdOrden(int id) {
        this.idOrden = id;
    }
    public String getFechaCompra() {
        return fechaCompra;
    }
    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
    public List<ProductosDTO> getProducto() {
        return producto;
    }
    public void setProducto(List<ProductosDTO> producto) {
        this.producto = producto;
    }
    public int getTotalCompra() {
        return totalCompra;
    }
    public void setTotalCompra(int totalCompra) {
        this.totalCompra = totalCompra;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
