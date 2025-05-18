package com.example.cataravinhos.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cataravinhos.model.CadastroModel;
import com.example.cataravinhos.model.VinhoModel;

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NOME   = "banco.db";
    private static final int    DATABASE_VERSAO = 3;      // << aumente sempre que mudar o schema

    public DBOpenHelper(Context context) {
        super(context, DATABASE_NOME, null, DATABASE_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Cria TODAS as tabelas necessárias
        db.execSQL(CadastroModel.CREATE_TABLE);
        db.execSQL(VinhoModel.CREATE_TABLE);
        // Se tiver mais modelos, adicione aqui
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Estratégia simples: descarta e recria todas as tabelas.
        // Em produção, prefira migrações específicas.
        db.execSQL("DROP TABLE IF EXISTS " + CadastroModel.TABELA_CADASTRO);
        db.execSQL("DROP TABLE IF EXISTS " + VinhoModel.TABELA_VINHO);

        onCreate(db);
    }
}
