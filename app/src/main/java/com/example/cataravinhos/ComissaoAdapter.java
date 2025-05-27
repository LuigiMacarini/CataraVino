package com.example.cataravinhos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cataravinhos.model.ComissaoModel;

import java.util.List;

public class ComissaoAdapter extends RecyclerView.Adapter<ComissaoAdapter.ViewHolder> {

    private final List<ComissaoModel> comissoes;

    public ComissaoAdapter(List<ComissaoModel> comissoes) {
        this.comissoes = comissoes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ComissaoModel c = comissoes.get(position);
        holder.title.setText("Pedido: " + c.getIdPedido() + " | R$ " + c.getValor());
        holder.subtitle.setText("Status: " + c.getStatusPagamento() + " | Prevista: " + c.getDataPrevista());
    }

    @Override
    public int getItemCount() {
        return comissoes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, subtitle;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(android.R.id.text1);
            subtitle = itemView.findViewById(android.R.id.text2);
        }
    }
}
