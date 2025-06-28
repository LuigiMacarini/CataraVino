package com.example.cataravinhos;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cataravinhos.helper.DBOpenHelper;
import com.example.cataravinhos.model.CadastroModel;

public class CadastroActivity extends AppCompatActivity {

    EditText edtNome, edtCpfCnpj, edtEndereco, edtResponsavel, edtContato, edtEmail, edtSenha;
    Spinner spinnerPerfil;
    Button btnCadastrar;
    TextView txtResultado;
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
        spinnerPerfil = findViewById(R.id.spinnerPerfil);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        txtResultado = findViewById(R.id.txtResultado);

        // Configurar Spinner de Perfil
        setupPerfilSpinner();

        Button btnListar = findViewById(R.id.btnListarCadastros);
        btnListar.setOnClickListener(v -> listarCadastros());

        dbHelper = new DBOpenHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        btnCadastrar.setOnClickListener(v -> cadastrar());
    }

    private void setupPerfilSpinner() {
        String[] perfis = {
                CadastroModel.PERFIL_USER,
                CadastroModel.PERFIL_ADMIN,
                CadastroModel.PERFIL_REPRESENTANTE
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                perfis
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPerfil.setAdapter(adapter);
    }

    @SuppressLint("SetTextI18n")
    private void listarCadastros() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        StringBuilder resultado = new StringBuilder();

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
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_ID));
                String nome = cursor.getString(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_NOME));
                String cpfCnpj = cursor.getString(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_CPF_CNPJ));
                String endereco = cursor.getString(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_ENDERECO));
                String responsavel = cursor.getString(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_RESPONSAVEL));
                String contato = cursor.getString(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_CONTATO));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_EMAIL));
                String perfil = cursor.getString(cursor.getColumnIndexOrThrow(CadastroModel.COLUNA_PERFIL));

                resultado.append("ID: ").append(id).append("\n")
                        .append("Nome: ").append(nome).append("\n")
                        .append("CPF/CNPJ: ").append(cpfCnpj).append("\n")
                        .append("Endereço: ").append(endereco).append("\n")
                        .append("Responsável: ").append(responsavel).append("\n")
                        .append("Contato: ").append(contato).append("\n")
                        .append("Email: ").append(email).append("\n")
                        .append("Perfil: ").append(perfil).append("\n\n");
            } while (cursor.moveToNext());
        } else {
            resultado.append("Nenhum cadastro encontrado.");
        }

        cursor.close();
        db.close();

        txtResultado.setText(resultado.toString());
    }

    @SuppressLint("SetTextI18n")
    private void cadastrar() {
        String nome = edtNome.getText().toString();
        String cpfCnpj = edtCpfCnpj.getText().toString();
        String endereco = edtEndereco.getText().toString();
        String responsavel = edtResponsavel.getText().toString();
        String contato = edtContato.getText().toString();
        String email = edtEmail.getText().toString();
        String senha = edtSenha.getText().toString();
        String perfil = spinnerPerfil.getSelectedItem().toString();

        // Validação básica
        if (nome.isEmpty() || cpfCnpj.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            Toast.makeText(this, "Preencha os campos obrigatórios!", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CadastroModel.COLUNA_NOME, nome);
        values.put(CadastroModel.COLUNA_CPF_CNPJ, cpfCnpj);
        values.put(CadastroModel.COLUNA_ENDERECO, endereco);
        values.put(CadastroModel.COLUNA_RESPONSAVEL, responsavel);
        values.put(CadastroModel.COLUNA_CONTATO, contato);
        values.put(CadastroModel.COLUNA_EMAIL, email);
        values.put(CadastroModel.COLUNA_SENHA, senha);
        values.put(CadastroModel.COLUNA_PERFIL, perfil);

        long result = db.insert(CadastroModel.TABELA_CADASTRO, null, values);

        if (result != -1) {
            txtResultado.setText(
                    "Cadastro realizado com sucesso!\n" +
                            "Nome: " + nome + "\n" +
                            "CPF/CNPJ: " + cpfCnpj + "\n" +
                            "Endereço: " + endereco + "\n" +
                            "Responsável: " + responsavel + "\n" +
                            "Contato: " + contato + "\n" +
                            "Email: " + email + "\n" +
                            "Perfil: " + perfil
            );
            Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();

            // Limpa campo
            limparCampos();

            db.close();
            listarCadastros();
        } else {
            Toast.makeText(this, "Erro ao realizar cadastro!", Toast.LENGTH_SHORT).show();
            db.close();
        }
    }

    private void limparCampos() {
        edtNome.setText("");
        edtCpfCnpj.setText("");
        edtEndereco.setText("");
        edtResponsavel.setText("");
        edtContato.setText("");
        edtEmail.setText("");
        edtSenha.setText("");
        spinnerPerfil.setSelection(0); // Volta para o primeiro item
    }
}