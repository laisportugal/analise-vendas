package com.ilegra.analisevendas.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity (name="produto")
public class Produto {

    @Id
    private Integer id;

    @Column(name="preco")
    private Double preco;

    @Column(name="nome")
    private String nome;

    @Column(name="quantidade")
    private Integer quantidade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        if (preco != null){
            this.preco = preco;
        }
        else{
            this.preco=0d;
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
