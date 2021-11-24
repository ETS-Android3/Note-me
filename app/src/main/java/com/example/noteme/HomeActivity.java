package com.example.noteme;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.noteme.databinding.ActivityHomeBinding;
import com.example.noteme.ui.ContentActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        ArrayList<Topic> topicArrayList = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        db.collection("temas")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot doc: task.getResult()) {
                                topicArrayList.add(new Topic(doc.get("nombre").toString()));
                            }

                            TopicListAdapter tla = new TopicListAdapter(HomeActivity.this, topicArrayList);
                            binding.topicListView.setAdapter(tla);
                            binding.topicListView.setClickable(true);
                            binding.topicListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    Topic t = (Topic) adapterView.getItemAtPosition(i);
                                    Intent intent = new Intent(HomeActivity.this, TemaActivity.class);
                                    intent.putExtra("tema", t.name);
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                });
    }
}