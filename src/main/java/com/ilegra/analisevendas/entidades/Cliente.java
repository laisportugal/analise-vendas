package com.ilegra.analisevendas.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity (name ="cliente")
public class Cliente {

    @Id
    private Integer id;

    @Column(name="cpnj")
    private String cnpj;

    @Column(name="nome")
    private String nome;

    @Column(name="areaNegocio")
    private String areaNegocio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAreaNegocio() {
        return areaNegocio;
    }

    public void setAreaNegocio(String areaNegocio) {
        this.areaNegocio = areaNegocio;
    }
}
