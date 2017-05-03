package com.example.gross.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView screenTV;
    private String display = "", currentOperator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screenTV = (TextView) findViewById(R.id.textView);
        screenTV.setText(display);
    }

    private void updateScreen(){
        screenTV.setText(display);
    }

    private void clear() {
        display = "";
        currentOperator = "";
    }

    public void onClickNumber(View v){
        Button clickedNumber = (Button) v;
        display += clickedNumber.getText();
        updateScreen();
    }

    public void onClickOperator(View v){
        Button clickedOperator = (Button) v;
        display += clickedOperator.getText();
        currentOperator = clickedOperator.getText().toString();
        updateScreen();
    }

    public void onClickClear(View v){
        clear();
        updateScreen();
    }
    public void onClickEqual(View v){

    }
}
