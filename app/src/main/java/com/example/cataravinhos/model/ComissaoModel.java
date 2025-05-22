package com.example.cataravinhos.model;

public class ComissaoModel {

    public static final String TABELA_COMISSAO = "Comissao";

    public static final String COLUNA_ID = "id";
    public static final String COLUNA_ID_PEDIDO = "idPedido";
    public static final String COLUNA_VALOR = "valor";
    public static final String COLUNA_STATUS_PAGAMENTO = "statusPagamento"; // pago / pendente

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABELA_COMISSAO + " (" +
                    COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUNA_ID_PEDIDO + " INTEGER NOT NULL, " +
                    COLUNA_VALOR + " REAL NOT NULL, " +
                    COLUNA_STATUS_PAGAMENTO + " TEXT NOT NULL DEFAULT 'pendente', " +
                    "FOREIGN KEY(" + COLUNA_ID_PEDIDO + ") REFERENCES " + PedidoModel.TABELA_PEDIDOS + "(" + PedidoModel.COLUNA_ID + "));";

}
