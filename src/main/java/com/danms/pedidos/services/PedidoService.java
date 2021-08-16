package com.danms.pedidos.services;

import com.danms.pedidos.model.EstadoPedido;
import com.danms.pedidos.model.Pedido;
import com.danms.pedidos.repositories.EstadoPedidoRepository;
import com.danms.pedidos.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    EstadoPedidoRepository estadoPedidoRepository;

    public Pedido saveNewPedido(Pedido pedido){
        pedido.setEstadoPedido(estadoPedidoRepository.getOne(1));
        return pedidoRepository.save(pedido);
    }

    public Pedido getOne(Integer idPedido){
        return pedidoRepository.getPedidosById(idPedido).orElse(null);
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

                }
                break;
            }
            case "PENDIENTE":{
                break;
            }
            case "CANCELADO":{
                break;
            }
            case "ACEPTADO":{
                break;
            }
            case "RECHAZADO":{
                break;
            }
            case "EN PREPARACION":{
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

}
