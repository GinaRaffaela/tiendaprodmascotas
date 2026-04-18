package com.duoc.tiendaprodmascotas.Service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duoc.tiendaprodmascotas.DTO.OrdenCompraDTO;
import com.duoc.tiendaprodmascotas.DTO.ProductosDTO;
import com.duoc.tiendaprodmascotas.Exception.ResourceNotFoundException;
import com.duoc.tiendaprodmascotas.Model.OrdenCompra;
import com.duoc.tiendaprodmascotas.Model.OrdenProducto;
import com.duoc.tiendaprodmascotas.Model.Productos;
import com.duoc.tiendaprodmascotas.Repository.OrdenCompraRepository;
import com.duoc.tiendaprodmascotas.Repository.OrdenProductoRepository;
import com.duoc.tiendaprodmascotas.Repository.ProductosRepository;
import com.duoc.tiendaprodmascotas.Service.TiendaProdMascotasService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TiendaProdMascotasServiceImpl implements TiendaProdMascotasService {

    @Autowired
    private final ProductosRepository productosRepository;
    @Autowired
    private final OrdenCompraRepository ordenCompraRepository;
    @Autowired
    private final OrdenProductoRepository ordenProductoRepository;

    @Override
    public List<ProductosDTO> getProductos() {
        List<Productos> productos = productosRepository.findAll();
        return productos.stream()
                .map(p -> new ProductosDTO(p.getIdProducto(), p.getNombre(), p.getDescripcion(), p.getPrecio()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrdenCompraDTO crearOrden(OrdenCompraDTO ordenCompraDTO) {
        OrdenCompra orden = new OrdenCompra();
        orden.setFechaCompra(ordenCompraDTO.getFechaCompra());
        orden.setTotalCompra(0);
        orden.setEstado(ordenCompraDTO.getEstado() != null ? ordenCompraDTO.getEstado() : "Por Pagar");

        return mapToDTO(ordenCompraRepository.save(orden));
    }

    @Override
    public List<OrdenCompraDTO> getOrdenes() {
        List<OrdenCompra> ordenes = ordenCompraRepository.findAll();
        return ordenes.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrdenCompraDTO getOrdenById(Long idOrden) {
        return ordenCompraRepository.findById(idOrden)
                .map(this::mapToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada con id: " + idOrden));
    }

    @Override
    @Transactional
    public void eliminarOrden(Long idOrden) {
        if (!ordenCompraRepository.existsById(idOrden)) {
            throw new ResourceNotFoundException("Orden no encontrada con id: " + idOrden);
        }
        ordenCompraRepository.deleteById(idOrden);
    }

    @Override
    @Transactional
    public OrdenCompraDTO agregarProducto(Long idOrden, Long idProducto) {

        OrdenCompra orden = ordenCompraRepository.findById(idOrden)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada con id: " + idOrden));

        Productos producto = productosRepository.findById(idProducto)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + idProducto));

        OrdenProducto item = new OrdenProducto();
        item.setOrden(orden);
        item.setProducto(producto);

        ordenProductoRepository.save(item);

        orden.setTotalCompra(orden.getTotalCompra() + producto.getPrecio());

        return mapToDTO(ordenCompraRepository.save(orden));
    }

    @Override
    @Transactional
    public OrdenCompraDTO eliminarProducto(Long idOrden, Long idProducto) {
        OrdenCompra orden = ordenCompraRepository.findById(idOrden)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada"));

        List<OrdenProducto> items = ordenProductoRepository.findByOrdenIdOrden(idOrden);

        OrdenProducto item = items.stream()
                .filter(i -> i.getProducto().getIdProducto().equals(idProducto))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Producto no está en la orden"));

        ordenProductoRepository.delete(item);

        orden.setTotalCompra(orden.getTotalCompra() - item.getProducto().getPrecio());

        return mapToDTO(ordenCompraRepository.save(orden));
    }

    @Override
    @Transactional
    public OrdenCompraDTO pagarOrden(Long idOrden) {
        OrdenCompra orden = ordenCompraRepository.findById(idOrden)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada con id: " + idOrden));

        if (!"Por Pagar".equals(orden.getEstado())) {
            throw new IllegalArgumentException("La orden ya no está en estado 'Por Pagar'");
        }

        orden.setEstado("Pagado");
        return mapToDTO(ordenCompraRepository.save(orden));
    }

    @Override
    @Transactional
    public OrdenCompraDTO cancelarOrden(Long idOrden) {
        OrdenCompra orden = ordenCompraRepository.findById(idOrden)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada con id: " + idOrden));

        if ("Cancelada".equals(orden.getEstado()) || "Pagado".equals(orden.getEstado())) {
            throw new IllegalArgumentException("Orden de compra ya esta pagada o cancelada");
        }

        orden.setEstado("Cancelada");
        return mapToDTO(ordenCompraRepository.save(orden));
    }

    private OrdenCompraDTO mapToDTO(OrdenCompra o) {
        List<ProductosDTO> productos = o.getProducto().stream()
                .map(i -> new ProductosDTO(
                        i.getProducto().getIdProducto(),
                        i.getProducto().getNombre(),
                        i.getProducto().getDescripcion(),
                        i.getProducto().getPrecio()))
                .collect(Collectors.toList());

        return new OrdenCompraDTO(
                o.getIdOrden(),
                o.getFechaCompra(),
                productos,
                o.getTotalCompra(),
                o.getEstado());
    }
}
