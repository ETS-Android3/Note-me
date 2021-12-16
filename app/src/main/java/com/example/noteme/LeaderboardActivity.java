package com.example.noteme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.noteme.databinding.ActivityLeaderboardBinding;
import com.example.noteme.databinding.ActivityTemaBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.core.OrderBy;

import java.util.ArrayList;

public class LeaderboardActivity extends AppCompatActivity {

    FirebaseFirestore db;
    ActivityLeaderboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        db = FirebaseFirestore.getInstance();
        ArrayList<Topic> userItemArrayList = new ArrayList<>();
        binding = ActivityLeaderboardBinding.inflate(getLayoutInflater());

        db.collection("scores")
                .orderBy("score", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot doc: task.getResult()) {
//                                Log.d("-->", doc.toString());
//                                // if (doc.contains("score") && doc.contains("email")) {
//                                // userItemArrayList.add(new UserItem(doc.getString("email"), doc.getLong("score").intValue()));
//                                userItemArrayList.add(new Topic(doc.getString("email")));
//                                // }
//                            }
//
//                            Log.d("==>", Integer.toString(userItemArrayList.size()));
//
//                            TopicListAdapter tla = new TopicListAdapter(LeaderboardActivity.this, userItemArrayList);
//                            binding.usersList.setAdapter(tla);
                            ArrayList<Subtopic> subtopicArrayList = new ArrayList<>();
                            subtopicArrayList.add(new Subtopic("doc.get(a).toString()"));

                            SubtopicListAdapter tla = new SubtopicListAdapter(LeaderboardActivity.this, subtopicArrayList);
                            binding.subtopicListView2.setAdapter(tla);
//                            binding..setClickable(true);
//                            binding..setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                @Override
//                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                                    Subtopic t = (Subtopic) adapterView.getItemAtPosition(i);
//                                    Intent intent = new Intent(TemaActivity.this, InfoTemaActivity.class);
//                                    intent.putExtra("tema", topic);
//                                    intent.putExtra("subtema", t.name);
//                                    startActivity(intent);
//                                }
//                            });
                        }
                    }
                });
    }
}