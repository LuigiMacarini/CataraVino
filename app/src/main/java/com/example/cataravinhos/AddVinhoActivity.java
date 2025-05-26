package com.example.cataravinhos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cataravinhos.dao.VinhoDAO;
import com.example.cataravinhos.model.VinhoModel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class AddVinhoActivity extends AppCompatActivity {

    private EditText editNome, editSafra, editTipo, editNotas, editHarmonizacoes;
    private Button btnSalvar, btnApagar, btnSelecionarImagem;
    private EditText editNomeParaApagar;
    private TextView textListaVinhos;
    private ImageView imageViewPreview;
    private String imagemUriSelecionada = null;




    private VinhoDAO vinhoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wine);

        editNome = findViewById(R.id.editNome);
        editSafra = findViewById(R.id.editSafra);
        editTipo = findViewById(R.id.editTipo);
        editNotas = findViewById(R.id.editNotas);
        editHarmonizacoes = findViewById(R.id.editHarmonizacoes);
        editNomeParaApagar = findViewById(R.id.editNomeParaApagar);
        textListaVinhos = findViewById(R.id.textListaVinhos);
        imageViewPreview = findViewById(R.id.imageViewPreview);
        btnSalvar = findViewById(R.id.btnSalvarVinho);
        btnApagar = findViewById(R.id.btnApagar);
        btnSelecionarImagem = findViewById(R.id.btnSelecionarImagem);
        btnSelecionarImagem.setOnClickListener(v -> abrirGaleria());

        vinhoDAO = new VinhoDAO(this);

       //FUNÇÃO PARA APAGAR O DB DEPOIS DE TESTAR
        // VinhoDAO dao = new VinhoDAO(this);
        //dao.apagarTodos(); // apaga todos os vinhos
        // apagarTodasImagens(); // apaga imagens locais


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarVinho();
            }
        });

        btnApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apagarVinho();
            }
        });


        listarVinhos(); //lista
    }


    private static final int PICK_IMAGE_REQUEST = 1;

    private void abrirGaleria() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri imagemUri = data.getData();


            String caminhoSalvo = salvarImagemInternamente(imagemUri);
            if (caminhoSalvo != null) {
                imagemUriSelecionada = caminhoSalvo;
                imageViewPreview.setImageURI(Uri.fromFile(new File(caminhoSalvo)));
                btnSelecionarImagem.setText("Imagem selecionada");
            } else {
                Toast.makeText(this, "Erro ao salvar imagem", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void salvarVinho() {
        String nome = editNome.getText().toString().trim();
        String safraStr = editSafra.getText().toString().trim();
        String tipo = editTipo.getText().toString().trim();
        String notas = editNotas.getText().toString().trim();
        String harmonizacoes = editHarmonizacoes.getText().toString().trim();
        String imagem = imagemUriSelecionada != null ? imagemUriSelecionada : "";



        if (nome.isEmpty() || safraStr.isEmpty() || tipo.isEmpty() || imagem.isEmpty())  {
            Toast.makeText(this, "Preencha os campos obrigatórios: Nome, Safra, Tipo e Imagem" , Toast.LENGTH_SHORT).show();
            return;
        }

        int safra;
        try {
            safra = Integer.parseInt(safraStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Safra deve ser um número válido", Toast.LENGTH_SHORT).show();
            return;
        }

        VinhoModel vinho = new VinhoModel();
        vinho.setNome(nome);
        vinho.setSafra(safra);
        vinho.setTipo(tipo);
        vinho.setNotasDegustacao(notas);
        vinho.setHarmonizacoes(harmonizacoes);
        vinho.setImagem(imagem);
        vinho.setImagem(imagemUriSelecionada);

        boolean sucesso = vinhoDAO.inserirVinho(vinho);
        if (sucesso) {
            Toast.makeText(this, "Vinho salvo com sucesso!", Toast.LENGTH_SHORT).show();
            limparCampos();
            listarVinhos();
        } else {
            Toast.makeText(this, "Erro ao salvar o vinho", Toast.LENGTH_SHORT).show();
        }
        limparCampos();
    }

    private String salvarImagemInternamente(Uri uri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            File diretorio = new File(getFilesDir(), "imagens");
            if (!diretorio.exists()) {
                diretorio.mkdir();
            }

            String nomeArquivo = "img_" + System.currentTimeMillis() + ".jpg";
            File imagemArquivo = new File(diretorio, nomeArquivo);

            OutputStream outputStream = new FileOutputStream(imagemArquivo);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
            }

            inputStream.close();
            outputStream.close();

            return imagemArquivo.getAbsolutePath(); // <-- salvar isso no banco
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    private void apagarVinho() {
        String nome = editNomeParaApagar.getText().toString().trim();
        if (nome.isEmpty()) {
            Toast.makeText(this, "Digite o nome do vinho para apagar", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean apagou = vinhoDAO.apagarVinho(nome);

        if (apagou) {
            Toast.makeText(this, "Vinho apagado com sucesso!", Toast.LENGTH_SHORT).show();
            editNomeParaApagar.setText("");
            listarVinhos();
        } else {
            Toast.makeText(this, "Erro ao apagar ou vinho não encontrado", Toast.LENGTH_SHORT).show();
        }
    }

   /* private void apagarTodasImagens() {
        File pastaImagens = new File(getFilesDir(), "imagens");
        if (pastaImagens.exists()) {
            for (File arquivo : pastaImagens.listFiles()) {
                arquivo.delete();
            }
        }
    } */




    private void limparCampos() {
        editNome.setText("");
        editSafra.setText("");
        editTipo.setText("");
        editNotas.setText("");
        editHarmonizacoes.setText("");
        btnSelecionarImagem.setText("");
    }



    private void listarVinhos() {
        List<VinhoModel> lista = vinhoDAO.listarVinhos();

        if (lista.isEmpty()) {
            textListaVinhos.setText("Nenhum vinho cadastrado.");
            return;
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerViewVinhos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new VinhoAdapter(lista));

        textListaVinhos.setText("");
    }
    }


