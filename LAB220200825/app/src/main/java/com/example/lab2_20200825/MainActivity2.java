package com.example.lab2_20200825;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_main2);

            // Configuración de WindowInsetsListener
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });

            // Configura el botón de la flecha (refreshIcon1) para que vuelva a MainActivity
            ImageButton refreshIcon1 = findViewById(R.id.refreshIcon1);
            refreshIcon1.setOnClickListener(v -> {
                // Intent para volver a MainActivity
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            });

            // Configura el botón de calcular (button3) para que vaya a MainActivity3
            Button calculateButton = findViewById(R.id.button3);
            calculateButton.setOnClickListener(v -> {
                // Intent para ir a MainActivity3
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);
            });
        }
    }

