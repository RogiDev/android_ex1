package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CalculatorActivity extends AppCompatActivity {

    int num1,num2;
    char operator;
    TextView textView;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        textView = findViewById(R.id.textView);
        textView.setText("");
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("person").child(uid);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Person value = dataSnapshot.getValue(Person.class);
                Toast.makeText(CalculatorActivity.this, "the user id is: " + value,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
            }
        });
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