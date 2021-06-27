package com.example.ideaapp.ui.newCategories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ideaapp.R;
import com.example.ideaapp.model.Appuser;
import com.example.ideaapp.model.GroupMember;
import com.example.ideaapp.model.IdeaCategory;
import com.example.ideaapp.model.IdeaGroup;
import com.example.ideaapp.ui.newIdeaGroup.CreateIdeaAdapter;
import com.example.ideaapp.ui.newIdeaGroup.NewIdeaGroup;
import com.example.ideaapp.ws.IllegalCreateException;
import com.example.ideaapp.ws.InfrastructureWebservice;
import com.example.ideaapp.ws.NoSuchRowException;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.ArrayList;

public class NewCategories extends AppCompatActivity {

    private RecyclerView rv;
    private ArrayList<String> content;
    private CreateCategories adapter;
    private EditText ed;
    private RecyclerView.LayoutManager layoutRV;

    private Button saveIdea;

    private InfrastructureWebservice service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_categories);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        //Rest
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        service = new InfrastructureWebservice();

        rv = findViewById(R.id.rec_category);
        ed = findViewById(R.id.inputCategory);
        layoutRV = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutRV);

        content = new ArrayList<String>();
        adapter = new CreateCategories(content);
        rv.setAdapter(adapter);

            ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT ) {


                @Override
                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                    Toast.makeText(NewCategories.this, "on Move", Toast.LENGTH_SHORT).show();
                    return false;
                }

                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                    Toast.makeText(NewCategories.this, "on Swiped ", Toast.LENGTH_SHORT).show();
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
                        InputMethodManager imm = (InputMethodManager)NewCategories.this.getBaseContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(ed.getWindowToken(), 0);
                        ed.clearFocus();
                        adapter.notifyDataSetChanged();
                        return false;
                    } else {
                        return false;
                    }
                }
            });

        Intent intent = getIntent();
        IdeaGroup tempIdeaGroup = (IdeaGroup) intent.getSerializableExtra("ideagroup");
        ArrayList<String> emails = (ArrayList<String>) intent.getSerializableExtra("emails");
        Log.v("IDEAGROUP", "INTENT" + tempIdeaGroup);
        Log.v("IDEAGROUP", "INTENT" + emails);
        Log.v("IDEAGROUP", "CONTENT SIZEW" + content.size());


        saveIdea = findViewById(R.id.saveIdea);
        saveIdea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Log.v("FORSCHLEIFE", "JETZTZ SCHLEIFE");
                /*
                for (String category : content) {
                    IdeaCategory tempCategory = new IdeaCategory(category);
                    tempIdeaGroup.addIdeaCategory(tempCategory);
                    Log.v("FORSCHLEIFE", "111");
                }

                Log.v("FORSCHLEIFE", "NACH SCHLEIFE");
                /*
                for (String category : tempMembers) {
                    GroupMember tempMember = new GroupMember(googleIdeaGroup,o);
                    groupMemberInterface.save(tempMember);
                }
                 */

                Log.v("IDEAGROUP", "CONTENT SIZEW" + content.size());
                try {
                    service.createIdeaGroup(tempIdeaGroup, acct.getEmail());
                    IdeaGroup tempIdeaGroup2 = service.getGroupByName(tempIdeaGroup.getGroupname());
                    service.createGroupMember(tempIdeaGroup2.getGroupname(),acct.getEmail());
                    for (String email : emails) {

                        Appuser tempAppuser = service.getUserByEmail(email);

                        service.createGroupMember(tempIdeaGroup2.getGroupname(),tempAppuser.getUseremail());
                    }
                    for (String category : content) {
                        Log.v("IDEAGROUP", "CONTENT SIZEW" + content);

                        service.createIdeaCategory(tempIdeaGroup2.getGroupname(),category);
                        Log.v("FORSCHLEIFECATEGORY", "FORSCHLEIFECATEGORY");
                    }
                } catch (IllegalCreateException | NoSuchRowException e) {
                    e.printStackTrace();
                }

            }
        });




    }
}