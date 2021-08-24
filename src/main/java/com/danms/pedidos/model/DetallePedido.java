package com.danms.pedidos.model;

import com.danms.pedidos.converters.ObraConverter;
import com.danms.pedidos.converters.ProductoConverter;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class DetallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private Integer cantidad;
    @NotNull
    private Double precio;
    @NotNull
    @Convert(converter = ProductoConverter.class)
    private Producto producto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
