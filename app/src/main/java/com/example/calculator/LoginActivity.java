package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    final String TAG = "LoginActivity";
    String rememberMail, rememberPass;
    SharedPreferences sharedPreferences;
    EditText emailInput , passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        Button loginBtn = findViewById(R.id.loginBtn);
         emailInput = findViewById(R.id.loginEmail);
         passwordInput = findViewById(R.id.loginPassword);
        sharedPreferences = getSharedPreferences("sharePref", MODE_PRIVATE);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(emailInput.getText().toString(),passwordInput.getText().toString());
            }
        });
        Button registerBtn = findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        rememberMail = sharedPreferences.getString("EMAIL", "");
        rememberPass = sharedPreferences.getString("PASSWORD", "");

        emailInput.setText(rememberMail);
        passwordInput.setText(rememberPass);

        if ( sharedPreferences.getString("EMAIL" , null ) != null){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            String KEY = uid;
            Toast.makeText(LoginActivity.this, "I remember you: " + KEY + " :)", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(LoginActivity.this, CalculatorActivity.class);
            //putExtra("str","I remember you: "+KEY+" :)");
            startActivity(intent);

        }

    }


    private void login(String email,String password){
        rememberMail = email;
        rememberPass = password;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("EMAIL", rememberMail);
        editor.putString("PASSWORD", rememberPass);
        editor.apply();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LoginActivity.this, "Authentication success.",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this,CalculatorActivity.class));

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }

}