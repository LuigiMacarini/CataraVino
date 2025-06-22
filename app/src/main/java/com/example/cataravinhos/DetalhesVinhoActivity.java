package com.example.cataravinhos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class DetalhesVinhoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_vinho);

        Intent intent = getIntent();
        String nome = intent.getStringExtra("nome");
        int safra = intent.getIntExtra("safra", 0);
        String tipo = intent.getStringExtra("tipo");
        String notas = intent.getStringExtra("notas");
        String harmonizacoes = intent.getStringExtra("harmonizacoes");

        String imagemPath = intent.getStringExtra("imagemPath");

        ImageView imagemVinho = findViewById(R.id.imagemVinho);
        TextView titulo = findViewById(R.id.tituloVinho);
        TextView tipoView = findViewById(R.id.tipoVinho);
        TextView safraView = findViewById(R.id.safraVinho);
        TextView notasView = findViewById(R.id.notasDegustacao);
        TextView harmonizacoesView = findViewById(R.id.harmonizacoes);
        Button btnVoltar = findViewById(R.id.btnVoltar);

        // Define a imagem: local ou drawable padrão
        if (imagemPath != null && !imagemPath.isEmpty()) {
            File imgFile = new File(imagemPath);
            if (imgFile.exists()) {
                imagemVinho.setImageURI(Uri.fromFile(imgFile));
            } else {
                imagemVinho.setImageResource(R.drawable.vinho);
            }
        } else {
            imagemVinho.setImageResource(R.drawable.vinho);
        }

        titulo.setText(nome);
        tipoView.setText("Tipo: " + tipo);
        safraView.setText("Safra: " + safra);
        notasView.setText("Notas: " + notas);
        harmonizacoesView.setText("Harmonizações: " + harmonizacoes);

        btnVoltar.setOnClickListener(v -> finish());
    }
}