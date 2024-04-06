package com.example.lab2_20200825;
import com.example.lab2_20200825.R;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView titleTeleMath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Encuentra el TextView por su ID y regístralo para el Context Menu
        titleTeleMath = findViewById(R.id.textView3); // Asegúrate de que este ID sea el correcto en tu archivo layout
        registerForContextMenu(titleTeleMath);

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

        // Configuración de la vista para que responda a los WindowInsets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Método que crea el Context Menu al hacer un long press sobre el TextView
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu); // Asegúrate de tener un archivo 'context_menu.xml' en res/menu.
    }

    // Método que maneja las selecciones de elementos del Context Menu

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // Asegúrate de que los ID aquí coincidan con los IDs en tu archivo context_menu.xml
        switch (item.getItemId()) {
            case R.id.menu_blue:
                titleTeleMath.setTextColor(Color.BLUE);
                return true;
            case R.id.menu_green:
                titleTeleMath.setTextColor(Color.GREEN);
                return true;
            case R.id.menu_red:
                titleTeleMath.setTextColor(Color.RED);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

}
