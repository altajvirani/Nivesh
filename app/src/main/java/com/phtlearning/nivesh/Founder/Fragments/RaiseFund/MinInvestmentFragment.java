package com.phtlearning.nivesh.Founder.Fragments.RaiseFund;

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
 * Use the {@link MinInvestmentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MinInvestmentFragment extends Fragment {

    EditText MinInvestEditText;
    Button MinInvestNxtBtn;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MinInvestmentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MinInvestmentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MinInvestmentFragment newInstance(String param1, String param2) {
        MinInvestmentFragment fragment = new MinInvestmentFragment();
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
        View view = inflater.inflate(R.layout.fragment_min_investment, container, false);
        MinInvestEditText=(EditText) view.findViewById(R.id.mini_invest_edt_xml);
        MinInvestNxtBtn = (Button)view.findViewById(R.id.min_invest_next_btn);

        String CompanyName = getArguments().getString("CompanyName");
        String FounderName = getArguments().getString("FounderName");
        String CoverImage = getArguments().getString("CoverImage");
        String CompanyDiscription = getArguments().getString("CompanyDiscription");
        String CompanyCategory = getArguments().getString("CompanyCategory");
        String StartDate = getArguments().getString("StartDate");
        String EndDate = getArguments().getString("EndDate");

        MinInvestNxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String MinInvestment = MinInvestEditText.getText().toString();

                if(TextUtils.isEmpty(MinInvestment))
                {
                    Toast.makeText(getContext(), "This Field be Can't Empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    TargetFragment obj = new TargetFragment();
                    Bundle args = new Bundle();
                    args.putString("CompanyName", CompanyName);
                    args.putString("FounderName", FounderName);
                    args.putString("CoverImage", CoverImage);
                    args.putString("CompanyDiscription", CompanyDiscription);
                    args.putString("CompanyCategory", CompanyCategory);
                    args.putString("StartDate", StartDate);
                    args.putString("EndDate", EndDate);
                    args.putString("MinInvestment", MinInvestment);
                    obj.setArguments(args);
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag, obj).commit();
                }
            }
        });
        return view;
    }
}