package com.example.cataravinhos;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cataravinhos.dao.VinhoDAO;
import com.example.cataravinhos.model.VinhoModel;

import java.util.ArrayList;
import java.util.List;

public class ListaVinhosActivity extends AppCompatActivity {

    private EditText editPesquisaVinho;
    private RecyclerView recyclerViewListaVinhos;
    private Button btnCadastrarNovoVinho;
    private VinhoAdapter adapter;
    private List<VinhoModel> listaVinhos;
    private VinhoDAO vinhoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_vinhos);

        editPesquisaVinho = findViewById(R.id.editPesquisaVinho);
        recyclerViewListaVinhos = findViewById(R.id.recyclerViewListaVinhos);
        btnCadastrarNovoVinho = findViewById(R.id.btnCadastrarNovoVinho);

        vinhoDAO = new VinhoDAO(this);
        listaVinhos = vinhoDAO.listarVinhos();

        adapter = new VinhoAdapter(this, listaVinhos);
        recyclerViewListaVinhos.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewListaVinhos.setAdapter(adapter);

        // Filtro de pesquisa simples
        editPesquisaVinho.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                filtrarVinhos(s.toString());
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        btnCadastrarNovoVinho.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddVinhoActivity.class);
            startActivity(intent);
        });
    }

    private void filtrarVinhos(String texto) {
        List<VinhoModel> listaFiltrada = new ArrayList<>();
        for (VinhoModel vinho : listaVinhos) {
            if (vinho.getNome().toLowerCase().contains(texto.toLowerCase())) {
                listaFiltrada.add(vinho);
            }
        }
        adapter.atualizarLista(listaFiltrada);
    }
}

