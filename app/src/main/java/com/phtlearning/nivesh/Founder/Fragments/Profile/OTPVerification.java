package com.phtlearning.nivesh.Founder.Fragments.Profile;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.phtlearning.nivesh.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OTPVerification#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OTPVerification extends Fragment {
     EditText otp1, otp2, otp3, otp4, otp5, otp6;
     Button VerfiyOtp;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OTPVerification() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OTPVerification.
     */
    // TODO: Rename and change types and number of parameters
    public static OTPVerification newInstance(String param1, String param2) {
        OTPVerification fragment = new OTPVerification();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_o_t_p_verification, container, false);

        String UserName = getArguments().getString("UserName");
        String UserProfession = getArguments().getString("UserProfession");
        String UserDOB = getArguments().getString("UserDOB");
        String UserGender = getArguments().getString("UserGender");
        String ProfileImage = getArguments().getString("ProfileImage");
        String AboutMe = getArguments().getString("AboutMe");
        String ContactNumber = getArguments().getString("ContactNumber");
        String otp = getArguments().getString("otp");


        ProgressDialog progressDialog =  new ProgressDialog(getContext());
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please Wait");

        otp1 =(EditText) view.findViewById(R.id.otp1);
        otp2 =(EditText) view.findViewById(R.id.otp2);
        otp3 =(EditText) view.findViewById(R.id.otp3);
        otp4 =(EditText) view.findViewById(R.id.otp4);
        otp5 =(EditText) view.findViewById(R.id.otp5);
        otp6 =(EditText) view.findViewById(R.id.otp6);
        VerfiyOtp =(Button) view.findViewById(R.id.verify);

        otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
              if(otp1.getText().toString().length()==1)
              {
                  otp1.clearFocus();
                  otp2.requestFocus();
                  otp2.setCursorVisible(true);

              }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(otp2.getText().toString().length()==1)
                {
                    otp2.clearFocus();
                    otp3.requestFocus();
                    otp3.setCursorVisible(true);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(otp3.getText().toString().length()==1)
                {
                    otp3.clearFocus();
                    otp4.requestFocus();
                    otp4.setCursorVisible(true);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(otp4.getText().toString().length()==1)
                {
                    otp4.clearFocus();
                    otp5.requestFocus();
                    otp5.setCursorVisible(true);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        otp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(otp5.getText().toString().length()==1)
                {
                    otp5.clearFocus();
                    otp6.requestFocus();
                    otp6.setCursorVisible(true);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        otp6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(otp6.getText().toString().length()==1)
                {
                    otp6.clearFocus();

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        VerfiyOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                if (otp1.getText().toString().trim().isEmpty() &&
                        otp2.getText().toString().trim().isEmpty() &&
                        otp3.getText().toString().trim().isEmpty() &&
                        otp4.getText().toString().trim().isEmpty() &&
                        otp5.getText().toString().trim().isEmpty() && otp6.getText().toString().trim().isEmpty()) {

                    progressDialog.hide();
                    Toast.makeText(getContext(), "Please Enter Valid OTP", Toast.LENGTH_SHORT).show();

                } else {
                    String EnterOtp = otp1.getText().toString() +
                            otp2.getText().toString() +
                            otp3.getText().toString() +
                            otp4.getText().toString() +
                            otp5.getText().toString() +
                            otp6.getText().toString();

                    if (otp != null) {

                            PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(otp,EnterOtp);
                            String OtpCode = phoneAuthCredential.getSmsCode().toString();
                            if(OtpCode.equals(EnterOtp))
                            {
                                progressDialog.hide();
                                ProfileSumbit obj = new ProfileSumbit();
                                Bundle args = new Bundle();
                                args.putString("UserName", UserName);
                                args.putString("UserProfession", UserProfession);
                                args.putString("UserDOB", UserDOB);
                                args.putString("UserGender", UserGender);
                                args.putString("ProfileImage", ProfileImage);
                                args.putString("AboutMe", AboutMe);
                                args.putString("ContactNumber", ContactNumber);
                                obj.setArguments(args);
                                getFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag, obj).commit();
                            }
                            else
                            {
                                Toast.makeText(getContext(), "Please Enter Correct OTP!", Toast.LENGTH_SHORT).show();
                            }


                    }
                    else {
                        progressDialog.hide();
                        Toast.makeText(getContext(), "Please Check Internet Connection", Toast.LENGTH_SHORT).show();
                    }


                }

            }
        });




        return view;

    }
}