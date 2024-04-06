package com.example.lab2_20200825;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity {

    private TextView historyTextView;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        // Configuración de la vista para que responda a los WindowInsets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Encuentra el TextView dentro de tu layout
        historyTextView = findViewById(R.id.textViewHistory);

        // Obtén el historial de la actividad anterior
        ArrayList<String> history = getIntent().getStringArrayListExtra("history");
        if (history != null) {
            // Muestra el historial en el TextView
            displayHistory(history);
        }

        // Configura el botón de la flecha para volver a MainActivity
        ImageButton refreshIcon1 = findViewById(R.id.refreshIcon1);
        refreshIcon1.setOnClickListener(v -> navigateToMainActivity());
    }

    private void displayHistory(ArrayList<String> history) {
        StringBuilder historyText = new StringBuilder();
        for (String entry : history) {
            historyText.append(entry).append("\n\n");
        }
        historyTextView.setText(historyText.toString());
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish(); // Finaliza esta actividad para que el usuario no pueda volver a ella con el botón Atrás
    }

}
