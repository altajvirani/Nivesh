package com.phtlearning.nivesh.Authentication.Login;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.phtlearning.nivesh.Authentication.Option.LoginOption.LoginOption;
import com.phtlearning.nivesh.Home.FounderHomeActivity;
import com.phtlearning.nivesh.Home.InvestorHomeActivity;


public class LoginWithEmailPassword extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
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
        firebaseAuth = FirebaseAuth.getInstance();
        Query query = FirebaseDatabase.getInstance().getReference().child(UserType).orderByChild("email").equalTo(UserEmail);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getChildrenCount()>0)
                {
                    firebaseAuth.signInWithEmailAndPassword(UserEmail, UserPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                if(firebaseAuth.getCurrentUser().isEmailVerified())
                                {
                                    if(UserType.equals("Founder"))
                                    {
                                        Intent intent = new Intent(LoginWithEmailPassword.this, FounderHomeActivity.class);
                                        startActivity(intent);
                                        intent.putExtra("UserType", UserType);
                                        finishAffinity();
                                        finish();
                                        progressDialog.hide();
                                    }
                                    else if(UserType.equals("Investor"))
                                    {
                                        Intent intent = new Intent(LoginWithEmailPassword.this, InvestorHomeActivity.class);
                                        startActivity(intent);
                                        intent.putExtra("UserType", UserType);
                                        finishAffinity();
                                        finish();
                                        progressDialog.hide();
                                    }

                                    Toast.makeText(LoginWithEmailPassword.this, "Login as "+UserType, Toast.LENGTH_SHORT).show();

                                }
                                else
                                {
                                    firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful())
                                            {
                                                Intent intent = new Intent(LoginWithEmailPassword.this, LoginActivity.class);
                                                intent.putExtra("UserType", UserType);
                                                startActivity(intent);
                                                finishAffinity();
                                                finish();
                                                progressDialog.hide();
                                                Toast.makeText(LoginWithEmailPassword.this, "Please Check Your Mail For Email Verification", Toast.LENGTH_SHORT).show();
                                            }
                                            else
                                            {
                                                progressDialog.hide();
                                                Toast.makeText(LoginWithEmailPassword.this, "Something Went Wrong, Please Try Again", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });



                                }
                            }
                            else
                            {
                                Intent intent = new Intent(LoginWithEmailPassword.this, LoginActivity.class);
                                intent.putExtra("UserType", UserType);
                                startActivity(intent);
                                finishAffinity();
                                finish();
                                progressDialog.hide();
                                Toast.makeText(LoginWithEmailPassword.this, "Wrong Email ID or Password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else
                {

                    Intent intent = new Intent(LoginWithEmailPassword.this, LoginActivity.class);
                    intent.putExtra("UserType", UserType);
                    startActivity(intent);
                    finishAffinity();
                    finish();
                    progressDialog.hide();
                    Toast.makeText(LoginWithEmailPassword.this, "Please Create Your "+ UserType +" Account First", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Intent intent = new Intent(LoginWithEmailPassword.this, LoginActivity.class);
                intent.putExtra("UserType", UserType);
                startActivity(intent);
                finishAffinity();
                finish();
                progressDialog.hide();
                Toast.makeText(LoginWithEmailPassword.this, "Please Check Your Internet Connectivity", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(LoginWithEmailPassword.this, LoginOption.class));
        finishAffinity();
        finish();

    }
}