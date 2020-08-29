package com.ilegra.analisevendas.entidades;

import com.ilegra.analisevendas.dto.RelatorioDTO;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Entity (name="venda")
public class Venda {
    @Id
    private Integer id;

    @Column(name="valorTotal")
    private BigDecimal valorTotal;

    @OneToMany (mappedBy = "venda")
    private List<Produto> produtos;

    @OneToOne
    private Vendedor vendedor;

    public Venda(Integer id, BigDecimal valorTotal, List<Produto> produtos, Vendedor vendedor) {
        this.id = id;
        this.valorTotal = valorTotal;
        this.produtos = produtos;
        this.vendedor = vendedor;
    }

    public Venda() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void preencherRelatorioVenda(){
        List<Venda> vendas = Arrays.asList(new Venda());
        BigDecimal valorTotal = vendas
                .stream()
                .map(Venda::getValorTotal)
                .reduce(BigDecimal.ZERO,
                        BigDecimal::add);
       RelatorioDTO relatorioDTO = new RelatorioDTO();
       relatorioDTO.setVendaMaisCara(vendas.stream().min(Comparator.comparing(Venda::getValorTotal)).orElse(null));
       relatorioDTO.setPiorVenda(vendas.stream().max(Comparator.comparing(Venda::getValorTotal)).orElse(null));
    }

}
