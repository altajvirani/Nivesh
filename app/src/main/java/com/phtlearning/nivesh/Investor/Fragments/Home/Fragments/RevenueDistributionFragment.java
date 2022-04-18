package com.phtlearning.nivesh.Investor.Fragments.Home.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.phtlearning.nivesh.R;

public class RevenueDistributionFragment extends Fragment {
    private String rds;

    public RevenueDistributionFragment (String rds) {
        this.rds = rds;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_revenue_distribution, container, false);
        TextView rdstext = v.findViewById(R.id.rdstext);
        if(!rds.contains("%"))
            rds = rds + "%";
        rdstext.setText(rds);
        return v;
    }
}