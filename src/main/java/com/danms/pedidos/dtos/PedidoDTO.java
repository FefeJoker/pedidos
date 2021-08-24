package com.danms.pedidos.dtos;

import com.danms.pedidos.model.EstadoPedido;
import com.danms.pedidos.model.Obra;
import com.danms.pedidos.model.Pedido;

import java.time.Instant;

public class PedidoDTO {
    private Integer id;
    private Instant fechaPedido;
    private EstadoPedido estadoPedido;
    private Obra obra;

    public PedidoDTO(Pedido pedido){
        this.id = pedido.getId();
        this.fechaPedido = pedido.getFechaPedido();
        this.estadoPedido = pedido.getEstadoPedido();
        this.obra = pedido.getObra();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Instant fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }
}
