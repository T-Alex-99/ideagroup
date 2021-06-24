package com.example.ideaapp.ui.groupGallery;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.ideaapp.R;
import com.example.ideaapp.model.Appuser;
import com.example.ideaapp.ws.InfrastructureWebservice;
import com.example.ideaapp.ws.NoSuchRowException;

public class GroupGalleryFragment extends Fragment {

    private GroupGalleryViewModel galleryViewModel;
    private TextView testText;
    private Button testRest;

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




    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        testText = view.findViewById(R.id.testText);
        testRest = view.findViewById(R.id.testButton);

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
                        try {
                            Appuser user = service.getUser(2);
                            if (user != null)
                                testText.setText(user.toString() + "booo");
                        } catch (NoSuchRowException e) {
                            testText.setText("Kein Raum gefunden!");
                        }

            }
        });
    }
}