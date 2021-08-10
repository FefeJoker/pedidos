package com.danms.pedidos.converters;

import com.danms.pedidos.model.Obra;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import javax.persistence.AttributeConverter;

public class ObraConverter implements AttributeConverter<Obra, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Obra obra) {
        return obra.getId();
    }

    @Override
    public Obra convertToEntityAttribute(Integer idObra) {
        String url = "http://localhost:9000/" + "api";
        WebClient client = WebClient.create(url);
        ResponseEntity<Obra> result = client.get()
                .uri("/obra/{id}", idObra).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(Obra.class)
                .or(null)
                .block();

        return result.getBody();
    }
}