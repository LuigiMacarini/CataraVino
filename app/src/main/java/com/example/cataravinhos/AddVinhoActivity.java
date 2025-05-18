package com.example.cataravinhos;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cataravinhos.dao.VinhoDAO;
import com.example.cataravinhos.model.VinhoModel;

public class AddVinhoActivity extends AppCompatActivity {

    private EditText editNome, editPreco;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wine);

        editNome = findViewById(R.id.editNome);
        editPreco = findViewById(R.id.editPreco);
        btnSalvar = findViewById(R.id.btnSalvarVinho);

        btnSalvar.setOnClickListener(v -> {
            String nome = editNome.getText().toString().trim();
            String precoTexto = editPreco.getText().toString().trim();

            if (nome.isEmpty() || precoTexto.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            double preco;
            try {
                preco = Double.parseDouble(precoTexto);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Preço inválido", Toast.LENGTH_SHORT).show();
                return;
            }

            VinhoModel vinho = new VinhoModel(nome, preco);
            VinhoDAO dao = new VinhoDAO(this);

            if (dao.inserirVinho(vinho)) {
                Toast.makeText(this, "Vinho salvo com sucesso", Toast.LENGTH_SHORT).show();
                finish(); // Fecha a tela
            } else {
                Toast.makeText(this, "Erro ao salvar vinho", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
