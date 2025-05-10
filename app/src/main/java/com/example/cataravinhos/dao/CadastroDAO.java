package com.example.cataravinhos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cataravinhos.helper.DBOpenHelper;
import com.example.cataravinhos.model.CadastroModel;

public class CadastroDAO {

    private final SQLiteDatabase escreve;
    private final SQLiteDatabase le;

    public CadastroDAO(Context context) {
        DBOpenHelper db = new DBOpenHelper(context);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }

    // Inserir cliente
    public boolean salvar(CadastroModel cadastro) {
        ContentValues valores = new ContentValues();
        valores.put(CadastroModel.COLUNA_NOME, cadastro.getNome());
        valores.put(CadastroModel.COLUNA_CPF_CNPJ, cadastro.getCpfOuCnpj());
        valores.put(CadastroModel.COLUNA_ENDERECO, cadastro.getEndereco());
        valores.put(CadastroModel.COLUNA_RESPONSAVEL, cadastro.getResponsavel());
        valores.put(CadastroModel.COLUNA_CONTATO, cadastro.getContato());
        valores.put(CadastroModel.COLUNA_EMAIL, cadastro.getEmail());
        valores.put(CadastroModel.COLUNA_SENHA, cadastro.getSenha());

        try {
            escreve.insert(CadastroModel.TABELA_CADASTRO, null, valores);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Verificar login
    // Verificar login
    public CadastroModel autenticar(String email, String senha) {
        CadastroModel model = null;

        Cursor cursor = le.query(
                CadastroModel.TABELA_CADASTRO,
                null,
                CadastroModel.COLUNA_EMAIL + "=? AND " + CadastroModel.COLUNA_SENHA + "=?",
                new String[]{email, senha},
                null, null, null
        );

        if (cursor != null && cursor.moveToFirst()) {
            model = new CadastroModel();
            model.setId(cursor.getInt(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_ID)));
            model.setNome(cursor.getString(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_NOME)));
            model.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_EMAIL)));
            // Adicione mais campos se quiser
        }

        if (cursor != null) cursor.close();
        return model;
    }

}
