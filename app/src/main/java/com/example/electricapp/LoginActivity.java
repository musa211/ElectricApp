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

public class LoginActivity extends AppCompatActivity {

    private Button btnClear;
    private Button btnLogin;
    private EditText etEmail;
    private EditText etPassword;
    private TextView tvCreateNewAccount;
    private ProgressBar loginProgress;

    private final String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+" +
            "/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        btnClear = findViewById(R.id.btnClearLogin);
        btnLogin = findViewById(R.id.btnLogin);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        tvCreateNewAccount = findViewById(R.id.tvCreateNewAccount);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        loginProgress = findViewById(R.id.loginProgress);
        loginProgress.setVisibility(View.GONE);

        tvCreateNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginProgress.setVisibility(View.VISIBLE);
                performLogin();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginProgress.setVisibility(View.GONE);
                etEmail.getText().clear();
                etPassword.getText().clear();
            }
        });
    }

    private void performLogin() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (!isValid(email)) {
            etEmail.setError("Enter Correct Email!!");
        } else if (password.isEmpty() || password.length() < 6) {
            etPassword.setError("Enter Proper Password!!");
        }  else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(
                    new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        loginProgress.setVisibility(View.GONE);
                        sendUserToNextActivity();
                        Toast.makeText(LoginActivity.this, "Login Successful",
                                Toast.LENGTH_SHORT).show();
                        Log.d("LoginActivity", "loginUserWithEmail: Success");
                    } else {
                        loginProgress.setVisibility(View.GONE);
                        Toast.makeText(LoginActivity.this, "" + task.getException(),
                                Toast.LENGTH_SHORT).show();
                        Log.d("LoginActivity", "loginUserWithEmail: Failure",
                                task.getException());
                    }
                }
            });
        }
    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(LoginActivity.this, UtilityDashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private boolean isValid(String email) {
        return email.matches(regex);
    }
}