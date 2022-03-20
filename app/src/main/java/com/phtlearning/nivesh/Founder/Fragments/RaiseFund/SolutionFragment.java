package com.phtlearning.nivesh.Founder.Fragments.RaiseFund;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.phtlearning.nivesh.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SolutionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SolutionFragment extends Fragment {
    Button SolutionNxtBtn;
    EditText SolutionEdittext;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SolutionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SolutionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SolutionFragment newInstance(String param1, String param2) {
        SolutionFragment fragment = new SolutionFragment();
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
        View view = inflater.inflate(R.layout.fragment_solution, container, false);

        SolutionEdittext = (EditText)view.findViewById(R.id.solution_edit_txt);
        SolutionNxtBtn = (Button)view.findViewById(R.id.solution_nxt_btn);
        SolutionEdittext.setGravity(Gravity.TOP);

        String CompanyName = getArguments().getString("CompanyName");
        String FounderName = getArguments().getString("FounderName");
        String CoverImage = getArguments().getString("CoverImage");
        String CompanyDiscription = getArguments().getString("CompanyDiscription");
        String CompanyCategory = getArguments().getString("CompanyCategory");
        String StartDate = getArguments().getString("StartDate");
        String EndDate = getArguments().getString("EndDate");
        String MinInvestment = getArguments().getString("MinInvestment");
        String TotalTargetAmount = getArguments().getString("TotalTargetAmount");
        String TotalInvestors = getArguments().getString("TotalInvestors");
        String ProblemStatement = getArguments().getString("ProblemStatement");

        SolutionNxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String SolutionStatement = SolutionEdittext.getText().toString();

                if(TextUtils.isEmpty(SolutionStatement))
                {
                    Toast.makeText(getContext(), "This Field be Can't Empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    PitchFragment obj = new PitchFragment();
                    Bundle args = new Bundle();
                    args.putString("CompanyName", CompanyName);
                    args.putString("FounderName", FounderName);
                    args.putString("CoverImage", CoverImage);
                    args.putString("CompanyDiscription", CompanyDiscription);
                    args.putString("CompanyCategory", CompanyCategory);
                    args.putString("StartDate", StartDate);
                    args.putString("EndDate", EndDate);
                    args.putString("MinInvestment", MinInvestment);
                    args.putString("TotalTargetAmount", TotalTargetAmount);
                    args.putString("TotalInvestors", TotalInvestors);
                    args.putString("ProblemStatement", ProblemStatement);
                    args.putString("SolutionStatement", SolutionStatement);
                    obj.setArguments(args);
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag, obj).commit();
                }

            }
        });

        return  view;
    }
}