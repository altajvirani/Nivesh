package com.phtlearning.nivesh.Founder.Fragments.Profile;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.phtlearning.nivesh.R;

import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FounderUserContactNumber#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FounderUserContactNumber extends Fragment {
    EditText ContactEditText;
    Button ContactNxtBtn;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FounderUserContactNumber() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserContactNumber.
     */
    // TODO: Rename and change types and number of parameters
    public static FounderUserContactNumber newInstance(String param1, String param2) {
        FounderUserContactNumber fragment = new FounderUserContactNumber();
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
        View view = inflater.inflate(R.layout.fragment_founder_user_contact_number, container, false);
        String UserName = getArguments().getString("UserName");
        String UserProfession = getArguments().getString("UserProfession");
        String UserDOB = getArguments().getString("UserDOB");
        String UserGender = getArguments().getString("UserGender");
        String ProfileImage = getArguments().getString("ProfileImage");
        String AboutMe = getArguments().getString("AboutMe");

        ContactEditText =(EditText) view.findViewById(R.id.contact_edt_xml);
        ContactNxtBtn = (Button)view.findViewById(R.id.contact_next_btn);

        ProgressDialog progressDialog =  new ProgressDialog(getContext());
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please Wait");

        ContactNxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                String ContactNumber = ContactEditText.getText().toString().trim();

                if(TextUtils.isEmpty(ContactNumber))
                {
                    Toast.makeText(getContext(), "This Field be Can't Empty", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    if(ContactNumber.length() == 10)
                    {
                        PhoneAuthProvider.getInstance().verifyPhoneNumber("+91"+ContactNumber, 1, TimeUnit.SECONDS, getActivity(),
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                        progressDialog.hide();
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        progressDialog.hide();
                                        Toast.makeText(getContext(), "Please Check Internet Connection", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String otp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        super.onCodeSent(otp, forceResendingToken);
                                        progressDialog.hide();
                                        FounderOTPVerification obj = new FounderOTPVerification();
                                        Bundle args = new Bundle();
                                        args.putString("UserName", UserName);
                                        args.putString("UserProfession", UserProfession);
                                        args.putString("UserDOB", UserDOB);
                                        args.putString("UserGender", UserGender);
                                        args.putString("ProfileImage", ProfileImage);
                                        args.putString("AboutMe", AboutMe);
                                        args.putString("ContactNumber", ContactNumber);
                                        args.putString("otp", otp);
                                        obj.setArguments(args);
                                        getFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag, obj).commit();

                                    }
                                });
                    }

                    else
                    {
                        Toast.makeText(getContext(), "Please Enter Correct Number!", Toast.LENGTH_SHORT).show();
                    }





                }
            }
        });
        return view;
    }
}