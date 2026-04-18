package com.duoc.tiendaprodmascotas.DTO;

import java.util.List;

public class OrdenCompraDTO {

    // Variables
    private Long idOrden;
    private String fechaCompra;
    private List<ProductosDTO> productos;
    private int totalCompra;
    private String estado;

    // Constructor
    public OrdenCompraDTO(Long idOrden, String fechaCompra, List<ProductosDTO> productos, int totalCompra,
            String estado) {
        this.idOrden = idOrden;
        this.fechaCompra = fechaCompra;
        this.productos = productos;
        this.totalCompra = totalCompra;
        this.estado = estado;
    }

    // Getters y Setters
    public Long getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Long id) {
        this.idOrden = id;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public List<ProductosDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductosDTO> productos) {
        this.productos = productos;
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
