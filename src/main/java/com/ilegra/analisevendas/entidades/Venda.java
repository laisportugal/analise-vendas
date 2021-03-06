package com.ilegra.analisevendas.entidades;

import javax.persistence.*;
import java.math.BigDecimal;
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


}
