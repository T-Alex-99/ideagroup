package com.example.ideaapp.ui.newIdeaGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ideaapp.MainActivity;
import com.example.ideaapp.R;
import com.example.ideaapp.model.IdeaGroup;
import com.example.ideaapp.ui.newCategories.NewCategories;
import com.example.ideaapp.ws.IllegalCreateException;
import com.example.ideaapp.ws.InfrastructureWebservice;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.ArrayList;

public class NewIdeaGroup extends AppCompatActivity {

    private RecyclerView rv;
    private ArrayList<String> content;
    private CreateIdeaAdapter adapter;
    private EditText ed;
    private EditText ideaName;
    private EditText ideaDescription;
    private RecyclerView.LayoutManager layoutRV;
    private Button addCategories;
    private InfrastructureWebservice service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_idea_group);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        //Rest
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        service = new InfrastructureWebservice();

        rv = findViewById(R.id.rec_email);
        ed = findViewById(R.id.inputEmail);
        layoutRV = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutRV);

        content = new ArrayList<String>();
        adapter = new CreateIdeaAdapter(content);
        rv.setAdapter(adapter);

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT ) {


            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                Toast.makeText(NewIdeaGroup.this, "on Move", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                Toast.makeText(NewIdeaGroup.this, "on Swiped ", Toast.LENGTH_SHORT).show();
                //Remove swiped item from list and notify the RecyclerView
                int position = viewHolder.getAdapterPosition();
                content.remove(position);
                adapter.notifyItemRemoved(position);
                adapter.notifyDataSetChanged();

            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(rv);

        ed.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    content.add(ed.getText().toString());
                    ed.setText("");
                    InputMethodManager imm = (InputMethodManager)NewIdeaGroup.this.getBaseContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(ed.getWindowToken(), 0);
                    ed.clearFocus();
                    adapter.notifyDataSetChanged();
                    return false;
                } else {
                    return false;
                }
            }
        });

        ideaName = findViewById(R.id.idea_name);
        ideaDescription = findViewById(R.id.idea_description);

        ideaDescription.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (source != null) {
                    String s = source.toString();
                    if (s.contains("\n")) {
                        // Hide soft keyboard
                        InputMethodManager imm = (InputMethodManager)NewIdeaGroup.this.getBaseContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(ideaDescription.getWindowToken(), 0);
                        // Lose focus
                        ideaDescription.clearFocus();
                        // Remove all newlines
                        return s.replaceAll("\n", "");
                    }
                }
                return null;
            }
        }});

        addCategories = findViewById(R.id.add_categories);
        addCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /**DUPLICATE IDEAGROUP PROOF IMPLEMENTIEREN */
                //try {
                    IdeaGroup tempIdeaGroup = new IdeaGroup(ideaName.getText().toString(),ideaDescription.getText().toString());
                    //service.createIdeaGroup(tempIdeaGroup,acct.getEmail().toString());
                    Intent intent = new Intent(NewIdeaGroup.this, NewCategories.class);
                    intent.putExtra("ideagroup", tempIdeaGroup);
                    intent.putExtra("emails", content);
                    startActivity(intent);
                //} catch (IllegalCreateException e) {
                 //   e.printStackTrace();
                //}

            }
        });




    }
}