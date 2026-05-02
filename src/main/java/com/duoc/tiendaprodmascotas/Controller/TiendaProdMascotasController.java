package com.duoc.tiendaprodmascotas.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.tiendaprodmascotas.DTO.OrdenCompraDTO;
import com.duoc.tiendaprodmascotas.DTO.ProductosDTO;
import com.duoc.tiendaprodmascotas.Service.TiendaProdMascotasService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TiendaProdMascotasController {

    private final TiendaProdMascotasService tiendaProdMascotasService;

    @GetMapping("/productos")
    public ResponseEntity<CollectionModel<EntityModel<ProductosDTO>>> getProductos() {
        List<EntityModel<ProductosDTO>> productos = tiendaProdMascotasService.getProductos().stream()
                .map(producto -> EntityModel.of(producto))
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(productos,
                linkTo(methodOn(TiendaProdMascotasController.class).getProductos()).withSelfRel()));
    }

    @PostMapping("/ordenes")
    public ResponseEntity<EntityModel<OrdenCompraDTO>> crearOrden(@RequestBody OrdenCompraDTO ordenCompraDTO) {
        OrdenCompraDTO createdOrden = tiendaProdMascotasService.crearOrden(ordenCompraDTO);
        EntityModel<OrdenCompraDTO> resource = toEntityModel(createdOrden);
        return ResponseEntity
                .created(linkTo(methodOn(TiendaProdMascotasController.class).getOrdenById(createdOrden.getIdOrden()))
                        .toUri())
                .body(resource);
    }

    @GetMapping("/ordenes")
    public ResponseEntity<CollectionModel<EntityModel<OrdenCompraDTO>>> getOrdenes() {
        List<EntityModel<OrdenCompraDTO>> ordenes = tiendaProdMascotasService.getOrdenes().stream()
                .map(this::toEntityModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(CollectionModel.of(ordenes,
                linkTo(methodOn(TiendaProdMascotasController.class).getOrdenes()).withSelfRel()));
    }

    @GetMapping("/ordenes/{idOrden}")
    public ResponseEntity<EntityModel<OrdenCompraDTO>> getOrdenById(@PathVariable Long idOrden) {
        OrdenCompraDTO orden = tiendaProdMascotasService.getOrdenById(idOrden);
        return ResponseEntity.ok(toEntityModel(orden));
    }

    @DeleteMapping("/ordenes/{idOrden}")
    public ResponseEntity<Void> eliminarOrden(@PathVariable Long idOrden) {
        tiendaProdMascotasService.eliminarOrden(idOrden);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/ordenes/{idOrden}/productos/{idProducto}")
    public ResponseEntity<EntityModel<OrdenCompraDTO>> agregarProducto(@PathVariable Long idOrden,
            @PathVariable Long idProducto) {
        OrdenCompraDTO orden = tiendaProdMascotasService.agregarProducto(idOrden, idProducto);
        return ResponseEntity.ok(toEntityModel(orden));
    }

    @DeleteMapping("/ordenes/{idOrden}/productos/{idProducto}")
    public ResponseEntity<EntityModel<OrdenCompraDTO>> eliminarProducto(@PathVariable Long idOrden,
            @PathVariable Long idProducto) {
        OrdenCompraDTO orden = tiendaProdMascotasService.eliminarProducto(idOrden, idProducto);
        return ResponseEntity.ok(toEntityModel(orden));
    }

    @PutMapping("/ordenes/{idOrden}/pago")
    public ResponseEntity<EntityModel<OrdenCompraDTO>> pagarOrden(@PathVariable Long idOrden) {
        OrdenCompraDTO orden = tiendaProdMascotasService.pagarOrden(idOrden);
        return ResponseEntity.ok(toEntityModel(orden));
    }

    @PutMapping("/ordenes/{idOrden}/cancelacion")
    public ResponseEntity<EntityModel<OrdenCompraDTO>> cancelarOrden(@PathVariable Long idOrden) {
        OrdenCompraDTO orden = tiendaProdMascotasService.cancelarOrden(idOrden);
        return ResponseEntity.ok(toEntityModel(orden));
    }

    private EntityModel<OrdenCompraDTO> toEntityModel(OrdenCompraDTO orden) {
        return EntityModel.of(orden,
                linkTo(methodOn(TiendaProdMascotasController.class).getOrdenById(orden.getIdOrden())).withSelfRel(),
                linkTo(methodOn(TiendaProdMascotasController.class).getOrdenes()).withRel("ordenes"),
                linkTo(methodOn(TiendaProdMascotasController.class).pagarOrden(orden.getIdOrden())).withRel("pagar"),
                linkTo(methodOn(TiendaProdMascotasController.class).cancelarOrden(orden.getIdOrden()))
                        .withRel("cancelar"));
    }
}
