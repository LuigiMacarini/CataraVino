package com.example.cataravinhos.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cataravinhos.model.CadastroModel;
import com.example.cataravinhos.model.VinhoModel;
import com.example.cataravinhos.model.PedidoModel;
import com.example.cataravinhos.model.ComissaoModel;

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NOME = "banco.db";
    private static final int DATABASE_VERSAO = 6;

    public DBOpenHelper(Context context) {
        super(context, DATABASE_NOME, null, DATABASE_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Criação de todas as tabelas
        db.execSQL(CadastroModel.CREATE_TABLE);
        db.execSQL(VinhoModel.CREATE_TABLE);
        db.execSQL(PedidoModel.CREATE_TABLE);
        db.execSQL(ComissaoModel.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Atualizações incrementais por versão
        if (oldVersion < 4) {
            try {
                db.execSQL("ALTER TABLE " + CadastroModel.TABELA_CADASTRO +
                        " ADD COLUMN " + CadastroModel.COLUNA_PERFIL +
                        " TEXT NOT NULL DEFAULT '" + CadastroModel.PERFIL_USER + "'");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (oldVersion < 5) {
            try {
                db.execSQL(PedidoModel.CREATE_TABLE);
                db.execSQL(ComissaoModel.CREATE_TABLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (oldVersion < 6) {
            // Verifica se a coluna 'status' já existe antes de tentar adicionar
            if (!colunaExiste(db, PedidoModel.TABELA_PEDIDOS, PedidoModel.COLUNA_STATUS)) {
                try {
                    db.execSQL("ALTER TABLE " + PedidoModel.TABELA_PEDIDOS +
                            " ADD COLUMN " + PedidoModel.COLUNA_STATUS + " TEXT DEFAULT 'pendente'");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean colunaExiste(SQLiteDatabase db, String tabela, String coluna) {
        boolean existe = false;
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("PRAGMA table_info(" + tabela + ")", null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String nomeColuna = cursor.getString(1); // coluna "name"
                    if (coluna.equals(nomeColuna)) {
                        existe = true;
                        break;
                    }
                }
            }
        } finally {
            if (cursor != null) cursor.close();
        }
        return existe;
    }
}
