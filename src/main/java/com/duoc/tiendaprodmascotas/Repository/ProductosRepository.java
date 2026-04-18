package com.duoc.tiendaprodmascotas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duoc.tiendaprodmascotas.Model.Productos;

@Repository
public interface ProductosRepository extends JpaRepository<Productos, Long> {
    
}