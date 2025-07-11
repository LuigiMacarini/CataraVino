package com.example.cataravinhos;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cataravinhos.model.VinhoModel;

import java.io.File;
import java.util.List;

public class VinhoAdapter extends RecyclerView.Adapter<VinhoAdapter.VinhoViewHolder> {
    private Context context;
    private List<VinhoModel> vinhos;
//componente pro add vinho
    public VinhoAdapter(Context context, List<VinhoModel> vinhos) {
        this.context = context;
        this.vinhos = vinhos;
    }

    @NonNull
    @Override
    public VinhoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_vinho, parent, false);
        return new VinhoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VinhoViewHolder holder, int position) {
        VinhoModel vinho = vinhos.get(position);

        holder.textViewInfo.setText(
                "Nome: " + vinho.getNome() + "\n" +
                        "Safra: " + vinho.getSafra() + "\n" +
                        "Tipo: " + vinho.getTipo() + "\n" +
                        "Notas: " + vinho.getNotasDegustacao() + "\n" +
                        "Harmonizações: " + vinho.getHarmonizacoes()
        );

        // Carrega a imagem usando o URI
        if (vinho.getImagem() != null && !vinho.getImagem().isEmpty()) {
            try {
                holder.imageView.setImageURI(Uri.parse(vinho.getImagem()));
            } catch (Exception e) {
                e.printStackTrace(); // evita crash
                holder.imageView.setImageURI(Uri.fromFile(new File(vinho.getImagem())));
            }
        } else {
            holder.imageView.setImageResource(R.drawable.bottle_wine);
        }
    }

    @Override
    public int getItemCount() {
        return vinhos.size();
    }

    static class VinhoViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewInfo;

        public VinhoViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textViewInfo = itemView.findViewById(R.id.textViewInfoVinho);
        }
    }

    public void atualizarLista(List<VinhoModel> novaLista) {
        this.vinhos.clear();
        this.vinhos.addAll(novaLista);
        notifyDataSetChanged();
    }

}

