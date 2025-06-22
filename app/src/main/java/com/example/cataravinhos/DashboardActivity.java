package com.example.cataravinhos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    Button btnCadastroClientes, btnConsultaClientes, btnProdutos,
            btnPedidos, btnRoteirizador, btnConsultaPedidos, btnComissoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnCadastroClientes = findViewById(R.id.btnCadastroClientes);
        btnConsultaClientes = findViewById(R.id.btnConsultaClientes);
        btnProdutos = findViewById(R.id.btnProdutos);
        btnPedidos = findViewById(R.id.btnPedidos);
        btnRoteirizador = findViewById(R.id.btnRoteirizador);
        btnConsultaPedidos = findViewById(R.id.btnConsultaPedidos);
        btnComissoes = findViewById(R.id.btnComissoes);

        // Atualizado: redirecionar para ListaClientesActivity
        btnCadastroClientes.setOnClickListener(v -> abrirTela(ListaClientesActivity.class));
        btnProdutos.setOnClickListener(v -> abrirTela(ListaVinhosActivity.class));
        btnPedidos.setOnClickListener(v -> abrirTela(CadastroPedidoActivity.class));
        // Outros botões podem ser configurados aqui também
    }

    private void abrirTela(Class<?> activityClass) {
        Intent intent = new Intent(DashboardActivity.this, activityClass);
        startActivity(intent);
    }
}
