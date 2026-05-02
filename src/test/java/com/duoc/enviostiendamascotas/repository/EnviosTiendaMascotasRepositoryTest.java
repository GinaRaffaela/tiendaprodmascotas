package com.duoc.enviostiendamascotas.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.duoc.enviostiendamascotas.Model.OrdenEnvio;
import com.duoc.enviostiendamascotas.Repository.EnvioTiendaMascotasRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EnviosTiendaMascotasRepositoryTest {

    @Autowired
    private EnvioTiendaMascotasRepository repository;

    @Test
    void testSave() {
        // Arrange
        OrdenEnvio ordenEnvio = new OrdenEnvio();
        ordenEnvio.setFechaEnvio("2022-01-01");
        ordenEnvio.setFechaEntrega("2022-01-02");
        ordenEnvio.setEstado("Pendiente");

        // Act
        OrdenEnvio savedOrdenEnvio = repository.save(ordenEnvio);

        // Assert
        assertNotNull(savedOrdenEnvio);
        assertEquals(ordenEnvio.getFechaEnvio(), savedOrdenEnvio.getFechaEnvio());
        assertEquals(ordenEnvio.getFechaEntrega(), savedOrdenEnvio.getFechaEntrega());
        assertEquals(ordenEnvio.getEstado(), savedOrdenEnvio.getEstado());
    }
    @Test
    void testFindById() {
        // Arrange
        OrdenEnvio ordenEnvio = new OrdenEnvio();
        ordenEnvio.setFechaEnvio("2022-01-01");
        ordenEnvio.setFechaEntrega("2022-01-02");
        ordenEnvio.setEstado("Pendiente");
        OrdenEnvio savedOrdenEnvio = repository.save(ordenEnvio);

        // Act
        java.util.Optional<OrdenEnvio> foundOrdenEnvio = repository.findById(savedOrdenEnvio.getIdOrden());

        // Assert
        org.junit.jupiter.api.Assertions.assertTrue(foundOrdenEnvio.isPresent());
        assertEquals(savedOrdenEnvio.getEstado(), foundOrdenEnvio.get().getEstado());
    }
}
