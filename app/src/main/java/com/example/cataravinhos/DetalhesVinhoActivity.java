package com.example.cataravinhos;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetalhesVinhoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_vinho);

        TextView titulo = findViewById(R.id.tituloVinho);
        TextView safra = findViewById(R.id.safraVinho);
        TextView tipo = findViewById(R.id.tipoVinho);
        TextView notas = findViewById(R.id.notasDegustacao);
        TextView harmonizacoes = findViewById(R.id.harmonizacoes);
        Button botaoVoltar = findViewById(R.id.btnVoltar);

        // Pegar os dados da intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            titulo.setText(extras.getString("nome"));
            safra.setText("Safra: " + extras.getInt("safra"));
            tipo.setText("Tipo: " + extras.getString("tipo"));
            notas.setText("Notas: " + extras.getString("notas"));
            harmonizacoes.setText("Harmonizações: " + extras.getString("harmonizacoes"));
        }

        botaoVoltar.setOnClickListener(v -> finish());
    }
}