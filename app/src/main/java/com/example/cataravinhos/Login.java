package com.example.cataravinhos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cataravinhos.dao.CadastroDAO;
import com.example.cataravinhos.model.CadastroModel;

public class Login extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private CadastroDAO cadastroDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.loginButton);

        cadastroDAO = new CadastroDAO(this);

        loginButton.setOnClickListener(view -> {
            String email = emailEditText.getText().toString().trim();
            String senha = passwordEditText.getText().toString().trim();

            if (email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            CadastroModel cliente = cadastroDAO.autenticar(email, senha);
            if (cliente != null) {
                Toast.makeText(this, "Bem-vindo, " + cliente.getNome(), Toast.LENGTH_SHORT).show();

                Intent intent;
                if (cliente.isAdmin() || cliente.isRepresentante()) {
                    // Vai para DashboardActivity se for admin ou representante
                    intent = new Intent(Login.this, DashboardActivity.class);
                } else if (cliente.isUser()) {
                    // Vai para Home se for usuário comum
                    intent = new Intent(Login.this, Home.class);
                } else {
                    // Caso o perfil não bata com nenhum, pode ir para Home por segurança
                    intent = new Intent(Login.this, Home.class);
                }

                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Email ou senha incorretos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
