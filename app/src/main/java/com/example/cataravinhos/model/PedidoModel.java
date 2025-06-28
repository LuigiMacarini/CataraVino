package com.example.cataravinhos.model;

public class PedidoModel {

    public static final String TABELA_PEDIDOS = "pedidos";

    public static final String COLUNA_ID = "id";
    public static final String COLUNA_USER_ID = "user_id";
    public static final String COLUNA_REPRESENTANTE_ID = "representante_id";
    public static final String COLUNA_VALOR_TOTAL = "valor_total";
    public static final String COLUNA_COMISSAO = "comissao";
    public static final String COLUNA_STATUS = "status";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABELA_PEDIDOS + " (" +
                    COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUNA_USER_ID + " INTEGER NOT NULL, " +
                    COLUNA_REPRESENTANTE_ID + " INTEGER NOT NULL, " +
                    COLUNA_VALOR_TOTAL + " REAL NOT NULL, " +
                    COLUNA_COMISSAO + " REAL NOT NULL, " +
                    COLUNA_STATUS + " TEXT NOT NULL DEFAULT 'PENDENTE'" +
                    ");";

    private int id;
    private int userId;
    private int representanteId;
    private double valorTotal;
    private double comissao;
    private String status;

    public PedidoModel() {
    }

    public PedidoModel(int id, int userId, int representanteId, double valorTotal, double comissao, String status) {
        this.id = id;
        this.userId = userId;
        this.representanteId = representanteId;
        this.valorTotal = valorTotal;
        this.comissao = comissao;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRepresentanteId() {
        return representanteId;
    }

    public void setRepresentanteId(int representanteId) {
        this.representanteId = representanteId;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getComissao() {
        return comissao;
    }

    public void setComissao(double comissao) {
        this.comissao = comissao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
