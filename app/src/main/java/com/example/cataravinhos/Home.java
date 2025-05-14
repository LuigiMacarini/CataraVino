package com.example.cataravinhos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class Home extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Botões e menus
        Button menuButton = findViewById(R.id.menuButton);
        LinearLayout accordionMenu = findViewById(R.id.accordionMenu);
        ImageButton cartButton = findViewById(R.id.cartButton);

        TextView winesMenuItem = findViewById(R.id.winesMenuItem);
        TextView genresMenuItem = findViewById(R.id.genresMenuItem);
        TextView contactMenuItem = findViewById(R.id.contactMenuItem);

        // Toggle do menu
        menuButton.setOnClickListener(v -> {
            if (accordionMenu.getVisibility() == View.VISIBLE) {
                accordionMenu.setVisibility(View.GONE);
            } else {
                accordionMenu.setVisibility(View.VISIBLE);
            }
        });

        Button saibaMaisButton = findViewById(R.id.saibaMaisButton);
        saibaMaisButton.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, Historia.class);
            startActivity(intent);
        });


        // Ação do carrinho
        cartButton.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, CartActivity.class);
            startActivity(intent);
        });

        // Ação do menu Vinhos
        winesMenuItem.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, WinesActivity.class);
            startActivity(intent);
        });

        // Ação do menu Gêneros
        genresMenuItem.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, GenresActivity.class);
            startActivity(intent);
        });

        // Ação do menu Contato
        contactMenuItem.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, ContactActivity.class);
            startActivity(intent);
        });

        saibaMaisButton.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, Historia.class);
            startActivity(intent);
        });

    }
}
