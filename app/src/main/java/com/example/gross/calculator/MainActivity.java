package com.example.gross.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private TextView screenTV;
    private String display = "", currentOperator = "", result = "";
    private boolean numberHasPoint = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screenTV = (TextView) findViewById(R.id.textView);

    }

    private void updateScreen() {

        screenTV.setText(display);

    }

    private void clear() {

        display = "";
        currentOperator = "";
        result = "";
        numberHasPoint = false;

    }

    public void onClickNumber(View v) {

        if (!result.equals("")) {

            clear();
            updateScreen();

        }

        Button clickedNumber = (Button) v;
        display += clickedNumber.getText();
        updateScreen();

    }

    private boolean isOperator(char op) {

        switch (op) {
            case '+':
            case '-':
            case 'x':
            case '/':
                return true;
            default:
                return false;

        }
    }

    public void onClickOperator(View v) {

        numberHasPoint = false;

        if (display.equals("")) return;

        Button clickedOperator = (Button) v;

        if (!result.equals("")) {

            String _display = result;
            clear();
            display = _display;

        }

        if (!currentOperator.equals("")) {

            if (isOperator(display.charAt(display.length() - 1))) {

                display = display.replace(display.charAt(display.length() - 1), clickedOperator.getText().charAt(0));
                updateScreen();
                return;

            } else {

                getResult();
                display = result;
                result = "";

            }

            currentOperator = clickedOperator.getText().toString();
        }

        display += clickedOperator.getText();
        currentOperator = clickedOperator.getText().toString();
        updateScreen();
    }

    public void onClickClear(View v) {

        clear();
        updateScreen();

    }

    private double operate(String a, String b, String op) {

        switch (op) {

            case "+":
                return Double.valueOf(a) + Double.valueOf(b);
            case "-":
                return Double.valueOf(a) - Double.valueOf(b);
            case "x":
                return Double.valueOf(a) * Double.valueOf(b);
            case "/":
                try {
                    return Double.valueOf(a) / Double.valueOf(b);
                } catch (Exception e) {
                    Log.d("Calc", e.getMessage());
                }

            default:
                return -1;
        }
    }

    private boolean getResult() {

        if (currentOperator.equals("")) return false;

        String[] operation = display.split(Pattern.quote(currentOperator));

        if (operation.length < 2) return false;

        result = String.valueOf(operate(operation[0], operation[1], currentOperator));
        return true;

    }

    public void onClickEqual(View v) {

        if (display.equals("")) return;

        if (!getResult()) return;

        screenTV.setText(display + "\n" + String.valueOf(result));

    }

    public void onClickPoint(View view) {

        if (!numberHasPoint) {

            display += ".";
            numberHasPoint = true;
            updateScreen();

        }
    }
}
