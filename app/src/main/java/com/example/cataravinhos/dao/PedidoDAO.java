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
    // No PedidoDAO.java
    public long salvar(PedidoModel pedido) {
        ContentValues valores = new ContentValues();
        valores.put(PedidoModel.COLUNA_USER_ID, pedido.getUserId());
        valores.put(PedidoModel.COLUNA_REPRESENTANTE_ID, pedido.getRepresentanteId());
        valores.put(PedidoModel.COLUNA_VALOR_TOTAL, pedido.getValorTotal());
        valores.put(PedidoModel.COLUNA_COMISSAO, pedido.getComissao());
        valores.put(PedidoModel.COLUNA_STATUS, pedido.getStatus());

        try {
            long idPedido = escreve.insert(PedidoModel.TABELA_PEDIDOS, null, valores);

            if (idPedido != -1) {
                // Você pode incluir aqui a lógica para salvar comissão, se quiser
                return idPedido;
            } else {
                return -1;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // Buscar todos os pedidos (sem filtro)
    public List<PedidoModel> listarPedidos() {
        List<PedidoModel> lista = new ArrayList<>();

        Cursor cursor = le.query(
                PedidoModel.TABELA_PEDIDOS,
                null,       // todas as colunas
                null,       // sem cláusula WHERE
                null,
                null,
                null,
                null
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

    // Listar pedidos com nome do cliente (usando JOIN)
    public List<String> listarPedidosComNomePorCliente(int userId) {
        List<String> lista = new ArrayList<>();

        String query = "SELECT p.*, u.nome " +
                "FROM " + PedidoModel.TABELA_PEDIDOS + " p " +
                "JOIN usuarios u ON p." + PedidoModel.COLUNA_USER_ID + " = u.id " +
                "WHERE p." + PedidoModel.COLUNA_USER_ID + " = ?";

        Cursor cursor = le.rawQuery(query, new String[]{String.valueOf(userId)});

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String nomeCliente = cursor.getString(cursor.getColumnIndexOrThrow("nome"));
                double valorTotal = cursor.getDouble(cursor.getColumnIndexOrThrow(PedidoModel.COLUNA_VALOR_TOTAL));
                double comissao = cursor.getDouble(cursor.getColumnIndexOrThrow(PedidoModel.COLUNA_COMISSAO));
                String status = cursor.getString(cursor.getColumnIndexOrThrow(PedidoModel.COLUNA_STATUS));

                String resultado = "Cliente: " + nomeCliente + "\n" +
                        "Valor: R$ " + valorTotal + "\n" +
                        "Comissão: R$ " + comissao + "\n" +
                        "Status: " + status;

                lista.add(resultado);
            } while (cursor.moveToNext());
        }

        if (cursor != null) cursor.close();
        return lista;
    }


}
