package com.example.de_cell;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<Linear> mData;
    private LayoutInflater mInflater;
    private Context context;


    public ListAdapter(List<Linear> itemList, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_elemnt, null);
        return new ListAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView remitente;
        TextView destinatario;
        TextView saldo;

        ViewHolder(View itemView) {
            super(itemView);

            remitente = itemView.findViewById(R.id.remitente);
            destinatario = itemView.findViewById(R.id.destinatario);
            saldo = itemView.findViewById(R.id.saldo);

        }

        void bindData(final Linear item) {
            remitente.setText(item.getRemitente());
            destinatario.setText(item.getDestinatario());
            saldo.setText(item.getSaldo());


        }

    }


}
