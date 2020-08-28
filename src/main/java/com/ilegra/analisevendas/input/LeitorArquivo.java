package com.ilegra.analisevendas.input;

import com.ilegra.analisevendas.dto.RelatorioDTO;
import com.ilegra.analisevendas.entidades.Cliente;
import com.ilegra.analisevendas.entidades.Produto;
import com.ilegra.analisevendas.entidades.Venda;
import com.ilegra.analisevendas.entidades.Vendedor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class LeitorArquivo {

    String nomeArquivoEntrada;
    RelatorioDTO relatorioDTO = new RelatorioDTO();

    public void lerArquivo(String caminhoDiretorio) throws FileNotFoundException {
        final String idVendedor = "001";
        final String idCliente = "002";
        final String idVenda = "003";

        File diretorio = new File(caminhoDiretorio);
        File[] listFiles = diretorio.listFiles();

        for (File arquivo : listFiles) {
            Scanner in = new Scanner(new FileReader(arquivo));
            nomeArquivoEntrada = arquivo.getName();
            while (in.hasNextLine()) {
                String linha = in.nextLine();
                String[] textoSeparado = linha.split("ç");

                if (idVendedor.equals(textoSeparado[0])) {
                    preencheVendedor(textoSeparado);
                    relatorioDTO.setQuantidadeVendedores(relatorioDTO.getQuantidadeVendedores() + 1);
                } else if (idCliente.equals(textoSeparado[0])) {
                    preencheCliente(textoSeparado);
                    relatorioDTO.setQuantidadeClientes(relatorioDTO.getQuantidadeClientes() + 1);
                } else if (idVenda.equals(textoSeparado[0])) {
                    preencheVenda(textoSeparado);
                }

            }
            in.close();
        }
    }
    private void preencheVendedor(String[] linha) {

        Vendedor vendedor = new Vendedor();
        vendedor.setId(Integer.valueOf(linha[0]));
        vendedor.setCpf(linha[1]);
        vendedor.setNome(linha[2]);
        vendedor.setSalario(Double.valueOf(linha[3]));
    }

    private void preencheCliente(String[] linha) {

        Cliente cliente = new Cliente();
        cliente.setId(Integer.valueOf(linha[0]));
        cliente.setCnpj(linha[1]);
        cliente.setNome(linha[2]);
        cliente.setAreaNegocio(linha[3]);

    }

    private void preencheVenda(String[] linha) {
        Venda venda = new Venda();
        Vendedor vendedor = new Vendedor();
        vendedor.setNome(linha[3]);

        venda.setId(Integer.valueOf(linha[1]));
        venda.setVendedor(vendedor);

        String conteudoVenda = linha[2].replace("[", "").replace("]", "");
        String[] conteudoItemVenda = conteudoVenda.split(",");
        for (String itemVenda : conteudoItemVenda) {
            String[] descricoesVenda = itemVenda.split("-");
            Produto produto = new Produto();
            produto.setId(Integer.valueOf(descricoesVenda[0]));
            produto.setQuantidade(Integer.valueOf(descricoesVenda[1]));
            produto.setPreco(Double.valueOf(descricoesVenda[2]));
            venda.getProdutos().add(produto);

            Double valorTotalProduto = produto.getPreco() * produto.getQuantidade();
            venda.setValorTotal(venda.getValorTotal() + valorTotalProduto);

        }
        preencheRelatorioVendaMaisCara(venda);
        preencheRelatorioPiorVendedor(venda);
    }

    private void preencheRelatorioVendaMaisCara(Venda venda) {
        if (relatorioDTO.getVendaMaisCara() == null) {
            relatorioDTO.setVendaMaisCara(venda);
        } else {

            if (venda.getValorTotal() > relatorioDTO.getVendaMaisCara().getValorTotal()) {
                relatorioDTO.setVendaMaisCara(venda);
            }
        }
    }

    private void preencheRelatorioPiorVendedor(Venda venda) {
        if (relatorioDTO.getPiorVenda() == null) {
            relatorioDTO.setPiorVenda(venda);
        } else {
            if (venda.getValorTotal() < relatorioDTO.getPiorVenda().getValorTotal()) {
                relatorioDTO.setPiorVenda(venda);
            }
        }
    }
    public RelatorioDTO getRelatorio() {
        return relatorioDTO;
    }
    public String getNomeArquivoEntrada() {
        return nomeArquivoEntrada;
    }
}
