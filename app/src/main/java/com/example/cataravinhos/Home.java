package com.example.cataravinhos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class Home extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageButton menuButton,cartButton;
    private Button saibaMaisButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        menuButton = findViewById(R.id.menuButton);
        cartButton = findViewById(R.id.cartButton);
        saibaMaisButton = findViewById(R.id.saibaMaisButton);


        menuButton.setOnClickListener(v -> {
            drawerLayout.openDrawer(navigationView);
        });


        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_wines) {
                startActivity(new Intent(this, CatalogoActivity.class));
            }
            else if (id == R.id.nav_genres) {
                startActivity(new Intent(this, CatalogoActivity.class));
            }
            else if (id == R.id.nav_contact) {
                startActivity(new Intent(this, ContactActivity.class));
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
        cartButton.setOnClickListener(v -> {
            startActivity(new Intent(this, CartActivity.class));
        });
        saibaMaisButton.setOnClickListener(v -> {
            startActivity(new Intent(this, Info.class));
        });

    }
}