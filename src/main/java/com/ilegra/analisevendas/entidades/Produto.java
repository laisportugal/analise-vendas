package com.ilegra.analisevendas.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity (name="produto")
public class Produto {

    @Id
    private Integer id;

    @Column(name="quantidade")
    private Integer quantidade;

    @Column(name="preco")
    private BigDecimal preco;

    public Produto() {
    }

    public Produto(Integer id, Integer quantidade, BigDecimal preco) {
        this.id = id;
             this.quantidade = quantidade;
        this.preco = preco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        if (preco != null){
            this.preco = preco;
        }
        else{
            this.preco=BigDecimal.ZERO;
        }
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        if (quantidade != null){
            this.quantidade = quantidade;
        }
        else{
            this.quantidade=0;
        }
    }


}
