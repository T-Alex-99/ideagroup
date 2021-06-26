package com.example.ideaapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MiniAdapter extends RecyclerView.Adapter<MiniAdapter.FirstViewHolder>{
    private ArrayList<com.example.ideaapp.model.IdeaGroup> content;
    private View.OnClickListener mOnClickListener = null;

    public class FirstViewHolder extends RecyclerView.ViewHolder {
        // "Hält" die Views
        // Textview aus score_list_item
        private TextView groupname;
        private TextView created;
        private TextView description;
        private TextView owner;


        public FirstViewHolder(View v) {
            super(v);
            //mOnClickListener = new ClickGroupListener((AppCompatActivity) v.getContext());
            groupname = v.findViewById(R.id.groupname);
            created = v.findViewById(R.id.created);
            description = v.findViewById(R.id.description);
            owner = v.findViewById(R.id.owner);
        }

        public void bind(final com.example.ideaapp.model.IdeaGroup group) {
            groupname.setText(group.getGroupname());
            description.setText(group.getGroupdescr());
            created.setText(group.getCreateLocalDate());
            owner.setText(group.getGroupowner().getUsername());
        }
    }

    public MiniAdapter(ArrayList<com.example.ideaapp.model.IdeaGroup> content){
        this.content = content;
    }

    public FirstViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        // Holt die Darstellung
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.listitem,parent,false);
        //v.setOnClickListener(mOnClickListener);
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
