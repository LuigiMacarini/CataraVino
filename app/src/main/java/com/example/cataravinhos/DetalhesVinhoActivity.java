package com.example.cataravinhos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetalhesVinhoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_vinho); // Certifique-se que o nome do XML é esse

        // Recupera os dados passados via Intent
        Intent intent = getIntent();
        String nome = intent.getStringExtra("nome");
        int safra = intent.getIntExtra("safra", 0);
        String tipo = intent.getStringExtra("tipo");
        String notas = intent.getStringExtra("notas");
        String harmonizacoes = intent.getStringExtra("harmonizacoes");
        int imagemResId = intent.getIntExtra("imagemResId", R.drawable.vinho); // imagem padrão vinho.png

        // Referencia os elementos do layout
        ImageView imagemVinho = findViewById(R.id.imagemVinho);
        TextView titulo = findViewById(R.id.tituloVinho);
        TextView tipoView = findViewById(R.id.tipoVinho);
        TextView safraView = findViewById(R.id.safraVinho);
        TextView notasView = findViewById(R.id.notasDegustacao);
        TextView harmonizacoesView = findViewById(R.id.harmonizacoes);
        Button btnVoltar = findViewById(R.id.btnVoltar);

        // Define os valores nos componentes
        imagemVinho.setImageResource(imagemResId);
        titulo.setText(nome);
        tipoView.setText("Tipo: " + tipo);
        safraView.setText("Safra: " + safra);
        notasView.setText("Notas: " + notas);
        harmonizacoesView.setText("Harmonizações: " + harmonizacoes);

        // Ação do botão "Voltar"
        btnVoltar.setOnClickListener(v -> finish());
    }
}