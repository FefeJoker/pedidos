package com.danms.pedidos.repositories;

import com.danms.pedidos.model.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {
    public Optional<DetallePedido> getDetallePedidoById(Integer id);
}
