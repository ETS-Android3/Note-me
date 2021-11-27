package com.example.noteme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class TestsActivity extends AppCompatActivity {

    Intent bintent;
    Button btnSiguiente;
    FirebaseFirestore db;
    String tema, subtema;
    TextView lblTema, lblPregunta, lblResp1, lblResp2, lblResp3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests);
        btnSiguiente = findViewById(R.id.btnSiguiente);

        lblPregunta = findViewById(R.id.lblPregunta);
        lblResp1 = findViewById(R.id.lblRespuesta1);
        lblResp2 = findViewById(R.id.lblRespuesta2);
        lblResp3 = findViewById(R.id.lblRespuesta3);

        lblTema = findViewById(R.id.lblTema);

        bintent = getIntent();
        tema = bintent.getStringExtra("tema");
        subtema = bintent.getStringExtra("subtema");
        Log.d("Tema", "onCreate: " + tema);
        Log.d("SubTema", "onCreate: " + subtema);
        lblTema.setText(subtema);

        db = FirebaseFirestore.getInstance();
        db.collection("preguntas")
                .whereEqualTo("tema", tema)
                .whereEqualTo("subtema", subtema)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot doc: task.getResult()) {
                                lblPregunta.setText(doc.getString("pregunta"));
                                lblResp1.setText(doc.getString("respuesta"));
                                lblResp2.setText(doc.getString("relleno1"));
                                lblResp3.setText(doc.getString("relleno2"));
                            }
                        }
                    }
                });
    }
}