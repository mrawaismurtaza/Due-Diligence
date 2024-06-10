package com.example.due_diligence;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class AdapterHomeProjects extends RecyclerView.Adapter<AdapterHomeProjects.MyViewHolder> {

    List<Projects> projects;
    Context context;
    private OnItemClickListener mListener;

    public AdapterHomeProjects(Context context, List<Projects> projects) {
        this.context = context;
        this.projects = projects;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.projects_card, parent, false);
        return new MyViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Projects project = projects.get(position);
        holder.textView.setText(project.getName());
        // holder.imageView.setImageResource(project.getImage());
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            textView = itemView.findViewById(R.id.projectname);
            imageView = itemView.findViewById(R.id.projectimage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
