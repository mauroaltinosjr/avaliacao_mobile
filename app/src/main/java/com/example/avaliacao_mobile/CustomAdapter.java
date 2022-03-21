package com.example.avaliacao_mobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList imovelId, imovelFinalidade, imovelTipo, imovelValor, imovelCep, imovelEndereco;

    CustomAdapter(Context context,
                  ArrayList imovelId,
                  ArrayList imovelFinalidade,
                  ArrayList imovelTipo,
                  ArrayList imovelValor,
                  ArrayList imovelCep,
                  ArrayList imovelEndereco){
        this.context = context;
        this.imovelId = imovelId;
        this.imovelFinalidade = imovelFinalidade;
        this.imovelTipo = imovelTipo;
        this.imovelValor = imovelValor;
        this.imovelCep = imovelCep;
        this.imovelEndereco = imovelEndereco;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_imovelId.setText(String.valueOf(imovelId.get(position)));
        holder.tv_imovelFinalidade.setText(String.valueOf(imovelFinalidade.get(position)));
        holder.tv_imovelTipo.setText(String.valueOf(imovelTipo.get(position)));
        holder.tv_imovelValor.setText(String.valueOf(imovelValor.get(position)));
        holder.tv_imovelCep.setText(String.valueOf(imovelCep.get(position)));
        holder.tv_imovelEndereco.setText(String.valueOf(imovelEndereco.get(position)));
    }

    @Override
    public int getItemCount() {
        return imovelId.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_imovelId, tv_imovelFinalidade, tv_imovelTipo, tv_imovelValor, tv_imovelCep, tv_imovelEndereco;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_imovelId = itemView.findViewById(R.id.tv_imovelId);
            tv_imovelFinalidade = itemView.findViewById(R.id.tv_imovelFinalidade);
            tv_imovelTipo = itemView.findViewById(R.id.tv_imovelTipo);
            tv_imovelValor = itemView.findViewById(R.id.tv_imovelValor);
            tv_imovelCep = itemView.findViewById(R.id.tv_imovelCep);
            tv_imovelEndereco = itemView.findViewById(R.id.tv_imovelEndereco);

        }
    }
}
