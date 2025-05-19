package com.example.cataravinhos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class CatalogoActivity extends AppCompatActivity {
    private ImageButton menuButton, cartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);

        menuButton = findViewById(R.id.menuButton);
        cartButton = findViewById(R.id.cartButton);

        menuButton.setOnClickListener(v -> {
            // Vai para a tela Home
            startActivity(new Intent(this, Home.class));
        });

        cartButton.setOnClickListener(v -> {
            // Vai para a tela do carrinho
            startActivity(new Intent(this, CartActivity.class));
        });
    }
}