package com.duoc.tiendaprodmascotas.Controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.duoc.tiendaprodmascotas.DTO.OrdenCompraDTO;
import com.duoc.tiendaprodmascotas.DTO.ProductosDTO;
import com.duoc.tiendaprodmascotas.Service.TiendaProdMascotasService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TiendaProdMascotasController {
    
    private final TiendaProdMascotasService tiendaProdMascotasService;
    
    @GetMapping("/productos")
    public ResponseEntity<List<ProductosDTO>> getProductos() {
        return ResponseEntity.ok(tiendaProdMascotasService.getProductos());
    }

    @PostMapping("/ordenes")
    public ResponseEntity<OrdenCompraDTO> crearOrden(@RequestBody OrdenCompraDTO ordenCompraDTO) {
        OrdenCompraDTO createdOrden = tiendaProdMascotasService.crearOrden(ordenCompraDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdOrden.getIdOrden())
                .toUri();
        return ResponseEntity.created(location).body(createdOrden);
    }

    @GetMapping("/ordenes")
    public ResponseEntity<List<OrdenCompraDTO>> getOrdenes() {
        return ResponseEntity.ok(tiendaProdMascotasService.getOrdenes());
    }

    @GetMapping("/ordenes/{idOrden}")
    public ResponseEntity<OrdenCompraDTO> getOrdenById(@PathVariable Long idOrden) {
        return ResponseEntity.ok(tiendaProdMascotasService.getOrdenById(idOrden));
    }
    
    @DeleteMapping("/ordenes/{idOrden}")
    public ResponseEntity<Void> eliminarOrden(@PathVariable Long idOrden) {
        tiendaProdMascotasService.eliminarOrden(idOrden);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/ordenes/{idOrden}/productos/{idProducto}")
    public ResponseEntity<OrdenCompraDTO> agregarProducto(@PathVariable Long idOrden, @PathVariable Long idProducto) {
        return ResponseEntity.ok(tiendaProdMascotasService.agregarProducto(idOrden, idProducto));
    }

    @DeleteMapping("/ordenes/{idOrden}/productos/{idProducto}")
    public ResponseEntity<OrdenCompraDTO> eliminarProducto(@PathVariable Long idOrden, @PathVariable Long idProducto) {
        return ResponseEntity.ok(tiendaProdMascotasService.eliminarProducto(idOrden, idProducto));
    }

    @PutMapping("/ordenes/{idOrden}/pago")
    public ResponseEntity<OrdenCompraDTO> pagarOrden(@PathVariable Long idOrden) {
        return ResponseEntity.ok(tiendaProdMascotasService.pagarOrden(idOrden));
    }

    @PutMapping("/ordenes/{idOrden}/cancelacion")
    public ResponseEntity<OrdenCompraDTO> cancelarOrden(@PathVariable Long idOrden) {
        return ResponseEntity.ok(tiendaProdMascotasService.cancelarOrden(idOrden));
    }
}
