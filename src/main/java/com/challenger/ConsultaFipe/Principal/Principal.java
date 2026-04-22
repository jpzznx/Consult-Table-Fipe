package com.challenger.ConsultaFipe.Principal;

import com.challenger.ConsultaFipe.Model.Dados;
import com.challenger.ConsultaFipe.Model.Modelos;
import com.challenger.ConsultaFipe.Model.Veiculo;
import com.challenger.ConsultaFipe.Service.ConsumoApi;
import com.challenger.ConsultaFipe.Service.ConverterDados;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
        var codMarc = sc.nextLine();
        this.setMarca(codMarc);

        response = api.obterDados(ENDERECO);
        var modeloLista = converter.obterDados(response, Modelos.class);

        System.out.println("Todos Modelos dessa marca:");
        modeloLista.modelos().stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);


        System.out.println("\n Digite um trecho do nome do carro a ser buscado: ");
        var nome = sc.nextLine();

        List<Dados> modelosFiltrados = modeloLista.modelos().stream()
                .filter(m -> m.descricao().toLowerCase().contains(nome.toLowerCase()))
                .toList();

        System.out.println("\n Modelos Filtrados: ");
        modelosFiltrados.forEach(System.out::println);

        System.out.println("Digite o código do modelo para buscar os valores de avaliação: ");
        var codModelo = sc.nextLine();

        this.setModelo(codModelo);

        response = api.obterDados(ENDERECO);
        List<Dados> anos = converter.obterLista(response, Dados.class);
        List<Veiculo> veiculos = new ArrayList<>();

        for (int i = 0 ; i < anos.size(); i++){
            var EnderecoAnos =  ENDERECO + anos.get(i).codigo();
            response = api.obterDados(EnderecoAnos);
            Veiculo veiculo = converter.obterDados(response, Veiculo.class);
            veiculos.add(veiculo);
        }

        System.out.println("\n Todos os veiculos filtrado por anos: ");
        veiculos.forEach(System.out::println);
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

    private void setMarca(String codMarc) {
        ENDERECO += codMarc + "/modelos/";
    }

    private void setModelo(String codModelo) {
        ENDERECO += codModelo + "/anos/";
    }
}
