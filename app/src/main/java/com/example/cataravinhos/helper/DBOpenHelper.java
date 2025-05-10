package com.example.cataravinhos.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cataravinhos.model.CadastroModel;

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NOME = "banco.db";
    private static final int DATABASE_VERSAO = 1;

    public DBOpenHelper(Context context) {
        super(context, DATABASE_NOME, null, DATABASE_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Criação da tabela de cadastro
        db.execSQL(CadastroModel.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Aqui você pode apagar e recriar as tabelas se mudar a versão futuramente
        db.execSQL("DROP TABLE IF EXISTS " + CadastroModel.TABELA_CADASTRO);
        onCreate(db);
    }
}
