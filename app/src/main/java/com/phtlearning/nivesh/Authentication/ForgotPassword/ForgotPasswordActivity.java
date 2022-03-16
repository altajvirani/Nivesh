package com.phtlearning.nivesh.Authentication.ForgotPassword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.phtlearning.nivesh.Authentication.Login.LoginActivity;
import com.phtlearning.nivesh.Authentication.Login.LoginWithEmailPassword;
import com.phtlearning.nivesh.Authentication.Option.LoginOption.LoginOption;
import com.phtlearning.nivesh.Authentication.Option.SignUpOption.SignupOption;
import com.phtlearning.nivesh.Authentication.SignUp.SignUpActivity;
import com.phtlearning.nivesh.R;

public class ForgotPasswordActivity extends AppCompatActivity {
    EditText UserEmail;
    Button SendEmailButton;
    TextView RedirectToSignUpPage;
    AwesomeValidation awesomeValidation;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        Intent intent = getIntent();
        String UserType = intent.getStringExtra("UserType");

        UserEmail =(EditText)findViewById(R.id.email_edt_xml);
        SendEmailButton =(Button)findViewById(R.id.send_email_btn_xml_for_login);
        RedirectToSignUpPage =(TextView) findViewById(R.id.signup_text_for_redirect);
        firebaseAuth = FirebaseAuth.getInstance();

        ProgressDialog progressDialog =  new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please Wait");


        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.email_edt_xml, Patterns.EMAIL_ADDRESS,R.string.Invalid_Email_Message);

        SendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                if(awesomeValidation.validate())
                {
                    progressDialog.show();
                    String UserEmailText = UserEmail.getText().toString();

                    firebaseAuth.sendPasswordResetEmail(UserEmailText).addOnCompleteListener(new OnCompleteListener<Void>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                progressDialog.hide();
                                Toast.makeText(ForgotPasswordActivity.this, "Forgot Password Link Send To Your Email Id", Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                                intent.putExtra("UserType", UserType);
                                startActivity(i);
                                finish();

                            }

                            else
                            {
                                progressDialog.hide();
                                Toast.makeText(ForgotPasswordActivity.this, "Email Id is Not Found", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

                else
                {
                    progressDialog.hide();
                    Toast.makeText(ForgotPasswordActivity.this, "Please Enter Valid Detail", Toast.LENGTH_SHORT).show();
                }


            }
        });

        RedirectToSignUpPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i=new Intent(ForgotPasswordActivity.this, SignupOption.class);
                intent.putExtra("UserType", UserType);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent=new Intent(ForgotPasswordActivity.this, LoginOption.class);
        startActivity(intent);
        finishAffinity();
        finish();

    }
}