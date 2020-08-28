package com.ilegra.analisevendas.output;

import com.ilegra.analisevendas.dto.RelatorioDTO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EscritorArquivo {
    public void escreverArquivo(RelatorioDTO relatorioDTO, String file) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(file));

        buffWrite.append("Quantidade de clientes no arquivo de entrada: " + relatorioDTO.getQuantidadeClientes() + "\n");
        buffWrite.append("Quantidade de vendedores no arquivo de entrada: " + relatorioDTO.getQuantidadeVendedores()+ "\n");
        buffWrite.append("ID da venda mais cara: " + relatorioDTO.getVendaMaisCara().getId() + "\n");
        buffWrite.append("Pior vendedor: " + relatorioDTO.getPiorVenda().getVendedor().getNome()+ "\n");

        buffWrite.close();
    }
}
