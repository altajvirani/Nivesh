package com.phtlearning.nivesh.Authentication.SignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.phtlearning.nivesh.Authentication.Login.LoginActivity;
import com.phtlearning.nivesh.Authentication.Option.SignUpOption.SignupOption;
import com.phtlearning.nivesh.Authentication.SignUp.DataBaseHelper.UserSignUpDetailsHelper;
import com.phtlearning.nivesh.Authentication.SignUp.DataBaseHelper.UserTypeHelper;


public class SignUpWithEmailPassword extends AppCompatActivity {
    DatabaseReference databaseReference, UserTypeReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String UserEmail = intent.getStringExtra("EMAIL");
        String UserPassword = intent.getStringExtra("PASSWORD");
        String UserType = intent.getStringExtra("UserType");

        ProgressDialog progressDialog =  new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please Wait");

        progressDialog.show();
        databaseReference = FirebaseDatabase.getInstance().getReference().child(UserType);
        UserTypeReference = FirebaseDatabase.getInstance().getReference().child("UserType");
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.createUserWithEmailAndPassword(UserEmail, UserPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String CurrentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    UserSignUpDetailsHelper userSignUpDetailsHelper = new UserSignUpDetailsHelper(UserEmail);
                    databaseReference.child(CurrentUser).setValue(userSignUpDetailsHelper);
                    UserTypeHelper userTypeHelper = new UserTypeHelper(UserType);
                    UserTypeReference.child(CurrentUser).setValue(userTypeHelper);
                    Intent intent = new Intent(SignUpWithEmailPassword.this, LoginActivity.class);
                    intent.putExtra("UserType",UserType);
                    startActivity(intent);
                    finishAffinity();
                    finish();
                    progressDialog.hide();
                    Toast.makeText(SignUpWithEmailPassword.this, "Your "+UserType+" Created Successfully! ", Toast.LENGTH_SHORT).show();

                }
                else {
                    Intent intent = new Intent(SignUpWithEmailPassword.this, SignUpActivity.class);
                    intent.putExtra("UserType",UserType);
                    startActivity(intent);
                    finishAffinity();
                    finish();
                    progressDialog.hide();
                    Toast.makeText(SignUpWithEmailPassword.this, "This Email ID Used By Someone Else", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(SignUpWithEmailPassword.this, SignupOption.class));
        finishAffinity();
        finish();

    }
}