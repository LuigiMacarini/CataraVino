package com.example.cataravinhos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cataravinhos.helper.DBOpenHelper;
import com.example.cataravinhos.model.VinhoModel;

import java.util.ArrayList;
import java.util.List;

public class VinhoDAO {

    private final DBOpenHelper dbHelper;

    public VinhoDAO(Context context) {
        this.dbHelper = new DBOpenHelper(context);
    }

    public boolean inserirVinho(VinhoModel vinho) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("nome", vinho.getNome());
        valores.put("preco", vinho.getPreco());

        long resultado = db.insert(VinhoModel.TABELA_VINHO, null, valores);
        db.close();
        return resultado != -1;
    }

    public List<VinhoModel> listarVinhos() {
        List<VinhoModel> lista = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sql = "SELECT * FROM " + VinhoModel.TABELA_VINHO + " ORDER BY nome";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                VinhoModel vinho = new VinhoModel();
                vinho.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                vinho.setNome(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
                vinho.setPreco(cursor.getDouble(cursor.getColumnIndexOrThrow("preco")));

                lista.add(vinho);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lista;
    }
}
