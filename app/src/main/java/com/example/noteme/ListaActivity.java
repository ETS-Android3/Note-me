package com.example.noteme;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.noteme.databinding.ActivityListaBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListaActivity extends AppCompatActivity {

    Intent baseIntent;
    String topic;

    ActivityListaBinding binding;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // Hide the action bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);

        binding = ActivityListaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Load the data
        db = FirebaseFirestore.getInstance();
        ArrayList<Subtopic> subtopicArrayList = new ArrayList<>();

        db.collection("scores")
                .orderBy("score", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot doc: task.getResult()) {
                                subtopicArrayList.add(new Subtopic(doc.getString("email").split("@")[0] + "  [" + Integer.toString(doc.getLong("score").intValue()) + "]"));
                            }
                            SubtopicListAdapter tla = new SubtopicListAdapter(ListaActivity.this, subtopicArrayList);
                            binding.subtopicListView.setAdapter(tla);
                        }
                    }
                });

        Button btn = findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}