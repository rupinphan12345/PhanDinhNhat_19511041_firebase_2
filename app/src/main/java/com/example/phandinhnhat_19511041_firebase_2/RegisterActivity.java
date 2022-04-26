package com.example.phandinhnhat_19511041_firebase_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText edtEmail, edtPassword;
    Button btnRegister, btnSignIn;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        mapping();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

    }

    private void login() {
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(RegisterActivity.this, "Vui lòng điền day đủ thông tin", Toast.LENGTH_SHORT);
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Regist Successed", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, SignInActivity.class));
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Try again!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void mapping () {
        edtEmail = findViewById(R.id.edtEmail_RegisterScreen);
        edtPassword = findViewById(R.id.edtPassword_RegisterScreen);
        btnRegister = findViewById(R.id.btnRegister_RegisterScreen);
    }
}