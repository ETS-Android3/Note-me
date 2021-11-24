package com.example.noteme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class InfoTemaActivity extends AppCompatActivity {

    Intent bintent;
    String tema;
    String subtema;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_tema);

        bintent = getIntent();
        tema = bintent.getStringExtra("tema");
        subtema = bintent.getStringExtra("subtema");

        db = FirebaseFirestore.getInstance();
        db.collection("informacion")
                .whereEqualTo("tema", tema)
                .whereEqualTo("subtema", subtema)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot doc: task.getResult()) {
                                String a = "dato";
                                Log.d("dato", doc.getData().toString());
                                if (doc.contains(a)) {
                                    String informacion = doc.getString(a);
                                    ((TextView) findViewById(R.id.tituloSeccion)).setText(subtema);
                                    ((TextView) findViewById(R.id.contenidoSeccion)).setText(informacion);
                                }
                            }
                        }
                    }
                });

    }
}