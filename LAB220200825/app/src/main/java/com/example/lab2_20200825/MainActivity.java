package com.example.lab2_20200825;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonIndicaciones = findViewById(R.id.button);
        buttonIndicaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crea un intent para iniciar MainActivity2
                Intent intentIndicaciones = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intentIndicaciones);
            }
        });

        // Configurar el botón Calcular
        Button buttonCalcular = findViewById(R.id.button2);
        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crea un intent para iniciar MainActivity3
                Intent intentCalcular = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intentCalcular);
            }
        });
    }
}