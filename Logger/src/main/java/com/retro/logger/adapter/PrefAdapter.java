package com.retro.logger.adapter;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.retro.logger.R;

public class PrefAdapter extends RecyclerView.Adapter<PrefAdapter.PrefAdapterViewholder> {
    String[] list;
    Context context;

    public PrefAdapter(String[] list) {
        this.list = list;
    }

    @NonNull
    @Override
    public PrefAdapterViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pref, parent, false);
        return new PrefAdapterViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PrefAdapterViewholder holder, int position) {
        holder.tvFileName.setText(list[position]);
    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    class PrefAdapterViewholder extends RecyclerView.ViewHolder{
        TextView tvFileName;
        public PrefAdapterViewholder(@NonNull View itemView) {
            super(itemView);
            tvFileName=itemView.findViewById(R.id.tvFileName);
        }
    }
}
