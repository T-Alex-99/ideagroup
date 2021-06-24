package com.example.ideaapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MiniAdapter extends RecyclerView.Adapter<MiniAdapter.FirstViewHolder>{
    private ArrayList<com.example.ideaapp.model.IdeaGroup> content;

    public class FirstViewHolder extends RecyclerView.ViewHolder {
        // "Hält" die Views
        // Textview aus score_list_item
        private TextView textview;

        public FirstViewHolder(View v) {
            super(v);
            textview = v.findViewById(R.id.groupname);
        }

        public void bind(final com.example.ideaapp.model.IdeaGroup group) {
            textview.setText(group.toString());
        }
    }

    public MiniAdapter(ArrayList<com.example.ideaapp.model.IdeaGroup> content){
        this.content = content;
    }

    public FirstViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        // Holt die Darstellung
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.listitem,parent,false);

        FirstViewHolder viewHolder = new FirstViewHolder(v);
        return viewHolder;
    }

    public void onBindViewHolder(FirstViewHolder holder,int position) {
        final com.example.ideaapp.model.IdeaGroup s = content.get(position);
        holder.bind(s);
    }

    public int getItemCount() {
        return content.size();
    }
}
