package com.example.cataravinhos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    Button btnCadastroClientes, btnProdutos,
            btnPedidos, btnRoteirizador, btnConsultaPedidos, btnComissoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnCadastroClientes = findViewById(R.id.btnCadastroClientes);
        btnProdutos = findViewById(R.id.btnProdutos);
        btnPedidos = findViewById(R.id.btnPedidos);
        btnRoteirizador = findViewById(R.id.btnRoteirizador);
        btnConsultaPedidos = findViewById(R.id.btnConsultaPedidos);
        btnComissoes = findViewById(R.id.btnComissoes);
        btnCadastroClientes.setOnClickListener(v -> abrirTela(ListaClientesActivity.class));
        btnProdutos.setOnClickListener(v -> abrirTela(ListaVinhosActivity.class));
        btnPedidos.setOnClickListener(v -> abrirTela(CadastroPedidoActivity.class));
        btnConsultaPedidos.setOnClickListener(v -> abrirTela(ConsultaPedidoActivity.class));
        btnComissoes.setOnClickListener(v -> abrirTela(ConsultaComissaoActivity.class));
    }

    private void abrirTela(Class<?> activityClass) {
        Intent intent = new Intent(DashboardActivity.this, activityClass);
        startActivity(intent);
    }
}
