package com.example.cataravinhos;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cataravinhos.dao.PedidoDAO;
import com.example.cataravinhos.model.PedidoModel;

public class CadastroPedidoActivity extends AppCompatActivity {

    private EditText etClienteId, etRepresentanteId, etValorTotal, etComissao, etStatus;
    private Button btnSalvarPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pedido);

        inicializarComponentes();

        btnSalvarPedido.setOnClickListener(v -> salvarPedido());
    }

    private void inicializarComponentes() {
        etClienteId = findViewById(R.id.etClienteId);
        etRepresentanteId = findViewById(R.id.etRepresentanteId);
        etValorTotal = findViewById(R.id.etValorTotal);
        etComissao = findViewById(R.id.etComissao);
        etStatus = findViewById(R.id.etStatus);
        btnSalvarPedido = findViewById(R.id.btnSalvarPedido);
    }


    private void salvarPedido() {
        try {
            PedidoModel pedido = new PedidoModel();
            pedido.setUserId(Integer.parseInt(etClienteId.getText().toString()));
            pedido.setRepresentanteId(Integer.parseInt(etRepresentanteId.getText().toString()));
            pedido.setValorTotal(Double.parseDouble(etValorTotal.getText().toString()));
            pedido.setComissao(Double.parseDouble(etComissao.getText().toString()));
            pedido.setStatus(etStatus.getText().toString().toUpperCase());

            PedidoDAO pedidoDAO = new PedidoDAO(this);
            long resultado = pedidoDAO.inserirPedido(pedido);

            if (resultado > 0) {
                Toast.makeText(this, "Pedido salvo com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Erro ao salvar o pedido.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Preencha todos os campos corretamente.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
