package com.phtlearning.nivesh.Authentication.Option.LoginOption;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.phtlearning.nivesh.Authentication.Login.LoginActivity;
import com.phtlearning.nivesh.Authentication.Option.SignUpOption.SignupOption;
import com.phtlearning.nivesh.R;

public class LoginOption extends AppCompatActivity {
    Button LoginAsFounder, LoginAsInvestor;
    TextView SignUpPageRedirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_option);

        ProgressDialog progressDialog =  new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please Wait");

        LoginAsFounder =(Button) findViewById(R.id.login_as_founder);
        LoginAsInvestor =(Button) findViewById(R.id.login_as_investor);
        SignUpPageRedirect =(TextView) findViewById(R.id.signup_text_for_redirect);

        LoginAsFounder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                Intent intent = new Intent(LoginOption.this, LoginActivity.class);
                intent.putExtra("UserType","Founder");
                startActivity(intent);
                finish();
                progressDialog.hide();
            }
        });

        LoginAsInvestor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                Intent intent = new Intent(LoginOption.this, LoginActivity.class);
                intent.putExtra("UserType","Investor");
                startActivity(intent);
                finish();
                progressDialog.hide();
            }
        });

        SignUpPageRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                Intent intent = new Intent(LoginOption.this, SignupOption.class);
                startActivity(intent);
                finish();
                progressDialog.hide();
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finishAffinity();
        finish();

    }
}