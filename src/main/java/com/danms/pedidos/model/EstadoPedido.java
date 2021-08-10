package com.danms.pedidos.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EstadoPedido {
    @Id
    private Integer id;
    private String estado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
