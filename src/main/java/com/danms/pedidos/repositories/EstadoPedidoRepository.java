package com.danms.pedidos.repositories;

import com.danms.pedidos.model.EstadoPedido;
import org.springframework.data.jpa.repository.JpaRepository;



public interface EstadoPedidoRepository extends JpaRepository<EstadoPedido, Integer> {
}
