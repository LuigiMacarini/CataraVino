package com.example.cataravinhos.model;

public class VinhoModel {

    public static final String TABELA_VINHO = "vinho";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABELA_VINHO + " ("
            + "nome TEXT PRIMARY KEY,"
            + "safra INTEGER,"
            + "tipo TEXT,"
            + "notasDegustacao TEXT,"
            + "harmonizacoes TEXT,"
            + "imagem TEXT"
            + ");";

    private String nome;
    private int safra;
    private String tipo;
    private String notasDegustacao;
    private String harmonizacoes;
    private String imagem;

    public VinhoModel() {}

    // Getters e Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getSafra() {
        return safra;
    }

    public void setSafra(int safra) {
        this.safra = safra;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNotasDegustacao() {
        return notasDegustacao;
    }

    public void setNotasDegustacao(String notasDegustacao) {
        this.notasDegustacao = notasDegustacao;
    }

    public String getHarmonizacoes() {
        return harmonizacoes;
    }

    public void setHarmonizacoes(String harmonizacoes) {
        this.harmonizacoes = harmonizacoes;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
