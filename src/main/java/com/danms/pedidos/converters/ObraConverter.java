package com.danms.pedidos.converters;

import com.danms.pedidos.model.Obra;

import javax.persistence.AttributeConverter;

public class ObraConverter implements AttributeConverter<Obra, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Obra obra) {
        return obra.getId();
    }

    @Override
    public Obra convertToEntityAttribute(Integer idObra) {
        //TODO
        //Traer el obra del microservicio de usuarios
        Obra obra = new Obra();
        obra.setId(idObra);
        return obra;
    }
}