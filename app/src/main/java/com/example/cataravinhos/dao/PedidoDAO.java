package com.example.cataravinhos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cataravinhos.helper.DBOpenHelper;
import com.example.cataravinhos.model.PedidoModel;

import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    private final SQLiteDatabase escreve;
    private final SQLiteDatabase le;

    public PedidoDAO(Context context) {
        DBOpenHelper db = new DBOpenHelper(context);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }

    // Salvar novo pedido
    public boolean salvar(PedidoModel pedido) {
        ContentValues valores = new ContentValues();
        valores.put(PedidoModel.COLUNA_USER_ID, pedido.getUserId());
        valores.put(PedidoModel.COLUNA_REPRESENTANTE_ID, pedido.getRepresentanteId());
        valores.put(PedidoModel.COLUNA_VALOR_TOTAL, pedido.getValorTotal());
        valores.put(PedidoModel.COLUNA_COMISSAO, pedido.getComissao());
        valores.put(PedidoModel.COLUNA_STATUS, pedido.getStatus());

        try {
            escreve.insert(PedidoModel.TABELA_PEDIDOS, null, valores);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Buscar todos os pedidos de um representante
    public List<PedidoModel> listarPorRepresentante(int representanteId) {
        List<PedidoModel> lista = new ArrayList<>();

        Cursor cursor = le.query(
                PedidoModel.TABELA_PEDIDOS,
                null,
                PedidoModel.COLUNA_REPRESENTANTE_ID + "=?",
                new String[]{String.valueOf(representanteId)},
                null, null, null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                PedidoModel pedido = new PedidoModel();
                pedido.setId(cursor.getInt(cursor.getColumnIndexOrThrow(PedidoModel.COLUNA_ID)));
                pedido.setUserId(cursor.getInt(cursor.getColumnIndexOrThrow(PedidoModel.COLUNA_USER_ID)));
                pedido.setRepresentanteId(cursor.getInt(cursor.getColumnIndexOrThrow(PedidoModel.COLUNA_REPRESENTANTE_ID)));
                pedido.setValorTotal(cursor.getDouble(cursor.getColumnIndexOrThrow(PedidoModel.COLUNA_VALOR_TOTAL)));
                pedido.setComissao(cursor.getDouble(cursor.getColumnIndexOrThrow(PedidoModel.COLUNA_COMISSAO)));
                pedido.setStatus(cursor.getString(cursor.getColumnIndexOrThrow(PedidoModel.COLUNA_STATUS)));

                lista.add(pedido);
            } while (cursor.moveToNext());
        }

        if (cursor != null) cursor.close();
        return lista;
    }

    // Atualizar status de um pedido
    public boolean atualizarStatus(int pedidoId, String novoStatus) {
        ContentValues valores = new ContentValues();
        valores.put(PedidoModel.COLUNA_STATUS, novoStatus);

        try {
            int resultado = escreve.update(
                    PedidoModel.TABELA_PEDIDOS,
                    valores,
                    PedidoModel.COLUNA_ID + "=?",
                    new String[]{String.valueOf(pedidoId)}
            );
            return resultado > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Buscar pedidos por cliente
    public List<PedidoModel> listarPorCliente(int userId) {
        List<PedidoModel> lista = new ArrayList<>();

        Cursor cursor = le.query(
                PedidoModel.TABELA_PEDIDOS,
                null,
                PedidoModel.COLUNA_USER_ID + "=?",
                new String[]{String.valueOf(userId)},
                null, null, null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                PedidoModel pedido = new PedidoModel();
                pedido.setId(cursor.getInt(cursor.getColumnIndexOrThrow(PedidoModel.COLUNA_ID)));
                pedido.setUserId(cursor.getInt(cursor.getColumnIndexOrThrow(PedidoModel.COLUNA_USER_ID)));
                pedido.setRepresentanteId(cursor.getInt(cursor.getColumnIndexOrThrow(PedidoModel.COLUNA_REPRESENTANTE_ID)));
                pedido.setValorTotal(cursor.getDouble(cursor.getColumnIndexOrThrow(PedidoModel.COLUNA_VALOR_TOTAL)));
                pedido.setComissao(cursor.getDouble(cursor.getColumnIndexOrThrow(PedidoModel.COLUNA_COMISSAO)));
                pedido.setStatus(cursor.getString(cursor.getColumnIndexOrThrow(PedidoModel.COLUNA_STATUS)));

                lista.add(pedido);
            } while (cursor.moveToNext());
        }

        if (cursor != null) cursor.close();
        return lista;
    }

    public long inserirPedido(PedidoModel pedido) {
        ContentValues valores = new ContentValues();
        valores.put(PedidoModel.COLUNA_USER_ID, pedido.getUserId());
        valores.put(PedidoModel.COLUNA_REPRESENTANTE_ID, pedido.getRepresentanteId());
        valores.put(PedidoModel.COLUNA_VALOR_TOTAL, pedido.getValorTotal());
        valores.put(PedidoModel.COLUNA_COMISSAO, pedido.getComissao());
        valores.put(PedidoModel.COLUNA_STATUS, pedido.getStatus());

        return escreve.insert(PedidoModel.TABELA_PEDIDOS, null, valores);
    }

}
