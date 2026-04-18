package com.duoc.tiendaprodmascotas.Service;

import java.util.List;

import com.duoc.tiendaprodmascotas.DTO.OrdenCompraDTO;
import com.duoc.tiendaprodmascotas.DTO.ProductosDTO;

public interface TiendaProdMascotasService {
    List<ProductosDTO> getProductos();

    OrdenCompraDTO crearOrden(OrdenCompraDTO ordenCompraDTO);

    List<OrdenCompraDTO> getOrdenes();

    OrdenCompraDTO getOrdenById(Long idOrden);

    void eliminarOrden(Long idOrden);

    OrdenCompraDTO agregarProducto(Long idOrden, Long idProducto);

    OrdenCompraDTO eliminarProducto(Long idOrden, Long idProducto);

    OrdenCompraDTO pagarOrden(Long idOrden);

    OrdenCompraDTO cancelarOrden(Long idOrden);
}
