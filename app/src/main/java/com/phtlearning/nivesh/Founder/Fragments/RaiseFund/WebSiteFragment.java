package com.phtlearning.nivesh.Founder.Fragments.RaiseFund;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.phtlearning.nivesh.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WebSiteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WebSiteFragment extends Fragment {

    EditText WebsiteEditText;
    Button WebsiteNxtBtn;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WebSiteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WebSiteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WebSiteFragment newInstance(String param1, String param2) {
        WebSiteFragment fragment = new WebSiteFragment();
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
        View view = inflater.inflate(R.layout.fragment_web_site, container, false);
        WebsiteEditText =(EditText) view.findViewById(R.id.website_edt_xml);
        WebsiteNxtBtn = (Button)view.findViewById(R.id.website_next_btn);

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
        String SolutionStatement = getArguments().getString("SolutionStatement");
        String PitchLink = getArguments().getString("PitchLink");
        String TotalRevenue = getArguments().getString("TotalRevenue");
        String TotalEmp = getArguments().getString("TotalEmp");

        WebsiteNxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String WebSiteLink = WebsiteEditText.getText().toString();

                if(TextUtils.isEmpty(WebSiteLink))
                {
                    Toast.makeText(getContext(), "This Field be Can't Empty", Toast.LENGTH_SHORT).show();
                }
                else if(!Patterns.WEB_URL.matcher(WebSiteLink).matches())
                {
                    Toast.makeText(getContext(), "Please Enter Valid Website Url!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    SubmitFragment obj = new SubmitFragment();
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
                    args.putString("PitchLink", PitchLink);
                    args.putString("TotalRevenue", TotalRevenue);
                    args.putString("TotalEmp", TotalEmp);
                    args.putString("WebSiteLink", WebSiteLink);
                    obj.setArguments(args);
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag, obj).commit();
                }
            }
        });
        return view;
    }
}