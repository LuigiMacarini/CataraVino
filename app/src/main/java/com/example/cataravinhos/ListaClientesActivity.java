package com.example.cataravinhos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cataravinhos.helper.DBOpenHelper;
import com.example.cataravinhos.model.CadastroModel;

import java.util.ArrayList;

public class ListaClientesActivity extends AppCompatActivity {

    EditText edtBuscarCliente;
    ListView listViewClientes;
    Button btnNovoCliente;

    ArrayList<String> listaClientes;
    ArrayAdapter<String> adapter;

    DBOpenHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);

        edtBuscarCliente = findViewById(R.id.edtBuscarCliente);
        listViewClientes = findViewById(R.id.listViewClientes);
        btnNovoCliente = findViewById(R.id.btnNovoCliente);

        dbHelper = new DBOpenHelper(this);
        listaClientes = new ArrayList<>();

        carregarClientes("");

        // Filtro
        edtBuscarCliente.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                carregarClientes(s.toString());
            }
        });

        // nav cadastro
        btnNovoCliente.setOnClickListener(v -> {
            Intent intent = new Intent(ListaClientesActivity.this, CadastroActivity.class);
            startActivity(intent);
        });
    }

    private void carregarClientes(String filtro) {
        listaClientes.clear();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT * FROM " + CadastroModel.TABELA_CADASTRO;
        String[] args = null;

        if (!filtro.isEmpty()) {
            query += " WHERE " + CadastroModel.COLUNA_NOME + " LIKE ?";
            args = new String[]{"%" + filtro + "%"};
        }

        Cursor cursor = db.rawQuery(query, args);

        if (cursor.moveToFirst()) {
            do {
                String nome = cursor.getString(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_NOME));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_EMAIL));
                listaClientes.add(nome + " - " + email);
            } while (cursor.moveToNext());
        } else {
            listaClientes.add("Nenhum cliente encontrado.");
        }

        cursor.close();
        db.close();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaClientes);
        listViewClientes.setAdapter(adapter);
    }
}
