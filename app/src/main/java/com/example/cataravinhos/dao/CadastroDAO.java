package com.example.cataravinhos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cataravinhos.helper.DBOpenHelper;
import com.example.cataravinhos.model.CadastroModel;

import java.util.ArrayList;
import java.util.List;

public class CadastroDAO {

    private final SQLiteDatabase escreve;
    private final SQLiteDatabase le;

    public CadastroDAO(Context context) {
        DBOpenHelper db = new DBOpenHelper(context);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }

    // Inserir cadastro
    public boolean salvar(CadastroModel cadastro) {
        ContentValues valores = new ContentValues();
        valores.put(CadastroModel.COLUNA_NOME, cadastro.getNome());
        valores.put(CadastroModel.COLUNA_CPF_CNPJ, cadastro.getCpfOuCnpj());
        valores.put(CadastroModel.COLUNA_ENDERECO, cadastro.getEndereco());
        valores.put(CadastroModel.COLUNA_RESPONSAVEL, cadastro.getResponsavel());
        valores.put(CadastroModel.COLUNA_CONTATO, cadastro.getContato());
        valores.put(CadastroModel.COLUNA_EMAIL, cadastro.getEmail());
        valores.put(CadastroModel.COLUNA_SENHA, cadastro.getSenha());
        valores.put(CadastroModel.COLUNA_PERFIL, cadastro.getPerfil());

        try {
            escreve.insert(CadastroModel.TABELA_CADASTRO, null, valores);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

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
            model.setCpfOuCnpj(cursor.getString(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_CPF_CNPJ)));
            model.setEndereco(cursor.getString(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_ENDERECO)));
            model.setResponsavel(cursor.getString(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_RESPONSAVEL)));
            model.setContato(cursor.getString(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_CONTATO)));
            model.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_EMAIL)));
            model.setSenha(cursor.getString(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_SENHA)));
            model.setPerfil(cursor.getString(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_PERFIL)));
        }

        if (cursor != null) cursor.close();
        return model;
    }

    // Buscar usuários por perfil
    public List<CadastroModel> buscarPorPerfil(String perfil) {
        List<CadastroModel> lista = new ArrayList<>();

        Cursor cursor = le.query(
                CadastroModel.TABELA_CADASTRO,
                null,
                CadastroModel.COLUNA_PERFIL + "=?",
                new String[]{perfil},
                null, null, null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                CadastroModel model = new CadastroModel();
                model.setId(cursor.getInt(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_ID)));
                model.setNome(cursor.getString(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_NOME)));
                model.setCpfOuCnpj(cursor.getString(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_CPF_CNPJ)));
                model.setEndereco(cursor.getString(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_ENDERECO)));
                model.setResponsavel(cursor.getString(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_RESPONSAVEL)));
                model.setContato(cursor.getString(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_CONTATO)));
                model.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_EMAIL)));
                model.setPerfil(cursor.getString(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_PERFIL)));

                lista.add(model);
            } while (cursor.moveToNext());
        }

        if (cursor != null) cursor.close();
        return lista;
    }

    // Atualizar perfil de um usuário
    public boolean atualizarPerfil(int id, String novoPerfil) {
        ContentValues valores = new ContentValues();
        valores.put(CadastroModel.COLUNA_PERFIL, novoPerfil);

        try {
            int resultado = escreve.update(
                    CadastroModel.TABELA_CADASTRO,
                    valores,
                    CadastroModel.COLUNA_ID + "=?",
                    new String[]{String.valueOf(id)}
            );
            return resultado > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Verificar se é admin
    public boolean isAdmin(String email) {
        CadastroModel user = buscarPorEmail(email);
        return user != null && user.isAdmin();
    }

    // Buscar usuário por email
    public CadastroModel buscarPorEmail(String email) {
        CadastroModel model = null;

        Cursor cursor = le.query(
                CadastroModel.TABELA_CADASTRO,
                null,
                CadastroModel.COLUNA_EMAIL + "=?",
                new String[]{email},
                null, null, null
        );

        if (cursor != null && cursor.moveToFirst()) {
            model = new CadastroModel();
            model.setId(cursor.getInt(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_ID)));
            model.setNome(cursor.getString(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_NOME)));
            model.setCpfOuCnpj(cursor.getString(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_CPF_CNPJ)));
            model.setEndereco(cursor.getString(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_ENDERECO)));
            model.setResponsavel(cursor.getString(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_RESPONSAVEL)));
            model.setContato(cursor.getString(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_CONTATO)));
            model.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_EMAIL)));
            model.setPerfil(cursor.getString(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_PERFIL)));
        }

        if (cursor != null) cursor.close();
        return model;
    }

    public void logAllData() {
        SQLiteDatabase db = le;
        Cursor cursor = db.query(
                CadastroModel.TABELA_CADASTRO,
                null,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                StringBuilder sb = new StringBuilder();
                sb.append("ID: ").append(cursor.getInt(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_ID)));
                sb.append(", Nome: ").append(cursor.getString(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_NOME)));
                sb.append(", Email: ").append(cursor.getString(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_EMAIL)));
                sb.append(", Perfil: ").append(cursor.getString(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_PERFIL)));

                android.util.Log.d("CadastroDAO", sb.toString());
            } while (cursor.moveToNext());
        }

        cursor.close();
    }
}