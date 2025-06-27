package com.example.cataravinhos;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cataravinhos.dao.ComissaoDAO;
import com.example.cataravinhos.model.ComissaoModel;

import java.util.List;

public class ActivityComissao extends AppCompatActivity {

    private EditText editDataInicio, editDataFim, editRepresentanteId;
    private RecyclerView recyclerComissoes;
    private ComissaoDAO comissaoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_comissoes);

        editDataInicio = findViewById(R.id.editDataInicio);
        editDataFim = findViewById(R.id.editDataFim);
        editRepresentanteId = findViewById(R.id.editRepresentanteId);
        recyclerComissoes = findViewById(R.id.recyclerComissoes);

        recyclerComissoes.setLayoutManager(new LinearLayoutManager(this));
        comissaoDAO = new ComissaoDAO(this);

        findViewById(R.id.btnFiltrar).setOnClickListener(view -> filtrar());
    }

    private void filtrar() {
        String dataInicio = editDataInicio.getText().toString().trim();
        String dataFim = editDataFim.getText().toString().trim();
        String idRepresentanteStr = editRepresentanteId.getText().toString().trim();

        Integer representanteId = null;
        if (!idRepresentanteStr.isEmpty()) {
            representanteId = Integer.parseInt(idRepresentanteStr);
        }

        List<ComissaoModel> lista = comissaoDAO.listarComissoesFiltradas(
                representanteId,
                null,
                dataInicio.isEmpty() ? null : dataInicio,
                dataFim.isEmpty() ? null : dataFim
        );

        recyclerComissoes.setAdapter(new ComissaoAdapter(lista));
    }
}
