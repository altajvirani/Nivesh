package com.phtlearning.nivesh.Investor.Fragments.Home.Fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.phtlearning.nivesh.R;

public class AboutTheTeamFragment extends Fragment {
    private final String abt;

    public AboutTheTeamFragment (String abt) {
        this.abt = abt;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_about_the_team, container, false);
        TextView abttext = v.findViewById(R.id.abttext);
        abttext.setText(abt);
        abttext.setPaintFlags(abttext.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        abttext.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(abt));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setPackage("com.android.chrome");
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException ex) {
                intent.setPackage(null);
                startActivity(Intent.createChooser(intent, "Select Browser"));
            }
        });
        return v;
    }
}