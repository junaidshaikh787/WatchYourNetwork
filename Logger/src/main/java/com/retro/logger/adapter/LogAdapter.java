package com.retro.logger.adapter;

import android.content.Context;
import android.security.AppUriAuthenticationPolicy;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.retro.logger.R;
import com.retro.logger.model.LogModel;
import com.retro.logger.utils.LogInterface;

import java.util.ArrayList;
import java.util.List;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.LogViewHolder> {

    Context context;
    List<LogModel> logModelList = new ArrayList<>();
    LogInterface logInterface;

    public LogAdapter(List<LogModel> logModelList, LogInterface logInterface) {
        this.logModelList = logModelList;
        this.logInterface=logInterface;
    }

    @NonNull
    @Override
    public LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_log, parent, false);
        return new LogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LogViewHolder holder, int position) {

        holder.tvStatus.setText(logModelList.get(position).getSTATUS());
        holder.tvMethod.setText("["+logModelList.get(position).getCALLMETHOD()+"]");
        holder.tvUrl.setText("["+logModelList.get(position).getURL()+"]");
        holder.clLogParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logInterface.onLogClick(logModelList.get(position));
            }
        });
        //holder.tvMethod.setText("["+logModelList.get(position).getCALLMETHOD()+"]");
    }

    @Override
    public int getItemCount() {
        return logModelList.size();
    }

    class LogViewHolder extends RecyclerView.ViewHolder {
        TextView tvMethod;
        TextView tvTiming;
        TextView tvUrl;
        TextView tvStatus;
        ConstraintLayout clLogParent;

        public LogViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMethod = itemView.findViewById(R.id.tvMethod);
            tvTiming = itemView.findViewById(R.id.tvTiming);
            tvUrl = itemView.findViewById(R.id.tvUrl);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            clLogParent = itemView.findViewById(R.id.clLogParent);
        }
    }
}
