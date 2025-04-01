package com.example.ameaasambientais;

import androidx.annotation.NonNull;

public class Ameaca {
    private Long id;
    private String endereco;
    private String data;
    private String descricao;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getEndereco(){
        return endereco;
    }

    public void setEndereco(String endereco){
        this.endereco = endereco;
    }

    public String getData(){
        return data;
    }

    public void setData(String data){
        this.data = data;
    }

    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    @NonNull
    @Override
    public String toString(){
        return id + " " + endereco + " " + data + " " + descricao;
    }
}
