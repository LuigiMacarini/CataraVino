package com.example.cataravinhos.model;

public class CadastroModel {

    public static final String TABELA_CADASTRO = "Cadastro";

    public static final String COLUNA_ID = "id";
    public static final String COLUNA_NOME = "nome";
    public static final String COLUNA_CPF_CNPJ = "cpfOuCnpj";
    public static final String COLUNA_ENDERECO = "endereco";
    public static final String COLUNA_RESPONSAVEL = "responsavel";
    public static final String COLUNA_CONTATO = "contato";
    public static final String COLUNA_EMAIL = "email";
    public static final String COLUNA_SENHA = "senha";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABELA_CADASTRO + " (" +
                    COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUNA_NOME + " TEXT NOT NULL, " +
                    COLUNA_CPF_CNPJ + " TEXT NOT NULL, " +
                    COLUNA_ENDERECO + " TEXT, " +
                    COLUNA_RESPONSAVEL + " TEXT, " +
                    COLUNA_CONTATO + " TEXT, " +
                    COLUNA_EMAIL + " TEXT NOT NULL, " +
                    COLUNA_SENHA + " TEXT NOT NULL);";

    private int id;
    private String nome;
    private String cpfOuCnpj;
    private String endereco;
    private String responsavel;
    private String contato;
    private String email;
    private String senha;

    public CadastroModel() {}

    public CadastroModel(int id, String nome, String cpfOuCnpj, String endereco,
                         String responsavel, String contato, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.cpfOuCnpj = cpfOuCnpj;
        this.endereco = endereco;
        this.responsavel = responsavel;
        this.contato = contato;
        this.email = email;
        this.senha = senha;
    }

    // Getters e Setters
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getCpfOuCnpj() { return cpfOuCnpj; }

    public void setCpfOuCnpj(String cpfOuCnpj) { this.cpfOuCnpj = cpfOuCnpj; }

    public String getEndereco() { return endereco; }

    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getResponsavel() { return responsavel; }

    public void setResponsavel(String responsavel) { this.responsavel = responsavel; }

    public String getContato() { return contato; }

    public void setContato(String contato) { this.contato = contato; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }

    public void setSenha(String senha) { this.senha = senha; }
}
