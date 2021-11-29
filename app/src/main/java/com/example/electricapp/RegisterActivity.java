package com.example.electricapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private Button btnClear;
    private Button btnRegister;
    private EditText etConfirmPassword;
    private EditText etEmail;
    private EditText etPassword;
    private TextView tvAlreadyHaveAccount;
    private ProgressBar registerProgress;

    private final String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+" +
            "/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        btnClear = findViewById(R.id.btnClear);
        btnRegister = findViewById(R.id.btnRegister);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        tvAlreadyHaveAccount = findViewById(R.id.tvAlreadyHaveAccount);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        registerProgress = findViewById(R.id.registerProgress);
        registerProgress.setVisibility(View.GONE);

        tvAlreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerProgress.setVisibility(View.VISIBLE);
                PerformAuth();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerProgress.setVisibility(View.GONE);
                etEmail.getText().clear();
                etPassword.getText().clear();
                etConfirmPassword.getText().clear();
            }
        });
    }

    private void PerformAuth() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();

        if (!isValid(email)) {
            etEmail.setError("Enter Correct Email!!");
        } else if (password.isEmpty() || password.length() < 6) {
            etPassword.setError("Enter Proper Password!!");
        } else if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError("Confirmed Password Does NOT Match!");
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                    new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        registerProgress.setVisibility(View.GONE);
                        sendUserToNextActivity();
                        Toast.makeText(RegisterActivity.this, "Registration Successful",
                                Toast.LENGTH_SHORT).show();
                        Log.d("RegisterActivity", "createUserWithEmail: Success");
                    } else {
                        registerProgress.setVisibility(View.GONE);
                        Toast.makeText(RegisterActivity.this, "" + task.getException(),
                                Toast.LENGTH_SHORT).show();
                        Log.d("RegisterActivity", "createUserWithEmail: Failure",
                                task.getException());
                    }
                }
            });
        }
    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(RegisterActivity.this, UtilityDashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private boolean isValid(String email) {
        return email.matches(regex);
    }
}