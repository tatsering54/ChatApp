package com.example.taz.chatapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterActivity extends AppCompatActivity {
    private Button GetStartedButon;
    private EditText DNEditText;
    private EditText EMEidtText;
    private EditText PWEidtText;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;
    private ProgressDialog mProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mProgress = new ProgressDialog(this);
        DNEditText = (EditText) findViewById(R.id.DisplayNameEditText);
        EMEidtText = (EditText) findViewById(R.id.EmailEditText);
        PWEidtText = (EditText) findViewById(R.id.PassWordEditText);
        GetStartedButon = (Button) findViewById(R.id.GetStartedButton);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
             if(firebaseAuth.getCurrentUser()== null){

             }
            }
        };

        GetStartedButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startRegister();
            }
        });
    }

    private void startRegister() {
        final String name = DNEditText.getText().toString().trim();
        String email = EMEidtText.getText().toString().trim();
        String password = PWEidtText.getText().toString().trim();

        if(!TextUtils.isEmpty(name)&& !TextUtils.isEmpty(email)&& !TextUtils.isEmpty(password)){

            mProgress.setMessage("Signing Up");
            mProgress.show();
            String user_id = mAuth.getCurrentUser().getUid();
            DatabaseReference current_user_db = mDatabase.child(user_id);
            current_user_db.child("user name").setValue(name);
            Intent mainIntent = new Intent(RegisterActivity.this,SignInActivity.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mainIntent);

        }


    }
}
