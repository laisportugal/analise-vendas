package com.ilegra.analisevendas.input;

import com.ilegra.analisevendas.dto.RelatorioDTO;
import com.ilegra.analisevendas.entidades.Cliente;
import com.ilegra.analisevendas.entidades.Produto;
import com.ilegra.analisevendas.entidades.Venda;
import com.ilegra.analisevendas.entidades.Vendedor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;

public class LeitorArquivo {

    String nomeArquivoEntrada;
    RelatorioDTO relatorioDTO = new RelatorioDTO();

    public void lerArquivo(String caminhoDiretorio) throws FileNotFoundException {
        final String idVendedor = "001";
        final String idCliente = "002";
        final String idVenda = "003";

        File diretorio = new File(caminhoDiretorio);
        File[] listFiles = diretorio.listFiles();
        List<Venda> vendas = new ArrayList<Venda>();
        List<Vendedor> vendedores = new ArrayList<Vendedor>();
        List<Cliente> clientes = new ArrayList<Cliente>();


        for (File arquivo : listFiles) {
            Scanner in = new Scanner(new FileReader(arquivo));
            nomeArquivoEntrada = arquivo.getName();
            while (in.hasNextLine()) {
                String linha = in.nextLine();
                String[] textoSeparado = linha.split("ç");

                if (idVendedor.equals(textoSeparado[0])) {
                    vendedores.add(preencheVendedor(textoSeparado));
                } else if (idCliente.equals(textoSeparado[0])) {
                    clientes.add(preencheCliente(textoSeparado));
                } else if (idVenda.equals(textoSeparado[0])) {
                    vendas.add(preencheVenda(textoSeparado));

                }

            }
            in.close();
        }
        preencherRelatorioVenda(vendas);
        preencheRelatorioVendedor(vendedores);
        preencheRelatorioClientes(clientes);
    }
    private Vendedor preencheVendedor(String[] linha) {
       Vendedor vendedor = new Vendedor(Integer.valueOf(linha[0]), linha[1],linha[2], Double.valueOf(linha[3]));
        return vendedor;
    }

    private Cliente preencheCliente(String[] linha) {
        Cliente cliente = new Cliente (Integer.valueOf(linha[0]),linha[1],linha[2],linha[3]);
        return cliente;
    }

    private Venda preencheVenda(String[] linha) {
        Venda venda = new Venda();
        Vendedor vendedor = new Vendedor();
        vendedor.setNome(linha[3]);
        venda.setId(Integer.valueOf(linha[1]));
        venda.setVendedor(vendedor);

        String conteudoVenda = linha[2].replace("[", "").replace("]", "");
        String[] conteudoItemVenda = conteudoVenda.split(",");
        List<Produto> produtos = new ArrayList<Produto>();

        for (String itemVenda : conteudoItemVenda) {
            String[] descricoesVenda = itemVenda.split("-");
            produtos.add(new Produto(Integer.valueOf(descricoesVenda[0]),Integer.valueOf(descricoesVenda[1]), new BigDecimal(descricoesVenda[2])));
        }
        venda.setProdutos(produtos);
        venda.setValorTotal(calcularTotalVendas(produtos));
        return venda;
    }

    private BigDecimal calcularTotalVendas(List<Produto> produtos) {
        BigDecimal totalVendas = produtos.stream()
                .map(p -> p.getPreco()
                        .multiply(BigDecimal.valueOf(p.getQuantidade().longValue())))
                .reduce(BigDecimal.ZERO,BigDecimal::add);
        return totalVendas;
     }
    public void preencherRelatorioVenda(List<Venda> vendas){
        relatorioDTO.setVendaMaisCara(vendas
                .stream()
                .max(Comparator.comparing(Venda::getValorTotal))
                .orElse(null));
        relatorioDTO.setPiorVenda(vendas.stream().min(Comparator.comparing(Venda::getValorTotal)).orElse(null));
    }
    public void preencheRelatorioVendedor(List<Vendedor> vendedores){
        relatorioDTO.setQuantidadeVendedores(vendedores.size());
    }
    public void preencheRelatorioClientes (List<Cliente> clientes){
        relatorioDTO.setQuantidadeClientes(clientes.size());
    }

    public RelatorioDTO getRelatorio() {
        return relatorioDTO;
    }
    public String getNomeArquivoEntrada() {
        return nomeArquivoEntrada;
    }
}
