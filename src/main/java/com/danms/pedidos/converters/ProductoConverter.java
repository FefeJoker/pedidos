package com.danms.pedidos.converters;

import com.danms.pedidos.model.Producto;

import javax.persistence.AttributeConverter;

public class ProductoConverter implements AttributeConverter<Producto, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Producto producto) {
        return producto.getId();
    }

    @Override
    public Producto convertToEntityAttribute(Integer idProducto) {
        //TODO
        //Traer el cliente del microservicio de producto
        return null;
    }
}
