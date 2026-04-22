package com.challenger.ConsultaFipe.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Dados(@JsonAlias("codigo") Integer codigo,
                    @JsonAlias("nome") String descricao) {


    @Override
    public String toString() {
        return "Cód: " + codigo + " Descrição: " + descricao ;
    }
}
