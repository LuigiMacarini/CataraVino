package com.example.cataravinhos.model;

public class ComissaoModel {

    public static final String TABELA_COMISSAO = "Comissao";

    public static final String COLUNA_ID = "id";
    public static final String COLUNA_ID_PEDIDO = "idPedido";
    public static final String COLUNA_REPRESENTANTE_ID = "representanteId";
    public static final String COLUNA_PERCENTUAL = "percentual";
    public static final String COLUNA_VALOR = "valor";
    public static final String COLUNA_STATUS_PAGAMENTO = "statusPagamento";
    public static final String COLUNA_DATA_PREVISTA = "dataPrevista";
    public static final String COLUNA_DATA_PAGAMENTO = "dataPagamento";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABELA_COMISSAO + " (" +
                    COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUNA_ID_PEDIDO + " INTEGER NOT NULL, " +
                    COLUNA_REPRESENTANTE_ID + " INTEGER NOT NULL, " +
                    COLUNA_PERCENTUAL + " REAL, " +
                    COLUNA_VALOR + " REAL NOT NULL, " +
                    COLUNA_STATUS_PAGAMENTO + " TEXT NOT NULL DEFAULT 'pendente', " +
                    COLUNA_DATA_PREVISTA + " TEXT, " +
                    COLUNA_DATA_PAGAMENTO + " TEXT, " +
                    "FOREIGN KEY(" + COLUNA_ID_PEDIDO + ") REFERENCES " + PedidoModel.TABELA_PEDIDOS + "(" + PedidoModel.COLUNA_ID + ")" +
                    ");";

    private int id;
    private int idPedido;
    private int representanteId;
    private double percentual;
    private double valor;
    private String statusPagamento; // pago, pendente
    private String dataPrevista;
    private String dataPagamento;

    public ComissaoModel() {}

    public ComissaoModel(int idPedido, int representanteId, double percentual, double valor, String statusPagamento) {
        this.idPedido = idPedido;
        this.representanteId = representanteId;
        this.percentual = percentual;
        this.valor = valor;
        this.statusPagamento = statusPagamento;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }

    public int getRepresentanteId() { return representanteId; }
    public void setRepresentanteId(int representanteId) { this.representanteId = representanteId; }

    public double getPercentual() { return percentual; }
    public void setPercentual(double percentual) { this.percentual = percentual; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public String getStatusPagamento() { return statusPagamento; }
    public void setStatusPagamento(String statusPagamento) { this.statusPagamento = statusPagamento; }

    public String getDataPrevista() { return dataPrevista; }
    public void setDataPrevista(String dataPrevista) { this.dataPrevista = dataPrevista; }

    public String getDataPagamento() { return dataPagamento; }
    public void setDataPagamento(String dataPagamento) { this.dataPagamento = dataPagamento; }
}
