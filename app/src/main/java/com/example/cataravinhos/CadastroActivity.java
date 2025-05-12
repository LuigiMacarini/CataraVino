package com.example.cataravinhos;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cataravinhos.helper.DBOpenHelper;
import com.example.cataravinhos.model.CadastroModel;


public class CadastroActivity extends AppCompatActivity {

    EditText edtNome, edtCpfCnpj, edtEndereco, edtResponsavel, edtContato, edtEmail, edtSenha;
    Button btnCadastrar;
    DBOpenHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad);

        edtNome = findViewById(R.id.edtNome);
        edtCpfCnpj = findViewById(R.id.edtCpfCnpj);
        edtEndereco = findViewById(R.id.edtEndereco);
        edtResponsavel = findViewById(R.id.edtResponsavel);
        edtContato = findViewById(R.id.edtContato);
        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        dbHelper = new DBOpenHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        btnCadastrar.setOnClickListener(v -> cadastrar());
    }

    private void cadastrar() {
        String nome = edtNome.getText().toString();
        String cpfCnpj = edtCpfCnpj.getText().toString();
        String endereco = edtEndereco.getText().toString();
        String responsavel = edtResponsavel.getText().toString();
        String contato = edtContato.getText().toString();
        String email = edtEmail.getText().toString();
        String senha = edtSenha.getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CadastroModel.COLUNA_NOME, nome);
        values.put(CadastroModel.COLUNA_CPF_CNPJ, cpfCnpj);
        values.put(CadastroModel.COLUNA_ENDERECO, endereco);
        values.put(CadastroModel.COLUNA_RESPONSAVEL, responsavel);
        values.put(CadastroModel.COLUNA_CONTATO, contato);
        values.put(CadastroModel.COLUNA_EMAIL, email);
        values.put(CadastroModel.COLUNA_SENHA, senha);

        long result = db.insert(CadastroModel.TABELA_CADASTRO, null, values);

        if (result != -1) {
            Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Erro ao cadastrar", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }
}
