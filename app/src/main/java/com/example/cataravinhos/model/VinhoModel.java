package com.example.cataravinhos.model;

public class VinhoModel {

    public static final String TABELA_VINHO = "vinho";

    // SQL para criar a tabela
    public static final String CREATE_TABLE = "CREATE TABLE " + TABELA_VINHO + " ("
            + "nome TEXT PRIMARY KEY,"
            + "preco REAL,"
            + "descricao TEXT,"
            + "ano INTEGER,"
            + "genero TEXT"
            + ");";

    private String nome;
    private double preco;
    private String descricao;
    private int ano;
    private String genero;

    public VinhoModel() {}

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
}
