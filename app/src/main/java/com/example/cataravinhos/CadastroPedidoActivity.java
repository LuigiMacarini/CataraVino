package com.example.cataravinhos;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cataravinhos.dao.PedidoDAO;
import com.example.cataravinhos.helper.DBOpenHelper;
import com.example.cataravinhos.model.CadastroModel;
import com.example.cataravinhos.model.PedidoModel;

import java.util.List;

public class CadastroPedidoActivity extends AppCompatActivity {

    private AutoCompleteTextView etClienteId, etRepresentanteId;
    private EditText etValorTotal, etComissao, etStatus;
    private Button btnSalvarPedido, btnListarPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pedido);

        inicializarComponentes();

        carregarClientes();
        carregarRepresentantes();

        btnSalvarPedido.setOnClickListener(v -> salvarPedido());
        btnListarPedidos.setOnClickListener(v -> mostrarPedidos());
    }

    private void inicializarComponentes() {
        etClienteId = findViewById(R.id.etClienteId);
        etRepresentanteId = findViewById(R.id.etRepresentanteId);
        etValorTotal = findViewById(R.id.etValorTotal);
        etComissao = findViewById(R.id.etComissao);
        etStatus = findViewById(R.id.etStatus);
        btnSalvarPedido = findViewById(R.id.btnSalvarPedido);
        btnListarPedidos = findViewById(R.id.btnListarPedidos);
    }

    private void carregarClientes() {
        DBOpenHelper dbHelper = new DBOpenHelper(this);
        List<String> clientes = dbHelper.buscarUsuariosPorPerfil(CadastroModel.PERFIL_USER);

        ArrayAdapter<String> adapterClientes = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                clientes
        );
        etClienteId.setAdapter(adapterClientes);
        etClienteId.setOnItemClickListener((parent, view, position, id) -> {
            String selecionado = (String) parent.getItemAtPosition(position);
            String idCliente = selecionado.split(" - ")[0].trim();
            etClienteId.setText(idCliente);
        });
    }

    private void carregarRepresentantes() {
        DBOpenHelper dbHelper = new DBOpenHelper(this);
        List<String> representantes = dbHelper.buscarUsuariosPorPerfil(CadastroModel.PERFIL_REPRESENTANTE);

        ArrayAdapter<String> adapterRepresentantes = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                representantes
        );
        etRepresentanteId.setAdapter(adapterRepresentantes);
        etRepresentanteId.setOnItemClickListener((parent, view, position, id) -> {
            String selecionado = (String) parent.getItemAtPosition(position);
            String idRep = selecionado.split(" - ")[0].trim();
            etRepresentanteId.setText(idRep);
        });
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
            long resultado = pedidoDAO.salvar(pedido);

            if (resultado > 0) {
                Toast.makeText(this, "Pedido salvo com sucesso!", Toast.LENGTH_SHORT).show();
                limparCampos();
            } else {
                Toast.makeText(this, "Erro ao salvar o pedido.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Preencha todos os campos corretamente.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void limparCampos() {
        etClienteId.setText("");
        etRepresentanteId.setText("");
        etValorTotal.setText("");
        etComissao.setText("");
        etStatus.setText("");
    }

    private void mostrarPedidos() {
        PedidoDAO pedidoDAO = new PedidoDAO(this);
        List<PedidoModel> pedidos = pedidoDAO.listarPedidos();

        if (pedidos.isEmpty()) {
            Toast.makeText(this, "Nenhum pedido cadastrado.", Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuilder builder = new StringBuilder();
        for (PedidoModel pedido : pedidos) {
            builder.append("ID Cliente: ").append(pedido.getUserId())
                    .append(", Representante: ").append(pedido.getRepresentanteId())
                    .append(", Valor: ").append(pedido.getValorTotal())
                    .append(", Comissão: ").append(pedido.getComissao())
                    .append(", Status: ").append(pedido.getStatus())
                    .append("\n\n");
        }

        new android.app.AlertDialog.Builder(this)
                .setTitle("Pedidos Cadastrados")
                .setMessage(builder.toString())
                .setPositiveButton("OK", null)
                .show();
    }
}
