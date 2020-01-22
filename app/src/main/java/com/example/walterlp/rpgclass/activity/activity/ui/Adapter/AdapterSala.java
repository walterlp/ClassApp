package com.example.walterlp.rpgclass.activity.activity.ui.Adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.walterlp.rpgclass.R;
import com.example.walterlp.rpgclass.model.Turma;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterSala extends RecyclerView.Adapter<AdapterSala.MyViewHolder> {

    private List<Turma> turmas;
    public AdapterSala(List<Turma> turmas){
        this.turmas = turmas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_sala,parent,false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Turma turma = turmas.get(position);
        holder.nomesala.setText(turma.getNome());
        holder.descsala.setText(turma.getDescricao());
        holder.codsala.setText(turma.getCodigo());
        //carregando imagem da sala/turma/guilda tantofaz.........
        String urlImagem = turma.getUrlImagem();
        Picasso.get().load( urlImagem ).into(holder.imagemSala);

    }

    @Override
    public int getItemCount() {
        return turmas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imagemSala;
        TextView nomesala;
        TextView descsala;
        TextView codsala;
        public MyViewHolder(View itemView) {
            super(itemView);
            nomesala = itemView.findViewById(R.id.textNomeSala);
            descsala = itemView.findViewById(R.id.textDescricaoSala);
            codsala = itemView.findViewById(R.id.textSalaCodigo);
            imagemSala =itemView.findViewById(R.id.imageTurma);
        }
    }
}
