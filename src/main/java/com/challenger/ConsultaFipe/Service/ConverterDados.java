package com.challenger.ConsultaFipe.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.Collection;
import java.util.List;

public class ConverterDados implements IConverterDados {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> classe) throws JsonProcessingException {
        return mapper.readValue(json, classe);
    }

    @Override
    public <T> List<T> obterLista(String json, Class<T> classe) throws JsonProcessingException {
        CollectionType list = mapper.getTypeFactory().constructCollectionType(List.class, classe);

        return mapper.readValue(json, list);
    }


}
