package com.ilegra.analisevendas.dto;

import com.ilegra.analisevendas.entidades.Venda;

public class RelatorioDTO {

    private Integer quantidadeClientes;
    private Integer quantidadeVendedores;
    private Venda vendaMaisCara;
    private Venda piorVenda;

    public Integer getQuantidadeClientes() {
        return quantidadeClientes;
    }

    public void setQuantidadeClientes(Integer quantidadeClientes) {
        if (quantidadeClientes != null){
            this.quantidadeClientes = quantidadeClientes;
        }else{
            this.quantidadeClientes =0;
        }
    }

    public Integer getQuantidadeVendedores() {
        return quantidadeVendedores;
    }

    public void setQuantidadeVendedores(Integer quantidadeVendedores) {
        if(quantidadeVendedores != null){
            this.quantidadeVendedores = quantidadeVendedores;
        }
        else {
            this.quantidadeVendedores=0;
        }

    }

    public Venda getVendaMaisCara() {
        return vendaMaisCara;
    }

    public void setVendaMaisCara(Venda vendaMaisCara) {
        this.vendaMaisCara = vendaMaisCara;
    }

    public Venda getPiorVenda() {
        return piorVenda;
    }

    public void setPiorVenda(Venda piorVenda) {
        this.piorVenda = piorVenda;
    }
}
