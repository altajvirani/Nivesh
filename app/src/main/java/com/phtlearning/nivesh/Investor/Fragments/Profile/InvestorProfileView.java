package com.phtlearning.nivesh.Investor.Fragments.Profile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.phtlearning.nivesh.Authentication.Option.LoginOption.LoginOption;
import com.phtlearning.nivesh.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InvestorProfileView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InvestorProfileView extends Fragment {
    DatabaseReference founderReference, userTypeReference;
    TextView LogoutText, UserNameTxtView, UserProfessionTxtView,UserDOBTxtView, UserEmailTxtView, UserContactNumberTxtView, UserGenderTxtView,UserAboutMeTxtView;
    ImageView UserProfileImageTxtView;
    String UserName, UserProfession, UserDOB, UserEmail,UserAboutMe, UserContactNumber, UserProfileImage, UserGender;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InvestorProfileView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InvestorProfileView.
     */
    // TODO: Rename and change types and number of parameters
    public static InvestorProfileView newInstance(String param1, String param2) {
        InvestorProfileView fragment = new InvestorProfileView();
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
        View view = inflater.inflate(R.layout.fragment_investor_profile_view, container, false);
        LogoutText = view.findViewById(R.id.logout_text);
        UserNameTxtView = view.findViewById(R.id.user_name_profile_view);
        UserProfessionTxtView = view.findViewById(R.id.user_profession_view);
        UserDOBTxtView = view.findViewById(R.id.user_dob);
        UserEmailTxtView = view.findViewById(R.id.user_email_id);
        UserAboutMeTxtView = view.findViewById(R.id.user_about_me);
        UserContactNumberTxtView = view.findViewById(R.id.user_contact_number);
        UserProfileImageTxtView = view.findViewById(R.id.user_image_view);
        UserGenderTxtView = view.findViewById(R.id.user_gender);


        /*making personal info visible*/



        userTypeReference = FirebaseDatabase.getInstance().getReference("UserType");
        String CurrentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        userTypeReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String UserType = snapshot.child(CurrentUserUid).child("userType").getValue().toString();
                if(UserType.equals("Investor"))
                {
                    founderReference = FirebaseDatabase.getInstance().getReference().child("Investor").child(CurrentUserUid);
                    founderReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            UserName = snapshot.child("Profile").child("userName").getValue(String.class);
                            UserProfession = snapshot.child("Profile").child("userProfession").getValue(String.class);
                            UserGender = snapshot.child("Profile").child("userGender").getValue(String.class);
                            UserDOB = snapshot.child("Profile").child("userDOB").getValue(String.class);
                            UserAboutMe = snapshot.child("Profile").child("userAboutMe").getValue(String.class);
                            UserProfileImage = snapshot.child("Profile").child("userProfileImage").getValue(String.class);
                            UserContactNumber = snapshot.child("Profile").child("userContactNumber").getValue(String.class);
                            UserEmail = snapshot.child("email").getValue(String.class);

                            Glide.with(getActivity()).load(UserProfileImage).into(UserProfileImageTxtView);
                            UserNameTxtView.setText(UserName);
                            UserProfessionTxtView.setText(UserProfession);
                            UserGenderTxtView.setText(UserGender);
                            UserDOBTxtView.setText(UserDOB);
                            UserAboutMeTxtView.setText(UserAboutMe);
                            UserContactNumberTxtView.setText(UserContactNumber);
                            UserEmailTxtView.setText(UserEmail);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                else
                {

                    Toast.makeText(getContext(), "User Not Found!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Please Check Your Internet Connectivity!", Toast.LENGTH_SHORT).show();
            }
        });

        LogoutText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
                firebaseAuth.signOut();
                Toast.makeText(getContext(), "Logout successfully! ", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getActivity(), LoginOption.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }
        });


        return  view;

    }
}