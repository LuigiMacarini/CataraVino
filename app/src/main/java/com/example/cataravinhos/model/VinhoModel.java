package com.example.cataravinhos.model;

public class VinhoModel {
    public static final String TABELA_VINHO = "tb_vinhos";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABELA_VINHO + " (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome TEXT NOT NULL, " +
                    "preco REAL NOT NULL)";

    private int id;
    private String nome;
    private double preco;

    public VinhoModel() {}

    public VinhoModel(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
}
