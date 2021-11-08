package com.example.noteme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TemaActivity extends AppCompatActivity {
    TextView txtBtn1, txtBtn2, txtBtn3,txtBtn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tema);

        txtBtn1 = findViewById(R.id.txtBtn1);
        txtBtn2 = findViewById(R.id.txtBtn2);
        txtBtn3 = findViewById(R.id.txtBtn3);
        txtBtn4 = findViewById(R.id.txtBtn4);

        txtBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TemaActivity.this, InfoTemaActivity.class);
                startActivity(i);
            }
        });
        txtBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TemaActivity.this, InfoTemaActivity.class);
                startActivity(i);
            }
        });
        txtBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TemaActivity.this, InfoTemaActivity.class);
                startActivity(i);
            }
        });
        txtBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TemaActivity.this, InfoTemaActivity.class);
                startActivity(i);
            }
        });
    }
}