package com.duoc.tiendaprodmascotas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.duoc.tiendaprodmascotas.Repository.ProductosRepository;
import com.duoc.tiendaprodmascotas.Service.Impl.TiendaProdMascotasServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TiendaProdMascotasServiceTest {

    @Mock
    private ProductosRepository productosRepository;

    @InjectMocks
    private TiendaProdMascotasServiceImpl tiendaProdMascotasService;

    @Test
    void testGetProductos() {
        when(productosRepository.findAll()).thenReturn(Collections.emptyList());
        assertEquals(0, tiendaProdMascotasService.getProductos().size());
    }
}
