package com.danms.pedidos.model;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
public class Pedido {
    @Id
    private Integer id;
    private Instant fechaPedido;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.DETACH)
    private List<DetallePedido> detalles;
    @ManyToOne(cascade = CascadeType.DETACH)
    private EstadoPedido estadoPedido;
    private Obra obra;

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

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
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
