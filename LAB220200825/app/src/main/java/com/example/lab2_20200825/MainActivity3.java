package com.example.lab2_20200825;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {

    private TextView sectionA, sectionB;
    private int firstNumber = 0, secondNumber = 0;
    private String currentOp = "";
    private ArrayList<String> history = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        sectionA = findViewById(R.id.sectionA);
        sectionB = findViewById(R.id.sectionB);

        clearCalculator();

        setupCalculatorButtons();

        ImageButton refreshIcon1 = findViewById(R.id.refreshIcon1);
        refreshIcon1.setOnClickListener(v -> startActivity(new Intent(MainActivity3.this, MainActivity.class)));
        ImageButton historyButton = findViewById(R.id.refreshIcon2);
        historyButton.setOnClickListener(v -> goToHistoryActivity());

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
            firstNumber = 0;
            sectionA.setText("0 " + op);
        } else {
            sectionA.setText(firstNumber + " " + op);
        }
        sectionB.setText("0");
    }

    private void clearSectionB() {
        secondNumber = 0;
        sectionB.setText("0");
    }

    private void clearCalculator() {
        firstNumber = 0;
        secondNumber = 0;
        currentOp = "";
        sectionA.setText("0");
        sectionB.setText("0");
    }

    private void calculateResult() {
        int result = 0;
        boolean error = false;
        String resultEntry;

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
                    return;
                }
                break;
        }

        if (!error) {
            String resultEntry5 = "Resultado " + (history.size() + 1) + ": " + result;
            history.add(resultEntry5);
            sectionB.setText(String.valueOf(result));
            firstNumber = 0;
            secondNumber = 0;
            currentOp = "";
            sectionA.setText("0");
        }
    }
    private void goToHistoryActivity() {
        Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
        intent.putStringArrayListExtra("history", history);
        startActivity(intent);
    }
}
