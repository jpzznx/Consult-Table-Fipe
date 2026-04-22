package com.challenger.ConsultaFipe.Principal;

import com.challenger.ConsultaFipe.Model.Dados;
import com.challenger.ConsultaFipe.Model.Modelos;
import com.challenger.ConsultaFipe.Service.ConsumoApi;
import com.challenger.ConsultaFipe.Service.ConverterDados;

import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;

public class Principal {
    Scanner sc = new Scanner(System.in);
    ConsumoApi api = new ConsumoApi();
    ConverterDados converter = new ConverterDados();

    private static String ENDERECO = "https://parallelum.com.br/fipe/api/v1/";

    public void exibeMenu() throws IOException, InterruptedException {

        var menu = """
                *** OPÇÕES ***
                Carro
                Moto
                Caminhão
                
                Digite uma das opções para consulta:
                """;
        System.out.println(menu);
        var opcao = sc.nextLine().toLowerCase();

        this.setAutomovel(opcao);

        String response = api.obterDados(ENDERECO);
        System.out.println(response);

        var marcas = converter.obterLista(response, Dados.class);
        marcas.stream()
                        .sorted(Comparator.comparing(Dados::codigo))
                                .forEach(System.out::println);


        System.out.println("Informe o código da marca para consulta: ");
        var codMarc = sc.nextInt();
        this.setMarca(codMarc);

        response = api.obterDados(ENDERECO);
        var modeloLista = converter.obterDados(response, Modelos.class);

        System.out.println("Todos Modelos dessa marca:");
        modeloLista.modelos().stream().sorted(Comparator.comparing(Dados::codigo)).forEach(System.out::println);



    }


    private void setAutomovel(String opcao) {
        if (opcao.toLowerCase().matches("car|carro|carros")) {
            ENDERECO += "carros/marcas/";
        }

        if (opcao.toLowerCase().matches("mot|moto|motos")) {
            ENDERECO += "motos/marcas/";
        }

        if (opcao.toLowerCase().matches("cami|caminhao|caminhoes")) {
            ENDERECO += "caminhoes/marcas/";
        }
    }

    private void setMarca(Integer codMarc) {
        ENDERECO += codMarc + "/modelos";
    }
}
