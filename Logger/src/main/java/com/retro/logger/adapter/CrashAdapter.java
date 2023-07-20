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
import com.retro.logger.model.LogModel;
import com.retro.logger.utils.CrashInterface;

import java.util.ArrayList;
import java.util.List;

public class CrashAdapter extends RecyclerView.Adapter<CrashAdapter.CrashViewHolder> {

    Context context;
    List<ExceptionModel> logModelList = new ArrayList<>();
    CrashInterface crashInterface;

    public CrashAdapter(List<ExceptionModel> logModelList, CrashInterface crashInterface) {
        this.logModelList = logModelList;
        this.crashInterface =crashInterface;
    }

    @NonNull
    @Override
    public CrashViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_crash, parent, false);
        return new CrashViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CrashViewHolder holder, int position) {

        holder.tvSequence.setText(String.valueOf(position+1));
        holder.title.setText(logModelList.get(position).getSTACKTRACE());
        holder.timing.setText(logModelList.get(position).getEXCEPTION_TIME());

        holder.clCrashParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crashInterface.onLogClick(logModelList.get(position));
            }
        });
        //holder.tvMethod.setText("["+logModelList.get(position).getCALLMETHOD()+"]");
    }

    @Override
    public int getItemCount() {
        return logModelList.size();
    }

    class CrashViewHolder extends RecyclerView.ViewHolder {
        TextView tvSequence;
        TextView title, timing;
        ConstraintLayout clCrashParent;


        public CrashViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvCrash);
            tvSequence = itemView.findViewById(R.id.tvCrashSequence);
            timing = itemView.findViewById(R.id.tvCrashTiming);
            clCrashParent = itemView.findViewById(R.id.clCrashParent);
        }
    }


    public void setData(List<ExceptionModel> list){
        logModelList = list;
        notifyDataSetChanged();
    }
}
