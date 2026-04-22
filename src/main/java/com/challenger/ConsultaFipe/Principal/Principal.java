package com.challenger.ConsultaFipe.Principal;

import com.challenger.ConsultaFipe.Service.ConsumoApi;
import com.challenger.ConsultaFipe.Service.ConverterDados;

import java.io.IOException;
import java.util.Scanner;

public class Principal {
    Scanner sc = new Scanner(System.in);
    ConsumoApi api = new ConsumoApi();
    ConverterDados converter = new ConverterDados();

    private static String ENDERECO = "https://parallelum.com.br/fipe/api/v1/";

    public void exibeMenu() {

        var menu = """
                *** OPÇÕES ***
                Carro
                Moto
                Caminhão
                
                Digite uma das opções para consulta:
                """;
        System.out.println(menu);
        var opcao = sc.nextLine().toLowerCase();

        this.setEndereco(opcao);

        try{
            String response = api.obterDados(ENDERECO);
            System.out.println(response);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    private void setEndereco(String opcao) {
        if (opcao.toLowerCase().matches("car|carro|carros")) {
            ENDERECO += "carros/marcas";
        }

        if (opcao.toLowerCase().matches("mot|moto|motos")) {
            ENDERECO += "motos/marcas";
        }

        if (opcao.toLowerCase().matches("cami|caminhao|caminhoes")) {
            ENDERECO += "caminhoes/marcas";
        }
    }
}
