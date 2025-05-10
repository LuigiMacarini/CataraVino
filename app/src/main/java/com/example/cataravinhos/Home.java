package com.example.cataravinhos;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout accordionMenu = findViewById(R.id.accordionMenu);
        ImageButton menuButton = findViewById(R.id.menuButton);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (accordionMenu.getVisibility() == View.GONE) {
                    accordionMenu.setVisibility(View.VISIBLE);
                } else {
                    accordionMenu.setVisibility(View.GONE);
                }
            }
        });

    }
}
