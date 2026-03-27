package com.duoc.tiendaprodmascotas.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.duoc.tiendaprodmascotas.DTO.OrdenCompraDTO;
import com.duoc.tiendaprodmascotas.DTO.ProductosDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class TiendaProdMascotasController {

    //Declaracion listas y variables de fecha e id automatico
    private List<ProductosDTO> productos = new ArrayList<>();
    private List<OrdenCompraDTO> ordenesCompra = new ArrayList<>();
    private int idOrdenCompra = 1;
    private String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));


    //Inicializacion lista de productos
    public TiendaProdMascotasController() {
        productos.add(new ProductosDTO(1, "Shampoo Mascotas 3 en 1 Bugalugs", "Sahmpoo multiuso que limpia, acondiciona y desodoriza el pelaje de mascotas.", 15990));
        productos.add(new ProductosDTO(2, "Zodiac Flea & Tick Shampoo", "Shampoo especializado para eliminar pulgas y garrapatas en perros y gatos.", 11000));
        productos.add(new ProductosDTO(3, "Rotating Pet Grooming Brush", "Cepillo para eliminar pelo muerto y desenredar el pelaje.", 17990));
        productos.add(new ProductosDTO(4, "Show Tech Carda Para Pulir Suave", "Cepillo tipo carda ideal para desenredar y dar acabado suave al pelaje.", 13990));
        productos.add(new ProductosDTO(5, "Alimento Purina Dog Chow", "Alimento para perros adultos", 35000));
        productos.add(new ProductosDTO(6, "Natural Flea & Tick Repellent Collar Tag", "Collar o accesorio repelente natural contra pulgas y garrapatas.", 207990));
    }
    
    //Metodo para obtencion de lista general de productos
    @GetMapping("/productos")
    public List<ProductosDTO> getProductos(){
        return productos;
    }

    //Metodo para crear orden de compra de 1 producto
    @GetMapping("/crearOrdenCompra/{idProducto}")
    public String getOrdenCreada(@PathVariable int idProducto) {
        for (ProductosDTO producto : productos) {
            if (producto.getIdProducto() == idProducto) {
                int total = producto.getPrecio();
                List<ProductosDTO> productosCompra = new ArrayList<>();
                productosCompra.add(producto);
                ordenesCompra.add(new OrdenCompraDTO(idOrdenCompra, fecha, productosCompra, total, "Por Pagar"));
                idOrdenCompra++;
                return "Orden Creada para el producto " +  producto.getNombre();
            }
        }
        return "Producto no existe en inventario";
    }

    //Metodo para obtener lista general de ordenes de compra
    @GetMapping("/ordenes")
    public List<OrdenCompraDTO> getOrdenes() {
        return ordenesCompra;
    }

    //Metodo para obtener orden de compra especifica segun ID
    @GetMapping("/orden/{idOrden}")
    public OrdenCompraDTO getOrdenById(@PathVariable int idOrden) {
        for (OrdenCompraDTO ordenCompraDTO : ordenesCompra) {
            if (ordenCompraDTO.getIdOrden() == idOrden) {
                return ordenCompraDTO;
            }
        }
        return null;
    }
    
    //Metodo para eliminar orden de compra
    @GetMapping("/eliminarOrden/{idOrden}")
    public String getEliminarOrden(@PathVariable int idOrden) {
        for (OrdenCompraDTO ordenCompraDTO : ordenesCompra) {
            if (ordenCompraDTO.getIdOrden() == idOrden) {
                ordenesCompra.remove(ordenCompraDTO);
                return "Orden " + idOrden + " Eliminada Correctamente";
            }
        }
        return "Orden " + idOrden + " no existe";
    }

    //Metodo para modificar orden de compra para agregar o eliminar productos
    @GetMapping("/modificarOrden/{idAccion}/{idOrden}/{idProducto}")
    public String getModificarOrden(@PathVariable int idAccion, @PathVariable int idOrden, @PathVariable int idProducto) {

        switch (idAccion) {
            case 1: // Accion con id 1 para agregar productos
                for (OrdenCompraDTO ordenCompra : ordenesCompra) {
                    if (ordenCompra.getIdOrden() == idOrden) {
                        for (ProductosDTO producto : productos) {
                            if (producto.getIdProducto() == idProducto) {
                                ordenCompra.getProducto().add(producto);
                                ordenCompra.setTotalCompra(ordenCompra.getTotalCompra() + producto.getPrecio());
                                return "Orden Modificada Correctamente, se agrega nuevo producto";
                            }
                        }
                        return "Producto no encontrado";
                    }
                }
                return "Orden no encontrada";
            case 2: // Accion con id 2 para eliminar productos
                for (OrdenCompraDTO ordenCompra : ordenesCompra) {
                    if (ordenCompra.getIdOrden() == idOrden) {
                        Iterator<ProductosDTO> iterator = ordenCompra.getProducto().iterator();

                        while (iterator.hasNext()) {
                            ProductosDTO producto = iterator.next();
                            if (producto.getIdProducto() == idProducto) {
                                ordenCompra.setTotalCompra(ordenCompra.getTotalCompra() - producto.getPrecio());
                                iterator.remove();
                                return "Orden Modificada Correctamente, se elimina producto";
                            }
                        }

                        return "Producto no existe dentro de la orden";
                    }
                }
                return "Orden no encontrada";
            default:
                return "Operacion Incorrecta, ingrese 1 o 2";
        }
    }

    //Metodo para cambiar el estado de la orden de compra a pagado
    @GetMapping("/pagarOrden/{idOrden}")
    public String getPagoOrden(@PathVariable int idOrden) {
        for (OrdenCompraDTO ordenCompraDTO : ordenesCompra) {
            if (ordenCompraDTO.getIdOrden() == idOrden && ordenCompraDTO.getEstado() == "Por Pagar") {
                ordenCompraDTO.setEstado("Pagado");
                return "Se paga exitosamente orden de compra " + idOrden;
            } else {
                return "Orden de compra pagada o no se encuentra: Orden" + idOrden;
            }
        }
        return "No se encuentra orden de compra";
    }

    //Metodo para cambiar el estado de la orden a cancelada
    @GetMapping("/cancelar/{idOrden}")
    public String getCancelaOrden(@PathVariable int idOrden) {
        for (OrdenCompraDTO ordenCompraDTO : ordenesCompra) {
            if (ordenCompraDTO.getIdOrden() == idOrden && ordenCompraDTO.getEstado() != "Cancelada" && ordenCompraDTO.getEstado() != "Pagado") {
                ordenCompraDTO.setEstado("Cancelada");
                return "Se cancela exitosamente orden de compra " + idOrden;
            } else {
                return "Orden de compra pagada o cancelada: Orden" + idOrden;
            }
        }
        return "No se encuentra orden de compra";
    }
    
}
