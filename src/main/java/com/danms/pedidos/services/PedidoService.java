package com.danms.pedidos.services;

import com.danms.pedidos.model.DetallePedido;
import com.danms.pedidos.model.EstadoPedido;
import com.danms.pedidos.model.Pedido;
import com.danms.pedidos.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    public Pedido saveNewPedido(Pedido pedido){
        pedido.setEstadoPedido(new EstadoPedido());
        return pedidoRepository.save(pedido);
    }

    public Pedido getOne(Integer idPedido){
        return pedidoRepository.getPedidosById(idPedido).orElse(null);
    }

}
