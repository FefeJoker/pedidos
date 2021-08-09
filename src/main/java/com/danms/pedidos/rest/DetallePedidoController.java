package com.danms.pedidos.rest;

import com.danms.pedidos.model.DetallePedido;
import com.danms.pedidos.repositories.DetallePedidoRepository;
import com.danms.pedidos.services.DetallePedidoService;
import com.danms.pedidos.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedido/{idPedido}")
public class DetallePedidoController {

    @Autowired
    DetallePedidoService detallePedidoService;

    @Autowired
    PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<DetallePedido> crear(@RequestBody @Validated DetallePedido detallePedido, @PathVariable Integer idPedido){
        if(pedidoService.getOne(idPedido) == null){
            return ResponseEntity.notFound().build();
        }
        DetallePedido resultado = detallePedidoService.saveNewDetalle(detallePedido, idPedido);
        return ResponseEntity.ok(resultado);
    }

    @DeleteMapping("/{idDetalle}")
    public ResponseEntity borrar(@PathVariable Integer idDetalle, @PathVariable Integer idPedido){
        if(pedidoService.getOne(idPedido) == null){
            return ResponseEntity.notFound().build();
        }
        detallePedidoService.deleteOne(idDetalle);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{idDetalle}")
    public ResponseEntity<DetallePedido> actualizar(@RequestBody DetallePedido detallePedido, @PathVariable Integer idDetalle, @PathVariable Integer idPedido){
        DetallePedido oldDetalle = detallePedidoService.getOne(idDetalle);
        if(oldDetalle == null){
            return ResponseEntity.notFound().build();
        }
        DetallePedido newDetalle = new DetallePedido();
        newDetalle.setId(oldDetalle.getId());
        newDetalle.setCantidad(detallePedido.getCantidad());
        newDetalle.setPrecio(detallePedido.getPrecio());
        newDetalle.setProducto(oldDetalle.getProducto());

        return ResponseEntity.ok(detallePedidoService.saveNewDetalle(newDetalle, idPedido));
    }

    @GetMapping("/{idDetalle}")
    public ResponseEntity<DetallePedido> obtener(@PathVariable Integer idDetalle){
        DetallePedido detallePedido = detallePedidoService.getOne(idDetalle);
        if(detallePedido == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detallePedido);
    }
}
