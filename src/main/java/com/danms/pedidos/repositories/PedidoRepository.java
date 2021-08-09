package com.danms.pedidos.repositories;

import com.danms.pedidos.model.EstadoPedido;
import com.danms.pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    public List<Pedido> getPedidosByEstadoPedido(EstadoPedido estadoPedido);
    public Optional<Pedido> getPedidosById(Integer id);

}
