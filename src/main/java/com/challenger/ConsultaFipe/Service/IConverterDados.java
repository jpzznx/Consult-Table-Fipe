package com.challenger.ConsultaFipe.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface IConverterDados {
    <T> T obterDados(String json, Class<T> classe) throws JsonProcessingException;

    <T> List<T> obterLista(String json, Class<T> classe) throws JsonProcessingException;
}
