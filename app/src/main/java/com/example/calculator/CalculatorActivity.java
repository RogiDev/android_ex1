package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {

    int num1,num2;
    char operator;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        textView = findViewById(R.id.textView);
        textView.setText("");
    }

    public void getNum(View view) {
        Button b1 = (Button) view;
        textView.append(b1.getText());
    }

    public void getOperator(View view) {
        String result = textView.getText().toString();
        num1 = Integer.parseInt(result);
        operator = ((Button) view).getText().toString().charAt(0);
        textView.setText("");
    }

    public void clearText(View view){
        textView.setText("");
    }

    public void equalAll(View view){
        String result = textView.getText().toString();
        num2 = Integer.parseInt(result);
        switch( operator ) {
            case '+':
                textView.setText((num1 + num2) + "");
                break;
            case '-':
                textView.setText((num1 - num2) + "");
                break;
            case '*':
                textView.setText((num1 * num2) + "");
                break;
            case '/':
                textView.setText((num1 / num2) + "");
                break;
        }
    }
}