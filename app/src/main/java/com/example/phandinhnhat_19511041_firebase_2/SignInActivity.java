package com.example.phandinhnhat_19511041_firebase_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransitionImpl;

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

public class SignInActivity extends AppCompatActivity {

    EditText edtUser, edtPassword;
    Button btnSignIn, btnRegister;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        firebaseAuth = FirebaseAuth.getInstance();

        mapping();
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this, RegisterActivity.class));
            }
        });

    }

    private void login() {
        String user = edtUser.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        if(TextUtils.isEmpty(user) || TextUtils.isEmpty(password)){
            Toast.makeText(SignInActivity.this, "Vui lòng điền day đủ thông tin", Toast.LENGTH_SHORT);
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(user, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SignInActivity.this, "Successed", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignInActivity.this, FaceActivity.class));
                }
                else{
                    Toast.makeText(SignInActivity.this, "Sai user hoac password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void mapping() {
        edtUser = findViewById(R.id.edtEmail_SignInScreen);
        edtPassword = findViewById(R.id.edtPassword_SignInScreen);
        btnSignIn= findViewById(R.id.btnSignIn_SignInScreen);
        btnRegister = findViewById(R.id.btnRegister_SignInScreen);
    }
}