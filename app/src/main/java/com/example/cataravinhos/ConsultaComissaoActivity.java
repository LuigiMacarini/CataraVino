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

public class ConsultaComissaoActivity extends AppCompatActivity {

    private AutoCompleteTextView autoRepresentanteConsulta;
    private Button btnBuscarComissoes;
    private TextView textResultadoComissoes;

    private String idRepresentanteSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_comissoes);

        autoRepresentanteConsulta = findViewById(R.id.autoRepresentanteConsulta);
        btnBuscarComissoes = findViewById(R.id.btnBuscarComissoes);
        textResultadoComissoes = findViewById(R.id.textResultadoComissoes);

        carregarRepresentantes();

        btnBuscarComissoes.setOnClickListener(v -> buscarComissoesPorRepresentante());
    }

    private void carregarRepresentantes() {
        DBOpenHelper dbHelper = new DBOpenHelper(this);
        List<String> representantes = dbHelper.buscarUsuariosPorPerfil(CadastroModel.PERFIL_REPRESENTANTE);

        ArrayAdapter<String> adapterRepresentantes = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                representantes
        );
        autoRepresentanteConsulta.setAdapter(adapterRepresentantes);
        autoRepresentanteConsulta.setOnItemClickListener((parent, view, position, id) -> {
            String selecionado = (String) parent.getItemAtPosition(position);
            String[] partes = selecionado.split(" - ", 2);
            if (partes.length == 2) {
                idRepresentanteSelecionado = partes[0].trim(); // ← SALVA O ID CORRETAMENTE
                String nomeRep = partes[1].trim();
                autoRepresentanteConsulta.setText(nomeRep);
            }
        });

    }

    private void buscarComissoesPorRepresentante() {
        if (idRepresentanteSelecionado == null || idRepresentanteSelecionado.isEmpty()) {
            Toast.makeText(this, "Selecione um representante da lista", Toast.LENGTH_SHORT).show();
            return;
        }

        PedidoDAO pedidoDAO = new PedidoDAO(this);
        List<PedidoModel> pedidos = pedidoDAO.listarPorRepresentante(Integer.parseInt(idRepresentanteSelecionado));

        if (pedidos.isEmpty()) {
            textResultadoComissoes.setText("Nenhuma comissão encontrada para o representante.");
            return;
        }

        StringBuilder builder = new StringBuilder();
        double totalComissao = 0.0;

        for (PedidoModel pedido : pedidos) {
            builder.append("Pedido ID: ").append(pedido.getId()).append("\n")
                    .append("Valor Total: R$ ").append(pedido.getValorTotal()).append("\n")
                    .append("Comissão: R$ ").append(pedido.getComissao()).append("\n")
                    .append("Status: ").append(pedido.getStatus()).append("\n\n");

            totalComissao += pedido.getComissao();
        }

        builder.append("Total de Comissão: R$ ").append(String.format("%.2f", totalComissao));
        textResultadoComissoes.setText(builder.toString());
    }
}
