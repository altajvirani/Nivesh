package com.phtlearning.nivesh.SplashScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.phtlearning.nivesh.Authentication.Login.LoginActivity;
import com.phtlearning.nivesh.Authentication.Option.LoginOption.LoginOption;
import com.phtlearning.nivesh.Founder.Fragments.RaiseFund.StartDateFragment;
import com.phtlearning.nivesh.Home.FounderHomeActivity;
import com.phtlearning.nivesh.Home.InvestorHomeActivity;
import com.phtlearning.nivesh.R;

public class SplashScreen extends AppCompatActivity {

    ProgressBar progressBar;

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("UserType");

        progressBar.setVisibility(View.VISIBLE);

//        Thread background = new Thread() {
//            public void run() {
//                try {
//                    // Thread will sleep for 5 seconds
//                    sleep(1*1000);
//
//                    // After 5 seconds redirect to another intent
//                    Intent i=new Intent(getBaseContext(), InvestorHomeActivity.class);
//                    startActivity(i);
//
//                    //Remove activity
//                    finish();
//                } catch (Exception e) {
//                }
//            }
//        };
//        // start thread
//        background.start();

        if(firebaseAuth.getCurrentUser() == null)
        {
            startActivity(new Intent(SplashScreen.this,LoginOption.class));
        }
        else
        {
            String CurrentUserUid = firebaseAuth.getCurrentUser().getUid();
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String UserType = snapshot.child(CurrentUserUid).child("userType").getValue().toString();
                    if(UserType.equals("Investor"))
                    {
                        if(firebaseAuth.getCurrentUser().isEmailVerified())
                        {
                            startActivity(new Intent(SplashScreen.this, InvestorHomeActivity.class));
                            finish();
                        }
                        else
                        {
                            Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                            intent.putExtra("UserType",UserType);
                            startActivity(intent);
                            finish();
                        }
                    }
                    else if (UserType.equals("Founder"))
                    {
                        if(firebaseAuth.getCurrentUser().isEmailVerified())
                        {
                            startActivity(new Intent(SplashScreen.this, FounderHomeActivity.class));
                            finish();
                        }
                        else
                        {
                            Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                            intent.putExtra("UserType",UserType);
                            startActivity(intent);
                            finish();
                        }
                    }
                    else
                    {
                        startActivity(new Intent(SplashScreen.this, LoginOption.class));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(SplashScreen.this, "Please Check Your Internet Connectivity", Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
}