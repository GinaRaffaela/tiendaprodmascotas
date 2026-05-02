package com.duoc.tiendaprodmascotas.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.duoc.tiendaprodmascotas.Controller.TiendaProdMascotasController;
import com.duoc.tiendaprodmascotas.Service.TiendaProdMascotasService;

@WebMvcTest(TiendaProdMascotasController.class)
public class TiendaProdMascotaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TiendaProdMascotasService tiendaProdMascotasService;

    @Test
    void testGetProductosEndpoint() throws Exception {
        mockMvc.perform(get("/productos"))
               .andExpect(status().isOk());
    }
}
