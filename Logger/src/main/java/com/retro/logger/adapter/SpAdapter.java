package com.retro.logger.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.retro.logger.R;
import com.retro.logger.model.ExceptionModel;
import com.retro.logger.model.SpModel;
import com.retro.logger.utils.CrashInterface;

import java.util.ArrayList;
import java.util.List;

public class SpAdapter extends RecyclerView.Adapter<SpAdapter.SpViewHolder> {

    Context context;
    List<SpModel> spModelList = new ArrayList<>();
    CrashInterface crashInterface;

    public SpAdapter(List<SpModel> spModels) {
        this.spModelList = spModels;
    }

    @NonNull
    @Override
    public SpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sp, parent, false);
        return new SpViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpViewHolder holder, int position) {

        holder.tvSequence.setText(String.valueOf(position+1));
        holder.key.setText(spModelList.get(position).getKey());
        holder.value.setText(spModelList.get(position).getValue());

    }

    @Override
    public int getItemCount() {
        return spModelList.size();
    }

    class SpViewHolder extends RecyclerView.ViewHolder {
        TextView tvSequence;
        TextView key, value;


        public SpViewHolder(@NonNull View itemView) {
            super(itemView);
            key = itemView.findViewById(R.id.tvSpKey);
            tvSequence = itemView.findViewById(R.id.tvSpSequence);
            value = itemView.findViewById(R.id.tvSpValue);
        }
    }


    public void setData(List<SpModel> list){
        spModelList = list;
        notifyDataSetChanged();
    }
}
