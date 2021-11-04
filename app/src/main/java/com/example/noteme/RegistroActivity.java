package com.example.noteme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistroActivity extends AppCompatActivity {
    EditText Correo, Psd;
    Button btnRegistro, btnToLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Correo = findViewById(R.id.txtEmailRegistro);
        Psd = findViewById(R.id.txtPsdRegistro);

        btnRegistro = findViewById(R.id.btnRegistro);
        btnToLogIn = findViewById(R.id.btnToLogIn);

        btnToLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegistroActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}