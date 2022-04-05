package com.phtlearning.nivesh.Investor.Fragments.Profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.phtlearning.nivesh.Founder.Fragments.Profile.FounderUserDOB;
import com.phtlearning.nivesh.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InvestorProfession#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InvestorProfession extends Fragment {
    Button UserProNxtBtn;
    EditText UserProEditText;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InvestorProfession() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InvestorProfession.
     */
    // TODO: Rename and change types and number of parameters
    public static InvestorProfession newInstance(String param1, String param2) {
        InvestorProfession fragment = new InvestorProfession();
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
        View view = inflater.inflate(R.layout.fragment_investor_profession, container, false);
        UserProNxtBtn = (Button) view.findViewById(R.id.investor_pro_btn_txt);
        UserProEditText = (EditText) view.findViewById(R.id.investor_pro_edt_txt);
        String UserName = getArguments().getString("UserName");

        UserProNxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserProfession = UserProEditText.getText().toString().trim();
                if(TextUtils.isEmpty(UserProfession))
                {
                    Toast.makeText(getContext(), "This Field Can't be Empty", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    InvestorDOB obj = new InvestorDOB();
                    Bundle args = new Bundle();
                    args.putString("UserName", UserName);
                    args.putString("UserProfession", UserProfession);
                    obj.setArguments(args);
                    getFragmentManager().beginTransaction().replace(R.id.fragment_investor_container_view_tag, obj).commit();
                }

            }
        });

        return view;
    }
}