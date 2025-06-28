package com.example.cataravinhos;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.cataravinhos.dao.VinhoDAO;
import com.example.cataravinhos.model.VinhoModel;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.util.List;

public class CatalogoActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageButton menuButton, cartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        menuButton = findViewById(R.id.menuButton);
        cartButton = findViewById(R.id.cartButton);

        menuButton.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        cartButton.setOnClickListener(v -> startActivity(new Intent(this, Home.class)));

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_wines) {
                startActivity(new Intent(this, CatalogoActivity.class));
            } else if (id == R.id.nav_genres) {
                startActivity(new Intent(this, CatalogoActivity.class));
            } else if (id == R.id.nav_contact) {
                startActivity(new Intent(this, ContactActivity.class));
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        carregarVinhosDoBanco();
    }

    private void carregarVinhosDoBanco() {
        VinhoDAO vinhoDAO = new VinhoDAO(this);
        List<VinhoModel> listaVinhos = vinhoDAO.listarVinhos();

        LinearLayout container = findViewById(R.id.containerVinhos);
        container.removeAllViews();

        LinearLayout linha = null;
        int count = 0;

        for (VinhoModel vinho : listaVinhos) {
            if (count % 2 == 0) {
                linha = new LinearLayout(this);
                linha.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                linha.setOrientation(LinearLayout.HORIZONTAL);
                linha.setGravity(Gravity.CENTER);
                linha.setPadding(0, 0, 0, 12);
                container.addView(linha);
            }

            LinearLayout card = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1
            );
            params.setMarginEnd(6);
            if (count % 2 == 1) {
                params.setMarginStart(6);
                params.setMarginEnd(0);
            }
            card.setLayoutParams(params);
            card.setOrientation(LinearLayout.VERTICAL);
            card.setBackgroundColor(Color.WHITE);
            card.setPadding(8, 8, 8, 8);
            card.setElevation(4f);

            // Imagem
            ImageView imagem = new ImageView(this);
            imagem.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    180
            ));
            imagem.setScaleType(ImageView.ScaleType.CENTER_CROP);
            if (vinho.getImagem() != null && !vinho.getImagem().isEmpty()) {
                File imgFile = new File(vinho.getImagem());
                if (imgFile.exists()) {
                    imagem.setImageURI(Uri.fromFile(imgFile));
                } else {
                    imagem.setImageResource(R.drawable.vinho);
                }
            } else {
                imagem.setImageResource(R.drawable.vinho);
            }

            // Nome
            TextView nome = new TextView(this);
            nome.setText(vinho.getNome());
            nome.setGravity(Gravity.CENTER);
            nome.setTypeface(null, Typeface.BOLD);
            nome.setPadding(0, 8, 0, 0);

            // Safra e Tipo
            TextView safraTipo = new TextView(this);
            safraTipo.setText("Safra " + vinho.getSafra() + "\n" + vinho.getTipo());
            safraTipo.setGravity(Gravity.CENTER);

            // Preço
            TextView preco = new TextView(this);
            preco.setText("R$ 750"); // Adicione campo 'preco' se desejar
            preco.setGravity(Gravity.CENTER);
            preco.setTypeface(null, Typeface.BOLD);
            preco.setPadding(0, 4, 0, 0);

            // Adiciona ao card
            card.addView(imagem);
            card.addView(nome);
            card.addView(safraTipo);
            card.addView(preco);

            card.setOnClickListener(v -> abrirDetalhes(
                    vinho.getNome(),
                    vinho.getSafra(),
                    vinho.getTipo(),
                    vinho.getNotasDegustacao(),
                    vinho.getHarmonizacoes(),
                    vinho.getImagem()
            ));

            linha.addView(card);
            count++;
        }
    }

    private void abrirDetalhes(String nome, int safra, String tipo, String notas, String harmonizacoes, String imagemPath) {
        Intent intent = new Intent(this, DetalhesVinhoActivity.class);
        intent.putExtra("nome", nome);
        intent.putExtra("safra", safra);
        intent.putExtra("tipo", tipo);
        intent.putExtra("notas", notas);
        intent.putExtra("harmonizacoes", harmonizacoes);
        intent.putExtra("imagemPath", imagemPath); // novo
        startActivity(intent);
    }
}