package com.danms.pedidos.services;

import com.danms.pedidos.model.*;
import com.danms.pedidos.repositories.EstadoPedidoRepository;
import com.danms.pedidos.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;


@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    EstadoPedidoRepository estadoPedidoRepository;

    @Autowired
    DetallePedidoService detallePedidoService;

    public Pedido saveNewPedido(Pedido pedido){
        pedido.setEstadoPedido(estadoPedidoRepository.findById(1).orElse(null));
        List<DetallePedido> detallesGuardados = new ArrayList<>();
        for(DetallePedido dp : pedido.getDetalles()){
            dp.setId(null);
            detallesGuardados.add(detallePedidoService.saveDetalle(dp));
        }
        pedido.setDetalles(detallesGuardados);
        return pedidoRepository.save(pedido);
    }

    public Pedido getOne(Integer idPedido){
        return pedidoRepository.getPedidosById(idPedido).orElse(null);
    }

    public List<Pedido> getPedidosByObra(Obra obra){
        return pedidoRepository.getPedidosByObra(obra);
    }

    public Pedido actualizarEstado(Pedido pedido, EstadoPedido estadoPedido){
        if(estadoPedido.getEstado() == "CONFIRMADO"){

        }
        else if(estadoPedido.getEstado() == "CANCELADO"
                && (pedido.getEstadoPedido().getEstado() == "NUEVO"
                        || pedido.getEstadoPedido().getEstado() == "CONFIRMADO"
                        || pedido.getEstadoPedido().getEstado() == "PENDIENTE")){
                pedido.setEstadoPedido(estadoPedido);
                updatePedido(pedido);
        }

        switch (pedido.getEstadoPedido().getEstado()){
            //TODO
            //Ver validaciones de la guia 6
            case "NUEVO":{
                if(estadoPedido.getEstado() == "CONFIRMADO"){
                    if(generaDeuda(pedido)
                            || (saldoMenorDescubierto(pedido)
                                && situacionCrediticiaBajoRiesgo(pedido))){
                        if(hayStock(pedido)){
                            EstadoPedido newEstadoPedido = estadoPedidoRepository.getEstadoPedidoByEstado("ACEPTADO").orElse(null);
                            pedido.setEstadoPedido(newEstadoPedido);
                        }
                        else{
                            EstadoPedido newEstadoPedido = estadoPedidoRepository.getEstadoPedidoByEstado("PENDIENTE").orElse(null);
                            pedido.setEstadoPedido(newEstadoPedido);
                        }
                    }
                    else{
                        EstadoPedido newEstadoPedido = estadoPedidoRepository.getEstadoPedidoByEstado("RECHAZADO").orElse(null);
                        pedido.setEstadoPedido(newEstadoPedido);
                    }
                }
                else if(estadoPedido.getEstado() == "CANCELADO"){
                    pedido.setEstadoPedido(estadoPedido);
                }
                break;
            }
            case "CONFIRMADO":{
                if(estadoPedido.getEstado() == "CANCELADO"){
                    pedido.setEstadoPedido(estadoPedido);
                }
            }
            case "PENDIENTE":{
                if(estadoPedido.getEstado() == "CANCELADO"){
                    pedido.setEstadoPedido(estadoPedido);
                }
                break;
            }
            case "ACEPTADO":{
                if(estadoPedido.getEstado() == "EN PREPARACION"){
                    pedido.setEstadoPedido(estadoPedido);
                }
                break;
            }
            case "EN PREPARACION":{
                if(estadoPedido.getEstado() == "ENTREGADO"){
                    pedido.setEstadoPedido(estadoPedido);
                }
                break;
            }
            case "ENTREGADO":{
                break;
            }
        }
        pedido = updatePedido(pedido);
        return pedido;
    }

    public Pedido updatePedido(Pedido pedido){
        return pedidoRepository.save(pedido);
    }

    public Boolean hayStock(Pedido pedido){
        //TODO
        //Revisar, es horrible la definicion
        List<DetallePedido> listPedidos = new ArrayList<>();
        Boolean resultado = Boolean.TRUE;

        for (DetallePedido dp: pedido.getDetalles()) {
            DetallePedido aux = listPedidos.stream().filter(dpa -> dpa.getProducto().equals(dp.getProducto())).findAny().orElse(null);
            if(aux != null){
                aux.setCantidad(aux.getCantidad() + dp.getCantidad());
            }
            else{
                DetallePedido newDp = new DetallePedido();
                newDp.setProducto(dp.getProducto());
                newDp.setCantidad(dp.getCantidad());
                listPedidos.add(newDp);
            }
        }


        for(DetallePedido dp : listPedidos){
            String url = "http://backend.fehler.gregoret.com.ar:8085/producto-service/" + "api";
            WebClient client = WebClient.create(url);
            ResponseEntity<Boolean> result = client.get()
                    .uri("/producto/{id}/{cantidad}", dp.getId(), dp.getCantidad()).accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .toEntity(Boolean.class)
                    .or(null)
                    .block();

            resultado = result.getBody();
            if(!resultado)  break;
        }

        return resultado;
    }

    public Boolean generaDeuda(Pedido pedido){
        //TODO
        return new Random().nextBoolean();
    }

    public Boolean saldoMenorDescubierto(Pedido pedido){
        //TODO
        return new Random().nextBoolean();
    }

    public Boolean situacionCrediticiaBajoRiesgo(Pedido pedido){
        //TODO
        return new Random().nextBoolean();
    }


}
