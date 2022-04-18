package com.phtlearning.nivesh.Investor.Fragments.Home.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.phtlearning.nivesh.R;

public class DescriptionFragment extends Fragment {
    private final String desc;

    public DescriptionFragment(String desc) {
       this.desc = desc;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_description_investor, container, false);
        TextView desctext = v.findViewById(R.id.desctext);
        desctext.setText(desc);
        return v;
    }
}