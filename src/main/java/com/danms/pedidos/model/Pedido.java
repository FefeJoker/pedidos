package com.danms.pedidos.model;

import com.danms.pedidos.converters.ObraConverter;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
public class Pedido {
    @Id
    private Integer id;
    private Instant fechaPedido;
    @OneToMany(mappedBy = "id", cascade = CascadeType.DETACH)
    private List<DetallePedido> detalles;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "estado_pedido_id")
    private EstadoPedido estadoPedido;
    @Convert(converter = ObraConverter.class)
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
