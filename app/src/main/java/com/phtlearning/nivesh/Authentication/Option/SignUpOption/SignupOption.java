package com.phtlearning.nivesh.Authentication.Option.SignUpOption;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.phtlearning.nivesh.Authentication.Login.LoginWithEmailPassword;
import com.phtlearning.nivesh.Authentication.Option.LoginOption.LoginOption;
import com.phtlearning.nivesh.Authentication.SignUp.SignUpActivity;
import com.phtlearning.nivesh.R;

public class SignupOption extends AppCompatActivity {
    Button SignUpAsFounder, SignUpAsInvestor;
    TextView LoginPageRedirect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_option);
        SignUpAsFounder =(Button) findViewById(R.id.signup_as_founder);
        SignUpAsInvestor =(Button) findViewById(R.id.sign_up_as_investor);
        LoginPageRedirect =(TextView) findViewById(R.id.login_text_for_redirect);

        SignUpAsFounder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupOption.this, SignUpActivity.class);
                intent.putExtra("UserType","Founder");
                startActivity(intent);
            }
        });

        SignUpAsInvestor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupOption.this, SignUpActivity.class);
                intent.putExtra("UserType","Investor");
                startActivity(intent);
            }
        });

        LoginPageRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupOption.this, LoginOption.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(SignupOption.this, LoginOption.class));
        finishAffinity();
        finish();

    }
}