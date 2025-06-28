package com.example.cataravinhos;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cataravinhos.dao.PedidoDAO;
import com.example.cataravinhos.helper.DBOpenHelper;
import com.example.cataravinhos.model.CadastroModel;
import com.example.cataravinhos.model.PedidoModel;

import java.util.List;

public class ConsultaPedidoActivity extends AppCompatActivity {

    private AutoCompleteTextView autoClienteConsulta;
    private Button btnBuscarPedidos;
    private TextView textResultadoPedidos;

    private String idClienteSelecionado = null;
    private String nomeClienteSelecionado = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_pedido);

        autoClienteConsulta = findViewById(R.id.autoClienteConsulta);
        btnBuscarPedidos = findViewById(R.id.btnBuscarPedidos);
        textResultadoPedidos = findViewById(R.id.textResultadoPedidos);

        carregarClientes();

        autoClienteConsulta.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) autoClienteConsulta.showDropDown();
        });

        btnBuscarPedidos.setOnClickListener(v -> buscarPedidosPorCliente());
    }

    private void carregarClientes() {
        DBOpenHelper dbHelper = new DBOpenHelper(this);
        List<String> clientes = dbHelper.buscarUsuariosPorPerfil(CadastroModel.PERFIL_USER);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                clientes
        );

        autoClienteConsulta.setAdapter(adapter);
        autoClienteConsulta.setOnItemClickListener((parent, view, position, id) -> {
            String selecionado = (String) parent.getItemAtPosition(position);
            if (selecionado.contains(" - ")) {
                String[] partes = selecionado.split(" - ");
                idClienteSelecionado = partes[0].trim();
                nomeClienteSelecionado = partes[1].trim();
            }
        });
    }

    private void buscarPedidosPorCliente() {
        if (idClienteSelecionado == null || idClienteSelecionado.isEmpty()) {
            Toast.makeText(this, "Selecione um cliente da lista", Toast.LENGTH_SHORT).show();
            return;
        }

        PedidoDAO dao = new PedidoDAO(this);
        List<PedidoModel> pedidos = dao.listarPorCliente(Integer.parseInt(idClienteSelecionado));

        if (pedidos.isEmpty()) {
            textResultadoPedidos.setText("Nenhum pedido encontrado para o cliente.");
            return;
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Cliente: ").append(nomeClienteSelecionado).append("\n\n");

        for (PedidoModel pedido : pedidos) {
            builder.append("Valor: R$ ").append(pedido.getValorTotal()).append("\n")
                    .append("Comissão: R$ ").append(pedido.getComissao()).append("\n")
                    .append("Status: ").append(pedido.getStatus()).append("\n\n");
        }

        textResultadoPedidos.setText(builder.toString());
    }
}
