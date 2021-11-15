package com.example.noteme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TemaActivity extends AppCompatActivity {
    TextView txtTema;
    Intent baseIntent;
    String tema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tema);

        baseIntent = getIntent();
        tema = baseIntent.getStringExtra("tema");

        txtTema = findViewById(R.id.tema);

        txtTema.setText(tema);
    }
}