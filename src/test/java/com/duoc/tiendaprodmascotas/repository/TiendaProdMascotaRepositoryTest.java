package com.duoc.tiendaprodmascotas.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import com.duoc.tiendaprodmascotas.Repository.ProductosRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TiendaProdMascotaRepositoryTest {

    @Autowired
    private ProductosRepository productosRepository;

    @Test
    void testRepositoryLoads() {
        assertNotNull(productosRepository);
    }

    @Test
    void testFindAllEmpty() {
        assertNotNull(productosRepository.findAll());
    }
}
