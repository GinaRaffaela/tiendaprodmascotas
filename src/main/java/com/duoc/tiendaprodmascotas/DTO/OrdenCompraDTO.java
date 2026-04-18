package com.duoc.tiendaprodmascotas.DTO;

import java.time.LocalDate;
import java.util.List;

public class OrdenCompraDTO {

    // Variables
    private Long idOrden;
    private LocalDate fechaCompra;
    private List<ProductosDTO> productos;
    private Integer totalCompra;
    private String estado;

    // Constructor
    public OrdenCompraDTO(Long idOrden, LocalDate fechaCompra, List<ProductosDTO> productos, Integer totalCompra,
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

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public List<ProductosDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductosDTO> productos) {
        this.productos = productos;
    }

    public Integer getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(Integer totalCompra) {
        this.totalCompra = totalCompra;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
