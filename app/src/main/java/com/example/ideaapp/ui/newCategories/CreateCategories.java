package com.example.ideaapp.ui.newCategories;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ideaapp.R;

import java.util.ArrayList;

public class CreateCategories extends RecyclerView.Adapter<CreateCategories.FirstViewHolder> {
    private ArrayList<String> content;
    private String selectedItem;
    private int row_index;

    // Daten werden von der Activity hineingereicht
    public CreateCategories(ArrayList<String> content) {
        this.content = content;
    }


    @NonNull
    @Override
    public FirstViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Datendarstellung holen
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.category_item,parent,false);
        FirstViewHolder viewHolder = new FirstViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FirstViewHolder holder, int position) {
        final String s = content.get(position);
        holder.textView.setText(s);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity act = (AppCompatActivity)view.getContext();
                row_index = position;

                selectedItem = s;
            }
        });




    }
    @Override
    public int getItemCount() {
        return content.size();
    }

    public class FirstViewHolder extends RecyclerView.ViewHolder {
        private View layout;
        private TextView textView;

        public FirstViewHolder(View v) {
            super(v);
            layout = v;
            textView = v.findViewById(R.id.ideagroup_category);
        }

        public void add(int position, String item) {
            content.add(position,item);
            notifyItemInserted(position);
        }

        public void remove(int position, String item) {
            content.remove(position);
            notifyItemRemoved(position);
        }



    }

    public int getRow_index() {
        return row_index;
    }

    public String getSelectedItem() {
        return selectedItem;
    }
}

