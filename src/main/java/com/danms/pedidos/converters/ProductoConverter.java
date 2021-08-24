package com.danms.pedidos.converters;

import com.danms.pedidos.model.Obra;
import com.danms.pedidos.model.Producto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import javax.persistence.AttributeConverter;

public class ProductoConverter implements AttributeConverter<Producto, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Producto producto) {
        return producto.getId();
    }

    @Override
    public Producto convertToEntityAttribute(Integer idProducto) {
        String url = "http://http://backend.fehler.gregoret.com.ar:8085/producto-service" + "api";
        WebClient client = WebClient.create(url);
        ResponseEntity<Producto> result = client.get()
                .uri("/producto/{id}", idProducto).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(Producto.class)
                .block();

        return result.getBody();
    }
}
