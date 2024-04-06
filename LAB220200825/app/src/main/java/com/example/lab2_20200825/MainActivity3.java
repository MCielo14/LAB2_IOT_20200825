package com.example.lab2_20200825;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    private TextView sectionA, sectionB;
    private int firstNumber = 0, secondNumber = 0;
    private String currentOp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        sectionA = findViewById(R.id.sectionA);
        sectionB = findViewById(R.id.sectionB);

        // Inicializa las cajas A y B con "0"
        clearCalculator();

        setupCalculatorButtons();

        ImageButton refreshIcon1 = findViewById(R.id.refreshIcon1);
        refreshIcon1.setOnClickListener(v -> startActivity(new Intent(MainActivity3.this, MainActivity.class)));

        ImageButton refreshIcon2 = findViewById(R.id.refreshIcon2);
        refreshIcon2.setOnClickListener(v -> startActivity(new Intent(MainActivity3.this, MainActivity4.class)));
    }

    private void setupCalculatorButtons() {
        int[] numberButtonIds = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
                R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9
        };
        for (int id : numberButtonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(this::onNumberButtonClick);
        }

        findViewById(R.id.buttonAdd).setOnClickListener(v -> onOperationButtonClick("+"));
        findViewById(R.id.buttonSub).setOnClickListener(v -> onOperationButtonClick("-"));
        findViewById(R.id.buttonMul).setOnClickListener(v -> onOperationButtonClick("*"));
        findViewById(R.id.buttonDiv).setOnClickListener(v -> onOperationButtonClick("/"));
        findViewById(R.id.buttonClr).setOnClickListener(v -> clearSectionB());
        findViewById(R.id.buttonEquals).setOnClickListener(v -> calculateResult());
    }

    private void onNumberButtonClick(View v) {
        Button button = (Button) v;
        int number = Integer.parseInt(button.getText().toString());
        if (currentOp.isEmpty()) {
            firstNumber = firstNumber * 10 + number;
            sectionA.setText(String.valueOf(firstNumber));
        } else {
            secondNumber = secondNumber * 10 + number;
            sectionB.setText(String.valueOf(secondNumber));
        }
    }

    private void onOperationButtonClick(String op) {
        currentOp = op;
        if (firstNumber == 0 && sectionB.getText().toString().equals("0")) {
            // Esto maneja un caso en el que el usuario elige una operación sin un primer número.
            firstNumber = 0;
            sectionA.setText("0 " + op);
        } else {
            sectionA.setText(firstNumber + " " + op);
        }
        sectionB.setText("0"); // Prepara la sección B para el segundo número
    }

    private void clearSectionB() {
        secondNumber = 0;
        sectionB.setText("0"); // Borra solo la sección B y deja la operación actual intacta
    }

    private void clearCalculator() {
        // Restablece todo a 0
        firstNumber = 0;
        secondNumber = 0;
        currentOp = "";
        sectionA.setText("0"); // Reinicia la sección A
        sectionB.setText("0"); // Reinicia la sección B
    }

    private void calculateResult() {
        int result = 0;
        boolean error = false;
        switch (currentOp) {
            case "+": result = firstNumber + secondNumber; break;
            case "-": result = firstNumber - secondNumber; break;
            case "*": result = firstNumber * secondNumber; break;
            case "/":
                if (secondNumber != 0) {
                    result = firstNumber / secondNumber;
                } else {
                    error = true;
                    sectionB.setText("Error");
                }
                break;
        }
        if (!error) {
            sectionB.setText(String.valueOf(result)); // Muestra el resultado en la sección B
            firstNumber = 0; // Prepara firstNumber para la nueva operación
            secondNumber = 0; // Reinicia secondNumber
            currentOp = ""; // Limpia la operación actual
            sectionA.setText("0"); // Asegúrate de que la caja A se reinicia a "0"
        }
    }
}
