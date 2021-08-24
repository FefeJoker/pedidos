package com.danms.pedidos.services;

import com.danms.pedidos.model.DetallePedido;
import com.danms.pedidos.model.Pedido;
import com.danms.pedidos.repositories.DetallePedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetallePedidoService {

    @Autowired
    DetallePedidoRepository detallePedidoRepository;

    @Autowired
    PedidoService pedidoService;

    public DetallePedido saveNewDetalle(DetallePedido newDetalle, Integer idPedido){
        Pedido pedido = pedidoService.getOne(idPedido);
        if(pedido == null)  return null;
        newDetalle = detallePedidoRepository.save(newDetalle);
        pedido.getDetalles().add(newDetalle);
        pedidoService.saveNewPedido(pedido);
        return newDetalle;
    }

    public DetallePedido saveDetalle(DetallePedido detallePedido){
        return detallePedidoRepository.save(detallePedido);
    }

    public void deleteOne(Integer idDetalle){
        detallePedidoRepository.deleteById(idDetalle);
    }

    public DetallePedido getOne(Integer idDetalle){
        return detallePedidoRepository.getDetallePedidoById(idDetalle).orElse(null);
    }
}
