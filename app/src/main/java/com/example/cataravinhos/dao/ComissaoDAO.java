package com.example.cataravinhos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cataravinhos.helper.DBOpenHelper;
import com.example.cataravinhos.model.ComissaoModel;
import com.example.cataravinhos.model.PedidoModel;

import java.util.ArrayList;
import java.util.List;

public class ComissaoDAO {

    private final SQLiteDatabase db;

    public ComissaoDAO(Context context) {
        DBOpenHelper dbHelper = new DBOpenHelper(context);
        this.db = dbHelper.getWritableDatabase();
    }

    public long inserirComissao(ComissaoModel comissao) {
        ContentValues values = new ContentValues();
        values.put(ComissaoModel.COLUNA_ID_PEDIDO, comissao.getIdPedido());
        values.put(ComissaoModel.COLUNA_REPRESENTANTE_ID, comissao.getRepresentanteId());
        values.put(ComissaoModel.COLUNA_PERCENTUAL, comissao.getPercentual());
        values.put(ComissaoModel.COLUNA_VALOR, comissao.getValor());
        values.put(ComissaoModel.COLUNA_STATUS_PAGAMENTO, comissao.getStatusPagamento());
        values.put(ComissaoModel.COLUNA_DATA_PREVISTA, comissao.getDataPrevista());
        values.put(ComissaoModel.COLUNA_DATA_PAGAMENTO, comissao.getDataPagamento());

        return db.insert(ComissaoModel.TABELA_COMISSAO, null, values);
    }

    public List<ComissaoModel> listarPorRepresentante(int representanteId) {
        List<ComissaoModel> lista = new ArrayList<>();

        Cursor cursor = db.query(
                ComissaoModel.TABELA_COMISSAO,
                null,
                ComissaoModel.COLUNA_REPRESENTANTE_ID + "=?",
                new String[]{String.valueOf(representanteId)},
                null, null, null
        );

        if (cursor.moveToFirst()) {
            do {
                ComissaoModel comissao = new ComissaoModel();
                comissao.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ComissaoModel.COLUNA_ID)));
                comissao.setIdPedido(cursor.getInt(cursor.getColumnIndexOrThrow(ComissaoModel.COLUNA_ID_PEDIDO)));
                comissao.setRepresentanteId(cursor.getInt(cursor.getColumnIndexOrThrow(ComissaoModel.COLUNA_REPRESENTANTE_ID)));
                comissao.setPercentual(cursor.getDouble(cursor.getColumnIndexOrThrow(ComissaoModel.COLUNA_PERCENTUAL)));
                comissao.setValor(cursor.getDouble(cursor.getColumnIndexOrThrow(ComissaoModel.COLUNA_VALOR)));
                comissao.setStatusPagamento(cursor.getString(cursor.getColumnIndexOrThrow(ComissaoModel.COLUNA_STATUS_PAGAMENTO)));
                comissao.setDataPrevista(cursor.getString(cursor.getColumnIndexOrThrow(ComissaoModel.COLUNA_DATA_PREVISTA)));
                comissao.setDataPagamento(cursor.getString(cursor.getColumnIndexOrThrow(ComissaoModel.COLUNA_DATA_PAGAMENTO)));

                lista.add(comissao);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return lista;
    }

    public boolean atualizarStatusPagamento(int idComissao, String novoStatus, String dataPagamento) {
        ContentValues values = new ContentValues();
        values.put(ComissaoModel.COLUNA_STATUS_PAGAMENTO, novoStatus);
        values.put(ComissaoModel.COLUNA_DATA_PAGAMENTO, dataPagamento);

        int resultado = db.update(
                ComissaoModel.TABELA_COMISSAO,
                values,
                ComissaoModel.COLUNA_ID + "=?",
                new String[]{String.valueOf(idComissao)}
        );

        return resultado > 0;
    }

    public List<ComissaoModel> listarComissoesFiltradas(
            Integer representanteId,
            Integer clienteId,
            String dataInicio,
            String dataFim
    ) {
        List<ComissaoModel> lista = new ArrayList<>();

        StringBuilder query = new StringBuilder();
        query.append("SELECT c.* FROM ").append(ComissaoModel.TABELA_COMISSAO).append(" c ");
        query.append("JOIN ").append(PedidoModel.TABELA_PEDIDOS).append(" p ");
        query.append("ON c.").append(ComissaoModel.COLUNA_ID_PEDIDO).append(" = p.").append(PedidoModel.COLUNA_ID);
        query.append(" WHERE 1=1 ");

        List<String> args = new ArrayList<>();

        if (representanteId != null) {
            query.append("AND c.").append(ComissaoModel.COLUNA_REPRESENTANTE_ID).append(" = ? ");
            args.add(String.valueOf(representanteId));
        }

        if (clienteId != null) {
            query.append("AND p.").append(PedidoModel.COLUNA_USER_ID).append(" = ? ");
            args.add(String.valueOf(clienteId));
        }

        if (dataInicio != null && dataFim != null) {
            query.append("AND c.").append(ComissaoModel.COLUNA_DATA_PREVISTA).append(" BETWEEN ? AND ? ");
            args.add(dataInicio);
            args.add(dataFim);
        }

        Cursor cursor = db.rawQuery(query.toString(), args.toArray(new String[0]));

        if (cursor.moveToFirst()) {
            do {
                ComissaoModel comissao = new ComissaoModel();
                comissao.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ComissaoModel.COLUNA_ID)));
                comissao.setIdPedido(cursor.getInt(cursor.getColumnIndexOrThrow(ComissaoModel.COLUNA_ID_PEDIDO)));
                comissao.setRepresentanteId(cursor.getInt(cursor.getColumnIndexOrThrow(ComissaoModel.COLUNA_REPRESENTANTE_ID)));
                comissao.setPercentual(cursor.getDouble(cursor.getColumnIndexOrThrow(ComissaoModel.COLUNA_PERCENTUAL)));
                comissao.setValor(cursor.getDouble(cursor.getColumnIndexOrThrow(ComissaoModel.COLUNA_VALOR)));
                comissao.setStatusPagamento(cursor.getString(cursor.getColumnIndexOrThrow(ComissaoModel.COLUNA_STATUS_PAGAMENTO)));
                comissao.setDataPrevista(cursor.getString(cursor.getColumnIndexOrThrow(ComissaoModel.COLUNA_DATA_PREVISTA)));
                comissao.setDataPagamento(cursor.getString(cursor.getColumnIndexOrThrow(ComissaoModel.COLUNA_DATA_PAGAMENTO)));

                lista.add(comissao);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return lista;
    }

}
