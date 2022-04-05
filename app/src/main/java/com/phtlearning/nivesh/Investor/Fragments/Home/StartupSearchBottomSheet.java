package com.phtlearning.nivesh.Investor.Fragments.Home;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.phtlearning.nivesh.R;

public class StartupSearchBottomSheet extends BottomSheetDialogFragment {
    private StartupSearchBottomSheetListener sbsListener;
    Button edu, fin, health, tech, show, amount, name, noin, per, time;
    TextView clr;
    String checkedCatButton, checkedSortButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  @Nullable ViewGroup container, @Nullable  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.startupsearch_bottomsheet, container, false);

        edu = v.findViewById(R.id.cat1);
        fin = v.findViewById(R.id.cat2);
        health = v.findViewById(R.id.cat3);
        tech = v.findViewById(R.id.cat4);
        amount = v.findViewById(R.id.sortbyamount);
        name = v.findViewById(R.id.sortbyname);
        noin = v.findViewById(R.id.sortbynoin);
        per = v.findViewById(R.id.sortbyper);
        time = v.findViewById(R.id.sortbtime);
        show = v.findViewById(R.id.show);
        clr = v.findViewById(R.id.clear);

        edu.setOnClickListener(view -> {
            edu.setBackgroundResource(R.drawable.categorybtn_selected);
            edu.setTextColor(getResources().getColor(R.color.white));
            fin.setBackgroundResource(R.drawable.categorybtn_notselected);
            fin.setTextColor(getResources().getColor(R.color.lavenderblue));
            health.setBackgroundResource(R.drawable.categorybtn_notselected);
            health.setTextColor(getResources().getColor(R.color.lavenderblue));
            tech.setBackgroundResource(R.drawable.categorybtn_notselected);
            tech.setTextColor(getResources().getColor(R.color.lavenderblue));
            checkedCatButton = "education";
            Log.i(TAG,  "78611: " + checkedCatButton + ", " + checkedSortButton);
        });

        fin.setOnClickListener(view -> {
            edu.setBackgroundResource(R.drawable.categorybtn_notselected);
            edu.setTextColor(getResources().getColor(R.color.lavenderblue));
            fin.setBackgroundResource(R.drawable.categorybtn_selected);
            fin.setTextColor(getResources().getColor(R.color.white));
            health.setBackgroundResource(R.drawable.categorybtn_notselected);
            health.setTextColor(getResources().getColor(R.color.lavenderblue));
            tech.setBackgroundResource(R.drawable.categorybtn_notselected);
            tech.setTextColor(getResources().getColor(R.color.lavenderblue));
            checkedCatButton = "finance";
            Log.i(TAG,  "78612: " + checkedCatButton + ", " + checkedSortButton);

        });

        health.setOnClickListener(view -> {
            edu.setBackgroundResource(R.drawable.categorybtn_notselected);
            edu.setTextColor(getResources().getColor(R.color.lavenderblue));
            fin.setBackgroundResource(R.drawable.categorybtn_notselected);
            fin.setTextColor(getResources().getColor(R.color.lavenderblue));
            health.setBackgroundResource(R.drawable.categorybtn_selected);
            health.setTextColor(getResources().getColor(R.color.white));
            tech.setBackgroundResource(R.drawable.categorybtn_notselected);
            tech.setTextColor(getResources().getColor(R.color.lavenderblue));
            checkedCatButton = "healthcare";
            Log.i(TAG,  "78613: " + checkedCatButton + ", " + checkedSortButton);

        });

        tech.setOnClickListener(view -> {
            edu.setBackgroundResource(R.drawable.categorybtn_notselected);
            edu.setTextColor(getResources().getColor(R.color.lavenderblue));
            fin.setBackgroundResource(R.drawable.categorybtn_notselected);
            fin.setTextColor(getResources().getColor(R.color.lavenderblue));
            health.setBackgroundResource(R.drawable.categorybtn_notselected);
            health.setTextColor(getResources().getColor(R.color.lavenderblue));
            tech.setBackgroundResource(R.drawable.categorybtn_selected);
            tech.setTextColor(getResources().getColor(R.color.white));
            checkedCatButton = "technology";
            Log.i(TAG,  "78614: " + checkedCatButton + ", " + checkedSortButton);

        });

        amount.setOnClickListener(view -> {
            amount.setBackgroundResource(R.drawable.categorybtn_selected);
            amount.setTextColor(getResources().getColor(R.color.white));
            name.setBackgroundResource(R.drawable.categorybtn_notselected);
            name.setTextColor(getResources().getColor(R.color.lavenderblue));
            noin.setBackgroundResource(R.drawable.categorybtn_notselected);
            noin.setTextColor(getResources().getColor(R.color.lavenderblue));
            per.setBackgroundResource(R.drawable.categorybtn_notselected);
            per.setTextColor(getResources().getColor(R.color.lavenderblue));
            time.setBackgroundResource(R.drawable.categorybtn_notselected);
            time.setTextColor(getResources().getColor(R.color.lavenderblue));
            checkedSortButton = "minAmount";
            Log.i(TAG,  "78621: " + checkedCatButton + ", " + checkedSortButton);

        });

        name.setOnClickListener(view -> {
            amount.setBackgroundResource(R.drawable.categorybtn_notselected);
            amount.setTextColor(getResources().getColor(R.color.lavenderblue));
            name.setBackgroundResource(R.drawable.categorybtn_selected);
            name.setTextColor(getResources().getColor(R.color.white));
            noin.setBackgroundResource(R.drawable.categorybtn_notselected);
            noin.setTextColor(getResources().getColor(R.color.lavenderblue));
            per.setBackgroundResource(R.drawable.categorybtn_notselected);
            per.setTextColor(getResources().getColor(R.color.lavenderblue));
            time.setBackgroundResource(R.drawable.categorybtn_notselected);
            time.setTextColor(getResources().getColor(R.color.lavenderblue));
            checkedSortButton = "companyName";
            Log.i(TAG,  "78622: " + checkedCatButton + ", " + checkedSortButton);

        });

        noin.setOnClickListener(view -> {
            amount.setBackgroundResource(R.drawable.categorybtn_notselected);
            amount.setTextColor(getResources().getColor(R.color.lavenderblue));
            name.setBackgroundResource(R.drawable.categorybtn_notselected);
            name.setTextColor(getResources().getColor(R.color.lavenderblue));
            noin.setBackgroundResource(R.drawable.categorybtn_selected);
            noin.setTextColor(getResources().getColor(R.color.white));
            per.setBackgroundResource(R.drawable.categorybtn_notselected);
            per.setTextColor(getResources().getColor(R.color.lavenderblue));
            time.setBackgroundResource(R.drawable.categorybtn_notselected);
            time.setTextColor(getResources().getColor(R.color.lavenderblue));
            checkedSortButton = "totalInvestors";
            Log.i(TAG,  "78623: " + checkedCatButton + ", " + checkedSortButton);

        });

        per.setOnClickListener(view -> {
            amount.setBackgroundResource(R.drawable.categorybtn_notselected);
            amount.setTextColor(getResources().getColor(R.color.lavenderblue));
            name.setBackgroundResource(R.drawable.categorybtn_notselected);
            name.setTextColor(getResources().getColor(R.color.lavenderblue));
            noin.setBackgroundResource(R.drawable.categorybtn_notselected);
            noin.setTextColor(getResources().getColor(R.color.lavenderblue));
            per.setBackgroundResource(R.drawable.categorybtn_selected);
            per.setTextColor(getResources().getColor(R.color.white));
            time.setBackgroundResource(R.drawable.categorybtn_notselected);
            time.setTextColor(getResources().getColor(R.color.lavenderblue));
            checkedSortButton = "perRaised";
            Log.i(TAG,  "78624: " + checkedCatButton + ", " + checkedSortButton);

        });

        time.setOnClickListener(view -> {
            amount.setBackgroundResource(R.drawable.categorybtn_notselected);
            amount.setTextColor(getResources().getColor(R.color.lavenderblue));
            name.setBackgroundResource(R.drawable.categorybtn_notselected);
            name.setTextColor(getResources().getColor(R.color.lavenderblue));
            noin.setBackgroundResource(R.drawable.categorybtn_notselected);
            noin.setTextColor(getResources().getColor(R.color.lavenderblue));
            per.setBackgroundResource(R.drawable.categorybtn_notselected);
            per.setTextColor(getResources().getColor(R.color.lavenderblue));
            time.setBackgroundResource(R.drawable.categorybtn_selected);
            time.setTextColor(getResources().getColor(R.color.white));
            checkedSortButton = "endDate";
            Log.i(TAG,  "78625: " + checkedCatButton + ", " + checkedSortButton);

        });

        show.setOnClickListener(v1 -> {
            Log.i(TAG, "78632: " + sbsListener);
            sbsListener.onButtonClicked(checkedCatButton, checkedSortButton);
            dismiss();
        });

        clr.setOnClickListener(view -> {
            edu.setBackgroundResource(R.drawable.categorybtn_notselected);
            edu.setTextColor(getResources().getColor(R.color.lavenderblue));
            fin.setBackgroundResource(R.drawable.categorybtn_notselected);
            fin.setTextColor(getResources().getColor(R.color.lavenderblue));
            health.setBackgroundResource(R.drawable.categorybtn_notselected);
            health.setTextColor(getResources().getColor(R.color.lavenderblue));
            tech.setBackgroundResource(R.drawable.categorybtn_notselected);
            tech.setTextColor(getResources().getColor(R.color.lavenderblue));
            amount.setBackgroundResource(R.drawable.categorybtn_notselected);
            amount.setTextColor(getResources().getColor(R.color.lavenderblue));
            name.setBackgroundResource(R.drawable.categorybtn_notselected);
            name.setTextColor(getResources().getColor(R.color.lavenderblue));
            noin.setBackgroundResource(R.drawable.categorybtn_notselected);
            noin.setTextColor(getResources().getColor(R.color.lavenderblue));
            per.setBackgroundResource(R.drawable.categorybtn_notselected);
            per.setTextColor(getResources().getColor(R.color.lavenderblue));
            time.setBackgroundResource(R.drawable.categorybtn_notselected);
            time.setTextColor(getResources().getColor(R.color.lavenderblue));
            checkedCatButton = checkedSortButton = null;

        });

        return v;
    }
    public interface StartupSearchBottomSheetListener{
        void onButtonClicked(@Nullable String checkedCarButton, @Nullable String checkedSortButton);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            Log.i(TAG,  "78631: onAttach invoked ");
            sbsListener = (StartupSearchBottomSheetListener) getParentFragment();
        }catch(ClassCastException e){
            throw new ClassCastException(context + " must implement StartupSearchBottomSheetListener.");
        }
    }
}
