package com.example.taz.chatapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

    private EditText emailText;
    private EditText passwdText;
    private Button logInButton;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        emailText = (EditText) findViewById(R.id.emailText);
        passwdText = (EditText) findViewById(R.id.passText);
        logInButton = (Button) findViewById(R.id.SignInButton);
        mAuth = FirebaseAuth.getInstance();



        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignIn(); //calls the signIn method from below method

            }
        });
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() != null) {
                    // Intent user account
                    Intent mainIntent = new Intent(SignInActivity.this,MainActivity.class);
                    startActivity(mainIntent);

                }

            }
        };

    }
    @Override
    protected void onStart() {
        // assign the auth listner to mauth
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }

    private void startSignIn(){

        String email = emailText.getText().toString();
        String passwd = passwdText.getText().toString();
        if(TextUtils.isEmpty(email)|| TextUtils.isEmpty(passwd)){

            Toast.makeText(SignInActivity.this,"Fields are empty.",Toast.LENGTH_LONG).show();


        }else {
            mAuth.signInWithEmailAndPassword(email,passwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(!task.isSuccessful()){
                        Toast.makeText(SignInActivity.this,"Sign In Problem",Toast.LENGTH_LONG).show();
                    }

                }
            });
        }


    }

}
