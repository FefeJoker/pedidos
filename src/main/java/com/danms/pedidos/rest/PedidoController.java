package com.danms.pedidos.rest;

import com.danms.pedidos.model.EstadoPedido;
import com.danms.pedidos.model.Obra;
import com.danms.pedidos.model.Pedido;
import com.danms.pedidos.repositories.EstadoPedidoRepository;
import com.danms.pedidos.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Locale;


@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @Autowired
    EstadoPedidoRepository estadoPedidoRepository;

    @PostMapping
    public ResponseEntity<Pedido> crear(@RequestBody Pedido pedido){

        if(pedido.getObra() == null
                || pedido.getDetalles().isEmpty()
                || pedido.getDetalles().get(0).getCantidad() == null
                || pedido.getDetalles().get(0).getProducto() == null){
            return ResponseEntity.badRequest().build();
        }

        String url = "http://localhost:9000/" + "api";
        WebClient client = WebClient.create(url);
        ResponseEntity<Obra> result = client.get()
                .uri("/obra/{id}", pedido.getObra().getId()).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(Obra.class)
                .or(null)
                .block();

        if(result.getStatusCode() != HttpStatus.OK){
            return ResponseEntity.notFound().build();
        }

        return  ResponseEntity.ok(pedidoService.saveNewPedido(pedido));
    }

    @PatchMapping(path = "/{id}/{nuevoEstado}")
    public ResponseEntity<Pedido> actualizarEstado(@PathVariable Integer id, @PathVariable String estado){
        Pedido pedido = pedidoService.getOne(id);
        EstadoPedido estadoPedido = estadoPedidoRepository.getEstadoPedidoByEstado(estado.toUpperCase(Locale.ROOT)).orElse(null);

        if(pedido == null || estadoPedido == null){
            return ResponseEntity.notFound().build();
        }


        return ResponseEntity.ok(pedidoService.actualizarEstado(pedido, estadoPedido));
    }

    @GetMapping(path = "/obra/{idObra}")
    public ResponseEntity<Pedido> getPedidoByObra(@PathVariable Integer idObra){

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
