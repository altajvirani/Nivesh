package com.phtlearning.nivesh.Investor.Fragments.Profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.phtlearning.nivesh.Founder.Fragments.Profile.FounderUserProfileImage;
import com.phtlearning.nivesh.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InvestorGender#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InvestorGender extends Fragment {
    private RadioGroup radioGroup;
    private RadioButton selectedRadioButton;
    private Button btnDisplay;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InvestorGender() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InvestorGender.
     */
    // TODO: Rename and change types and number of parameters
    public static InvestorGender newInstance(String param1, String param2) {
        InvestorGender fragment = new InvestorGender();
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
        View view = inflater.inflate(R.layout.fragment_investor_gender, container, false);
        String UserName = getArguments().getString("UserName");
        String UserProfession = getArguments().getString("UserProfession");
        String UserDOB = getArguments().getString("UserDOB");
        radioGroup = (RadioGroup) view.findViewById(R.id.gender_radio_investor);
        btnDisplay = (Button) view.findViewById(R.id.gender_founder_next_btn);
        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                selectedRadioButton = (RadioButton) getView().findViewById(selectedId);
                if(selectedId == -1)
                {
                    Toast.makeText(getContext(), "Please Select Your Gender!", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    String UserGender = selectedRadioButton.getText().toString();
                    InvestorProfileImage obj = new InvestorProfileImage();
                    Bundle args = new Bundle();
                    args.putString("UserName", UserName);
                    args.putString("UserProfession", UserProfession);
                    args.putString("UserDOB", UserDOB);
                    args.putString("UserGender", UserGender);

                    obj.setArguments(args);
                    getFragmentManager().beginTransaction().replace(R.id.fragment_investor_container_view_tag, obj).commit();
                }



            }
        });
        return  view;
    }
}