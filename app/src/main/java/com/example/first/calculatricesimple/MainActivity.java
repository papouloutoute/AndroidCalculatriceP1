package com.example.first.calculatricesimple;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private Operator currentOperator;
    private Stack<Double> numbers;

    private enum Operator {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
        numbers = new Stack<>();

        setNumberListeners();
        setOperatorListeners();
        setEqualListener();
        setClearListener();
        setNegateListener();
    }

    private void setNumberListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                display.append(button.getText().toString());
            }
        };

        findViewById(R.id.btn0).setOnClickListener(listener);
        findViewById(R.id.btn1).setOnClickListener(listener);
        findViewById(R.id.btn2).setOnClickListener(listener);
        findViewById(R.id.btn3).setOnClickListener(listener);
        findViewById(R.id.btn4).setOnClickListener(listener);
        findViewById(R.id.btn5).setOnClickListener(listener);
        findViewById(R.id.btn6).setOnClickListener(listener);
        findViewById(R.id.btn7).setOnClickListener(listener);
        findViewById(R.id.btn8).setOnClickListener(listener);
        findViewById(R.id.btn9).setOnClickListener(listener);
    }

    private void setOperatorListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                double currentNumber = Double.valueOf(display.getText().toString());
                if (!numbers.isEmpty()) {
                    Operator previousOperator = currentOperator;
                    if (previousOperator != null) {
                        switch (button.getText().toString()) {
                            case "+":
                            case "-":
                                if (previousOperator == Operator.MULTIPLY || previousOperator == Operator.DIVIDE) {
                                    evaluateAndDisplay();
                                }
                                break;
                            case "×":
                            case "÷":
                                if (previousOperator == Operator.ADD || previousOperator == Operator.SUBTRACT) {
                                    evaluateAndDisplay();
                                }
                                break;
                        }
                    }
                }
                switch (button.getText().toString()) {
                    case "+":
                        currentOperator = Operator.ADD;
                        break;
                    case "-":
                        currentOperator = Operator.SUBTRACT;
                        break;
                    case "×":
                        currentOperator = Operator.MULTIPLY;
                        break;
                    case "÷":
                        currentOperator = Operator.DIVIDE;
                        break;
                }
                numbers.push(currentNumber);
                display.setText("");
            }
        };

        findViewById(R.id.btnPlus).setOnClickListener(listener);
        findViewById(R.id.btnMoin).setOnClickListener(listener);
        findViewById(R.id.btnFois).setOnClickListener(listener);
        findViewById(R.id.btnDiv).setOnClickListener(listener);
    }


    private void evaluateAndDisplay() {
        double result = 0;
        double secondOperand = Double.valueOf(display.getText().toString());
        double firstOperand = numbers.pop();

        switch (currentOperator) {
            case ADD:
                result = firstOperand + secondOperand;
                break;
            case SUBTRACT:
                result = firstOperand - secondOperand;
                break;
            case MULTIPLY:
                result = firstOperand * secondOperand;
                break;
            case DIVIDE:
                if (secondOperand == 0) {
                    display.setText("Error");
                    return;
                }
                result = firstOperand / secondOperand;
                break;
        }
        display.setText(String.valueOf(result));
    }
    private void setEqualListener() {
        findViewById(R.id.btnEgal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double result = 0;
                double secondOperand = Double.valueOf(display.getText().toString());
                double firstOperand = numbers.pop();

                switch (currentOperator) {
                    case ADD:
                        result = firstOperand + secondOperand;
                        break;
                    case SUBTRACT:
                        result = firstOperand - secondOperand;
                        break;
                    case MULTIPLY:
                        result = firstOperand * secondOperand;
                        break;
                    case DIVIDE:
                        if (secondOperand == 0) {
                            display.setText("Error");
                            return;
                        }
                        result = firstOperand / secondOperand;
                        break;
                }
                display.setText(String.valueOf(result));
            }
        });
    }


    private void setClearListener() {
        findViewById(R.id.btnReset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText("");
                numbers.clear();
                currentOperator = null;
            }
        });
    }

    private void setNegateListener() {
        findViewById(R.id.btnNegatif).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = display.getText().toString();
                if (!currentText.isEmpty()) {
                    try {
                        double value = Double.valueOf(currentText);
                        value = -value;
                        display.setText(String.valueOf(value));
                    } catch (NumberFormatException e) {
                        display.setText("Error");
                    }
                }
            }
        });
    }

}
