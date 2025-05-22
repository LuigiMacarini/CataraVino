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
    public static final String COLUNA_PERFIL = "perfil";

    // Constantes para os tipos de perfil
    public static final String PERFIL_USER = "user";
    public static final String PERFIL_ADMIN = "layout/admin";
    public static final String PERFIL_REPRESENTANTE = "representante";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABELA_CADASTRO + " (" +
                    COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUNA_NOME + " TEXT NOT NULL, " +
                    COLUNA_CPF_CNPJ + " TEXT NOT NULL, " +
                    COLUNA_ENDERECO + " TEXT, " +
                    COLUNA_RESPONSAVEL + " TEXT, " +
                    COLUNA_CONTATO + " TEXT, " +
                    COLUNA_EMAIL + " TEXT NOT NULL, " +
                    COLUNA_SENHA + " TEXT NOT NULL, " +
                    COLUNA_PERFIL + " TEXT NOT NULL DEFAULT '" + PERFIL_USER + "');";

    private int id;
    private String nome;
    private String cpfOuCnpj;
    private String endereco;
    private String responsavel;
    private String contato;
    private String email;
    private String senha;
    private String perfil;

    public CadastroModel() {
        this.perfil = PERFIL_USER; // Perfil padrão
    }

    public CadastroModel(int id, String nome, String cpfOuCnpj, String endereco,
                         String responsavel, String contato, String email, String senha, String perfil) {
        this.id = id;
        this.nome = nome;
        this.cpfOuCnpj = cpfOuCnpj;
        this.endereco = endereco;
        this.responsavel = responsavel;
        this.contato = contato;
        this.email = email;
        this.senha = senha;
        this.perfil = perfil != null ? perfil : PERFIL_USER;
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

    public String getPerfil() { return perfil; }

    public void setPerfil(String perfil) {
        this.perfil = perfil != null ? perfil : PERFIL_USER;
    }

    // Métodos auxiliares para verificar perfil
    public boolean isAdmin() {
        return PERFIL_ADMIN.equals(this.perfil);
    }

    public boolean isRepresentante() {
        return PERFIL_REPRESENTANTE.equals(this.perfil);
    }

    public boolean isUser() {
        return PERFIL_USER.equals(this.perfil);
    }
}