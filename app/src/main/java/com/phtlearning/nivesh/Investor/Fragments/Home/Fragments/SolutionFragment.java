package com.phtlearning.nivesh.Investor.Fragments.Home.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.phtlearning.nivesh.R;

public class SolutionFragment extends Fragment {
    private final String sol;

    public SolutionFragment (String sol) {
        this.sol = sol;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_solution_investor, container, false);
        TextView soltext = v.findViewById(R.id.solutiontext);
        soltext.setText(sol);
        return v;
    }
}