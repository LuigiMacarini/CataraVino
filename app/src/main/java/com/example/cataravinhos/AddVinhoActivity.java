package com.example.cataravinhos;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cataravinhos.dao.VinhoDAO;
import com.example.cataravinhos.model.VinhoModel;

import java.util.List;

public class AddVinhoActivity extends AppCompatActivity {

    private EditText editNome, editPreco, editDescricao, editAno;
    private Button btnSalvar;

    private VinhoDAO vinhoDAO;
    private TextView textListaVinhos;
    private EditText editNomeParaApagar;

    private EditText editGenero;

    private Button btnApagar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wine);




        textListaVinhos = findViewById(R.id.textListaVinhos);
        editGenero = findViewById(R.id.editGenero);
        editNomeParaApagar = findViewById(R.id.editNomeParaApagar);
        editNome = findViewById(R.id.editNome);
        editPreco = findViewById(R.id.editPreco);
        editDescricao = findViewById(R.id.editDescricao);
        editAno = findViewById(R.id.editAno);
        btnSalvar = findViewById(R.id.btnSalvarVinho);
        Button btnListar = findViewById(R.id.btnListar); // NOVO

        vinhoDAO = new VinhoDAO(this);

        btnApagar = findViewById(R.id.btnApagar);

        btnApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apagarVinho();
            }
        });


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarVinho();
            }
        });



        btnListar.setOnClickListener(new View.OnClickListener() { // NOVO
            @Override
            public void onClick(View v) {
                listarVinhos();
            }
        });


        listarVinhos(); // opcional
    }



    private void salvarVinho() {
        String nome = editNome.getText().toString().trim();
        String precoStr = editPreco.getText().toString().trim();
        String descricao = editDescricao.getText().toString().trim();
        String anoStr = editAno.getText().toString().trim();
        String genero = editGenero.getText().toString().trim();

        if (nome.isEmpty() || precoStr.isEmpty() || anoStr.isEmpty() || genero.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos obrigatórios", Toast.LENGTH_SHORT).show();
            return;
        }

        double preco = Double.parseDouble(precoStr);
        int ano = Integer.parseInt(anoStr);

        VinhoModel vinho = new VinhoModel();
        vinho.setNome(nome);
        vinho.setPreco(preco);
        vinho.setDescricao(descricao);
        vinho.setAno(ano);
        vinho.setGenero(genero);

        boolean sucesso = vinhoDAO.inserirVinho(vinho);
        if (sucesso) {
            Toast.makeText(this, "Vinho salvo com sucesso!", Toast.LENGTH_SHORT).show();
            limparCampos();
            listarVinhos();
        } else {
            Toast.makeText(this, "Erro ao salvar o vinho", Toast.LENGTH_SHORT).show();
        }
    }

    private void apagarVinho() {
        String nome = editNomeParaApagar.getText().toString().trim();
        if (nome.isEmpty()) {
            Toast.makeText(this, "Digite o nome do vinho para apagar", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean apagou = vinhoDAO.apagarVinho(nome);

        if (apagou) {
            Toast.makeText(this, "Vinho apagado com sucesso!", Toast.LENGTH_SHORT).show();
            editNomeParaApagar.setText("");
            listarVinhos();
        } else {
            Toast.makeText(this, "Erro ao apagar ou vinho não encontrado", Toast.LENGTH_SHORT).show();
        }
    }




    private void limparCampos() {
        editNome.setText("");
        editPreco.setText("");
        editDescricao.setText("");
        editAno.setText("");
        editGenero.setText("");
    }


    private void listarVinhos() {
        List<VinhoModel> lista = vinhoDAO.listarVinhos();
        StringBuilder sb = new StringBuilder();

        for (VinhoModel vinho : lista) {
            sb.append("Nome: ").append(vinho.getNome())
                    .append(" | Preço: R$").append(vinho.getPreco())
                    .append(" | Ano: ").append(vinho.getAno())
                    .append(" | Gênero: ").append(vinho.getGenero())
                    .append(" | Desc: ").append(vinho.getDescricao())
                    .append("\n\n");
        }

        if (sb.length() == 0) {
            textListaVinhos.setText("Nenhum vinho cadastrado.");
        } else {
            textListaVinhos.setText(sb.toString());
        }
    }



}
