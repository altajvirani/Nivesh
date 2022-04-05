package com.phtlearning.nivesh.Founder.Fragments.Profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.phtlearning.nivesh.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FounderAboutMe#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FounderAboutMe extends Fragment {
    Button UserNameNxtBtn;
    EditText UserNameEditText;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FounderAboutMe() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AboutCompany.
     */
    // TODO: Rename and change types and number of parameters
    public static FounderAboutMe newInstance(String param1, String param2) {
        FounderAboutMe fragment = new FounderAboutMe();
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
        View view = inflater.inflate(R.layout.fragment_founder_about_me, container, false);

        String UserName = getArguments().getString("UserName");
        String UserProfession = getArguments().getString("UserProfession");
        String UserDOB = getArguments().getString("UserDOB");
        String UserGender = getArguments().getString("UserGender");
        String ProfileImage = getArguments().getString("ProfileImage");

        UserNameNxtBtn = view.findViewById(R.id.user_profile_about_me_nxt_btn);
        UserNameEditText = view.findViewById(R.id.about_me_edit_txt);

        UserNameNxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String AboutMe = UserNameEditText.getText().toString().trim();
                if(TextUtils.isEmpty(AboutMe))
                {
                    Toast.makeText(getContext(), "This Field be Can't Empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    FounderUserContactNumber obj = new FounderUserContactNumber();
                    Bundle args = new Bundle();
                    args.putString("UserName", UserName);
                    args.putString("UserProfession", UserProfession);
                    args.putString("UserDOB", UserDOB);
                    args.putString("UserGender", UserGender);
                    args.putString("ProfileImage", ProfileImage);
                    args.putString("AboutMe", AboutMe);
                    obj.setArguments(args);
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag, obj).commit();
                }
            }

        });


        return view;
    }
}