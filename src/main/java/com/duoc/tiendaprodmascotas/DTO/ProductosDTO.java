package com.duoc.tiendaprodmascotas.DTO;

public class ProductosDTO {
    
    //Variables
    private int idProducto;
    private String nombre;
    private String descripcion;
    private int precio;

    //Constructor
    public ProductosDTO(int idProducto, String nombre, String descripcion, int precio) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    //Getters y Setters
    public int getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(int id) {
        this.idProducto = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public int getPrecio() {
        return precio;
    }
    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
