package com.example.ideaapp.ui.groupGallery;

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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ideaapp.MiniAdapter;
import com.example.ideaapp.R;
import com.example.ideaapp.model.Appuser;
import com.example.ideaapp.model.IdeaGroup;
import com.example.ideaapp.ws.InfrastructureWebservice;
import com.example.ideaapp.ws.NoSuchRowException;

import java.util.ArrayList;
import java.util.Collection;

public class GroupGalleryFragment extends Fragment {

    private GroupGalleryViewModel galleryViewModel;
    private TextView testText;
    private Button testRest;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutRv;
    private ArrayList<IdeaGroup> content;
    private MiniAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GroupGalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_group_gallery, container, false);
        final TextView textView = root.findViewById(R.id.textGallery);


        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        return root;
    }



    // Test aus android stuido
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        testText = view.findViewById(R.id.testText);
        testRest = view.findViewById(R.id.testButton);

        recyclerView = view.findViewById(R.id.rec_view);
        layoutRv = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutRv);

        content = new ArrayList<IdeaGroup>();
        adapter = new MiniAdapter(content);
        //content.add(new IdeaGroup("test","test"));
        recyclerView.setAdapter(adapter);


        testRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfrastructureWebservice service = null;
                String s = "";
                long id;
                String result;
                Appuser appuser = null;

                        Log.println(1,"d","JETZT IN CASE DRIN");
                        id = 1;
                        service = new InfrastructureWebservice();

                //Appuser user = service.getUser(242);
                //System.out.println("jjbfojbwfbwefbjobf" + user.toString());
                //System.out.println(user == null);
                ArrayList<IdeaGroup> g = (ArrayList<IdeaGroup>) service.getGroupsByUserid(11402);
                //IdeaGroup g = service.getGroupByName("Updategruppe4");
                //System.out.println(g == null);
                if (g != null) {
                    //System.out.println("jjbfojbwfbwefbjobf" + user.toString());
                    testText.setText(g.toString() + "booo");

                    //testText.setText(g.toString());
                    //content.add(new IdeaGroup("test", "test"));
                    //content.add(g);
                    content.addAll(g);
                    adapter = new MiniAdapter(content);
                    recyclerView.setAdapter(adapter);
                }

            }
        });
    }
}