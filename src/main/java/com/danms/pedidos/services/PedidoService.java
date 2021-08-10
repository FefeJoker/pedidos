package com.danms.pedidos.services;

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

}
