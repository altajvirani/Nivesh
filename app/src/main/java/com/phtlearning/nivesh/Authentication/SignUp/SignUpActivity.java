package com.phtlearning.nivesh.Authentication.SignUp;

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
import com.phtlearning.nivesh.Authentication.Login.LoginActivity;
import com.phtlearning.nivesh.Authentication.Option.SignUpOption.SignupOption;
import com.phtlearning.nivesh.R;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    EditText UserEmail, UserPassword, UserRePassword;
    Button ContinueButton;
    TextView LoginPageRedirect;
    ImageButton GoogleButton, FacebookButton;
    AwesomeValidation awesomeValidation;

    //making password pattern using pattern class
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^"+
                    "(?=.*[0-9])"+           // at least 1 number
                    "(?=.*[a-z])"+           // at least one lower alphabet
                    "(?=.*[A-Z])"+           // at least one upper alphabet
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{8,}" +                // at least 8 characters
                    "$"
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        // getting refrence from activity_founder_sign_up.xml file
        UserEmail = (EditText)findViewById(R.id.email_edt_xml);
        UserPassword = (EditText)findViewById((R.id.password_edt_xml));
        UserRePassword = (EditText)findViewById(R.id.re_password_edt_xml);
        ContinueButton = (Button) findViewById(R.id.continue_btn_xml);
        GoogleButton = (ImageButton) findViewById(R.id.google_btn_xml);
        FacebookButton = (ImageButton) findViewById(R.id.facebook_btn_xml);
        LoginPageRedirect =(TextView) findViewById(R.id.login_text_for_redirect);

        // getting Usertype
        Intent intent = getIntent();
        String UserType = intent.getStringExtra("UserType");

        ProgressDialog progressDialog =  new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please Wait");

        // Checking for valid inputs from users(Founders)
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.email_edt_xml, Patterns.EMAIL_ADDRESS,R.string.Invalid_Email_Message);
        awesomeValidation.addValidation(this,R.id.password_edt_xml,PASSWORD_PATTERN,R.string.Invalid_Password_Message);
        awesomeValidation.addValidation(this,R.id.re_password_edt_xml,R.id.password_edt_xml,R.string.Invalid_Confirm_Password_Message);

        ContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(awesomeValidation.validate())
                {
                    progressDialog.show();
                    String UserEmailText = UserEmail.getText().toString();
                    String UserRePasswordText = UserRePassword.getText().toString();
                    Intent intent = new Intent(SignUpActivity.this, SignUpWithEmailPassword.class);
                    intent.putExtra("EMAIL", UserEmailText);
                    intent.putExtra("PASSWORD", UserRePasswordText);
                    intent.putExtra("UserType",UserType);
                    startActivity(intent);
                    progressDialog.hide();

                }
                else{
                    Toast.makeText(SignUpActivity.this, "Please Enter Valid Details", Toast.LENGTH_SHORT).show();
                }

            }
        });
        GoogleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, ContinueWithGoogle.class);
                startActivity(intent);
                finish();
            }
        });

        FacebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, ContinueWithFacebook.class);
                startActivity(intent);
                finish();
            }
        });
        LoginPageRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                intent.putExtra("UserType",UserType);
                startActivity(intent);
                progressDialog.hide();
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(SignUpActivity.this, SignupOption.class));
        finishAffinity();
        finish();

    }
}