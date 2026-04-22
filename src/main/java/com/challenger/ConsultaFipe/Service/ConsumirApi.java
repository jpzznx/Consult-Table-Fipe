package com.challenger.ConsultaFipe.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConsumirApi implements IConsumirApi{
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> classe) throws JsonProcessingException {
        return mapper.readValue(json, classe);
    }
}
