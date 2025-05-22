package com.example.cataravinhos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class CatalogoActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageButton menuButton, cartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);

        // Inicializa componentes
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        menuButton = findViewById(R.id.menuButton);
        cartButton = findViewById(R.id.cartButton);

        // Menu lateral
        menuButton.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        // Botão do carrinho
        cartButton.setOnClickListener(v -> startActivity(new Intent(this, CartActivity.class)));

        // Menu lateral: navegação
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_wines) {
                startActivity(new Intent(this, WinesActivity.class));
            } else if (id == R.id.nav_genres) {
                startActivity(new Intent(this, GenresActivity.class));
            } else if (id == R.id.nav_contact) {
                startActivity(new Intent(this, ContactActivity.class));
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        // Clique nos vinhos
        configurarCliqueVinhos();
    }

    private void configurarCliqueVinhos() {
        LinearLayout vinho1 = findViewById(R.id.vinho1);
        LinearLayout vinho2 = findViewById(R.id.vinho2);
        LinearLayout vinho3 = findViewById(R.id.vinho3);
        LinearLayout vinho4 = findViewById(R.id.vinho4);

        vinho1.setOnClickListener(v -> abrirDetalhes(
                "Textura Wines", 2018, "Tinto",
                "Notas de frutas vermelhas, taninos macios",
                "Carnes vermelhas, massas com molho vermelho"));

        vinho2.setOnClickListener(v -> abrirDetalhes(
                "Quinta das Arcas", 2020, "Branco",
                "Aromas cítricos e florais, leve acidez",
                "Peixes, saladas, frutos do mar"));

        vinho3.setOnClickListener(v -> abrirDetalhes(
                "Miolo Reserva", 2019, "Rosé",
                "Notas de morango e cereja, refrescante",
                "Petiscos, queijos leves, pratos leves"));

        vinho4.setOnClickListener(v -> abrirDetalhes(
                "Casa Valduga", 2021, "Espumante",
                "Perlage fina, sabor frutado, boa acidez",
                "Comida japonesa, entradas leves"));
    }

    private void abrirDetalhes(String nome, int safra, String tipo, String notas, String harmonizacoes) {
        Intent intent = new Intent(this, DetalhesVinhoActivity.class);
        intent.putExtra("nome", nome);
        intent.putExtra("safra", safra);
        intent.putExtra("tipo", tipo);
        intent.putExtra("notas", notas);
        intent.putExtra("harmonizacoes", harmonizacoes);
        startActivity(intent);
    }
}