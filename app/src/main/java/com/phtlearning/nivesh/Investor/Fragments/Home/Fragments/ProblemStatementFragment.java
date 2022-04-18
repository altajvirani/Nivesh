package com.phtlearning.nivesh.Investor.Fragments.Home.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.phtlearning.nivesh.R;

public class ProblemStatementFragment extends Fragment {
    private final String ps;

    public ProblemStatementFragment (String ps) {
        this.ps = ps;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_problem_statement, container, false);
        TextView pstext = v.findViewById(R.id.pstext);
        pstext.setText(ps);
        return v;
    }
}