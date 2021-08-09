package com.danms.pedidos.rest;

import com.danms.pedidos.model.Pedido;
import com.danms.pedidos.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> crear(@RequestBody Pedido pedido){
        //TODO
        //Habria que verificar que la obra exista en el otro microservicio
        //Revisar de nuevo esta validacion
        if(pedido.getObra() == null
                || pedido.getDetalles().isEmpty()
                || pedido.getDetalles().get(0).getCantidad() == null
                || pedido.getDetalles().get(0).getProducto() == null){
            return ResponseEntity.badRequest().build();
        }

        return  ResponseEntity.ok(pedidoService.saveNewPedido(pedido));
    }

    @PatchMapping(path = "/{id}/{nuevoEstado}")
    public ResponseEntity<Pedido> actualizarEstado(){
        //TODO
        //Verificar las validaciones que pide la consigna
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/obra/{idObra}")
    public ResponseEntity<Pedido> getPedidoByObra(@PathVariable Integer idObra){
        //TODO
        //Interfaceo con los otros microservicios
        return ResponseEntity.ok().build();
    }
    @GetMapping(path = "/estado/{estado}")
    public ResponseEntity<Pedido> getPedidoByEstado(@PathVariable String estado){
        //TODO
        //Interfaceo con los otros microservicios
        return ResponseEntity.ok().build();
    }
    @GetMapping(path = "/cliente/{idCliente}")
    public ResponseEntity<Pedido> getPedidoByCliente(@PathVariable Integer idCliente){
        //TODO
        //Interfaceo con los otros microservicios
        return ResponseEntity.ok().build();
    }



}
