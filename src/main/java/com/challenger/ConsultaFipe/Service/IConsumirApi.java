package com.challenger.ConsultaFipe.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IConsumirApi {
    <T> T obterDados(String json, Class<T> classe) throws JsonProcessingException;
}
