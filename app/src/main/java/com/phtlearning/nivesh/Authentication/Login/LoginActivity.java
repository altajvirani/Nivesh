package com.phtlearning.nivesh.Authentication.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.phtlearning.nivesh.Authentication.ForgotPassword.ForgotPasswordActivity;
import com.phtlearning.nivesh.Authentication.Option.LoginOption.LoginOption;
import com.phtlearning.nivesh.Authentication.SignUp.SignUpActivity;
import com.phtlearning.nivesh.R;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    EditText UserEmail, UserPassword;
    Button ContinueButton;
    ImageButton GoogleButton, FacebookButton;
    AwesomeValidation awesomeValidation;
    TextView SignUpPageRedirect, ForgotPassword;


    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])"+       // 1 number
                    "(?=.*[a-z])"+         // 1 lower case
                    "(?=.*[A-Z])"+          // at least 1 uper case
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{8,}" +                // at least 8 characters
                    "$");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent intent = getIntent();
        String UserType = intent.getStringExtra("UserType");

        ProgressDialog progressDialog =  new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please Wait");

        UserEmail = (EditText)findViewById(R.id.email_edt_xml);
        UserPassword = (EditText)findViewById((R.id.password_edt_xml));
        ContinueButton = (Button) findViewById(R.id.send_email_btn_xml_for_login);
        GoogleButton = (ImageButton) findViewById(R.id.google_btn_xml);
        FacebookButton = (ImageButton) findViewById(R.id.facebook_btn_xml);
        SignUpPageRedirect =(TextView) findViewById(R.id.signup_text_for_redirect);
        ForgotPassword =(TextView) findViewById(R.id.forgotPassword);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.email_edt_xml, Patterns.EMAIL_ADDRESS,R.string.Invalid_Email_Message);
        awesomeValidation.addValidation(this,R.id.password_edt_xml,PASSWORD_PATTERN,R.string.Invalid_Confirm_Password_Message);

        ContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(awesomeValidation.validate())
                {
                    progressDialog.show();
                    String UserEmailText = UserEmail.getText().toString();
                    String UserPasswordText = UserPassword.getText().toString();
                    Intent intent = new Intent(LoginActivity.this, LoginWithEmailPassword.class);
                    intent.putExtra("EMAIL", UserEmailText);
                    intent.putExtra("PASSWORD", UserPasswordText);
                    intent.putExtra("UserType", UserType);
                    startActivity(intent);
                    progressDialog.hide();
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Please Enter Valid Details", Toast.LENGTH_SHORT).show();
                }
            }
        });

        SignUpPageRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                intent.putExtra("UserType", UserType);
                startActivity(intent);
                finishAffinity();
                finish();
                progressDialog.hide();
            }
        });

        ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                intent.putExtra("UserType", UserType);
                startActivity(intent);
                finishAffinity();
                finish();
                progressDialog.hide();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(LoginActivity.this, LoginOption.class));
        finishAffinity();
        finish();

    }
}