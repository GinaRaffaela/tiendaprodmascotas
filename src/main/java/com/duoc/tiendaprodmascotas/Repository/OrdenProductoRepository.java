package com.duoc.tiendaprodmascotas.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duoc.tiendaprodmascotas.Model.OrdenProducto;

public interface OrdenProductoRepository extends JpaRepository<OrdenProducto, Long> {
    List<OrdenProducto> findByOrdenIdOrden(Long idOrden);
}
