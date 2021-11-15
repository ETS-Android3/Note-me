package com.example.noteme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.noteme.databinding.ActivityHomeBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class TemaActivity extends AppCompatActivity {

    TextView txtTopic;
    Intent baseIntent;
    String topic;

    ActivityHomeBinding binding;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        baseIntent = getIntent();
        topic = baseIntent.getStringExtra("tema");

        Log.d("->", "Before find");
        txtTopic = findViewById(R.id.txtTema);
        Log.d("->", "Before set " + topic + " to " + txtTopic.toString());
        txtTopic.setText(topic);
        Log.d("->", "After set");

        // Load the data
        ArrayList<Subtopic> subtopicArrayList = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        db.collection("subtemas")
                .whereEqualTo("tema", topic)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot doc: task.getResult()) {
                                subtopicArrayList.add(new Subtopic(doc.get("nombre").toString()));
                            }

                            SubtopicListAdapter tla = new SubtopicListAdapter(TemaActivity.this, subtopicArrayList);
                            binding.topicListView.setAdapter(tla);
                            binding.topicListView.setClickable(true);
                            binding.topicListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                                    Topic t = (Topic) adapterView.getItemAtPosition(i);
//                                    Intent intent = new Intent(TemaActivity.this, TemaActivity.class);
//                                    intent.putExtra("tema", t.name);
//                                    startActivity(intent);
                                }
                            });
                        }
                    }
                });
    }
}