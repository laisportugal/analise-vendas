package com.ilegra.analisevendas.entidades;

import javax.persistence.*;
import java.util.List;

@Entity (name="venda")
public class Venda {
    @Id
    private Integer id;

    @Column(name="valorTotal")
    private Double valorTotal;

    @OneToMany (mappedBy = "venda")
    private List<Produto> produtos;

    @OneToOne
    private Vendedor vendedor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produto) {
        this.produtos = produto;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
