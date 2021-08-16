package com.danms.pedidos.repositories;

import com.danms.pedidos.model.EstadoPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface EstadoPedidoRepository extends JpaRepository<EstadoPedido, Integer> {
    public Optional<EstadoPedido> getEstadoPedidoByEstado(String estado);
}
