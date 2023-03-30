package com.example.de_cell;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.time.temporal.Temporal;
import java.util.List;

public class shimpentsAdapter extends RecyclerView.Adapter<shimpentsAdapter.ViewHolder> {
    private List<shimpmentsElements> datos;
    private LayoutInflater minflater;
    private Context context;
    final shimpentsAdapter.OnItemClickListener lista;


    public interface OnItemClickListener {
        void onItemClick(shimpmentsElements item);

    }

    public shimpentsAdapter(List<shimpmentsElements> ItemList, Context context, shimpentsAdapter.OnItemClickListener lista) {
        this.minflater = LayoutInflater.from(context);
        this.context = context;
        this.datos = ItemList;
        this.lista = lista;
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    @Override
    public shimpentsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = minflater.inflate(R.layout.layautshipments, null);
        return new shimpentsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final shimpentsAdapter.ViewHolder holder, final int posicion) {
        holder.bindData(datos.get(posicion));

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconshipment;
        TextView planes;
        TextView cargos;

        ViewHolder(View itemView) {
            super(itemView);
            iconshipment = itemView.findViewById(R.id.iconshipments);
            planes = itemView.findViewById(R.id.plan);
            cargos = itemView.findViewById(R.id.cargo);
        }


        void bindData(final shimpmentsElements item) {
            iconshipment.setColorFilter(Color.parseColor(item.getColors()), PorterDuff.Mode.SRC_IN);
            planes.setText(item.getPlan());
            cargos.setText(item.getCargo());
            itemView.setOnClickListener(v -> lista.onItemClick(item));
        }

    }
}
