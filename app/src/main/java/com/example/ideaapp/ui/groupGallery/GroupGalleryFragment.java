package com.example.ideaapp.ui.groupGallery;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ideaapp.MiniAdapter;
import com.example.ideaapp.R;
import com.example.ideaapp.model.Appuser;
import com.example.ideaapp.model.IdeaGroup;
import com.example.ideaapp.ui.home.HomeFragment;
import com.example.ideaapp.ui.ideagroup.IdeaGroupExp;
import com.example.ideaapp.ui.ideagroup.PlaceholderFragment;
import com.example.ideaapp.ws.InfrastructureWebservice;
import com.example.ideaapp.ws.NoSuchRowException;

import java.util.ArrayList;
import java.util.Collection;

public class GroupGalleryFragment extends Fragment {

    private GroupGalleryViewModel galleryViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutRv;
    private ArrayList<IdeaGroup> content;
    private MiniAdapter adapter;
    private InfrastructureWebservice service;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GroupGalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_group_gallery, container, false);
        //final TextView textView = root.findViewById(R.id.textGallery);
        recyclerView = root.findViewById(R.id.rec_view);
        layoutRv = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutRv);

        content = new ArrayList<IdeaGroup>();
        adapter = new MiniAdapter(content);
        recyclerView.setAdapter(adapter);
        service = new InfrastructureWebservice();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        return root;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<IdeaGroup> g = (ArrayList<IdeaGroup>) service.getGroupsByUserid(11402);

        if (g != null) {
            content.addAll(g);
            adapter = new MiniAdapter(content);
            recyclerView.setAdapter(adapter);
        }
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this.getContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        System.out.println("Test" + content.get(position).getGroupname());
                        //FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                        Intent intent = new Intent(getActivity(), IdeaGroupExp.class);
                        startActivity(intent);
                        intent.putExtra("Gruppe", content.get(position));
                        System.out.println(content.get(position));

                        //ft.replace(R.id.nav_host_fragment, new HomeFragment());
                        //ft.commit();

                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                }));

    }
}