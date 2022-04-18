package com.phtlearning.nivesh.Investor.Fragments.Home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.phtlearning.nivesh.R;
import com.suke.widget.SwitchButton;

public class StartupSearchBottomSheet extends BottomSheetDialogFragment {
    private StartupSearchBottomSheetListener sbsListener;
    private Button edu;
    private Button fin;
    private Button health;
    private Button tech;
    private Button amount;
    private Button name;
    private Button noin;
    private Button per;
    private Button time;
    private Spinner spinner;
    private SwitchButton sb;
    private @Nullable String checkedCatButton, checkedSortButton = "";
    private boolean descending, showInactiveDeals = false;
    public static String TAG = "StartupBottomSheet";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  @Nullable ViewGroup container, @Nullable  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.startupsearch_bottomsheet, container, false);

        String[] sortby = {"Sort by", "Ascending", "Descending"};
        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(requireContext(), R.layout.sortby_spinner_item, sortby){
                @Override
                public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
                View view;

                if (position == 0) {
                    TextView tv = new TextView(getContext());
                    tv.setHeight(0);
                    tv.setVisibility(View.GONE);
                    view = tv;
                } else {
                    view = super.getDropDownView(position, null, parent);
                }
                return view;
            }
        };
        adapter_state.setDropDownViewResource(R.layout.sortby_spinner_dropdown_item);
        spinner= v.findViewById(R.id.sortbyspinner);
        spinner.canScrollVertically(0);
        spinner.setAdapter(adapter_state);

        edu = v.findViewById(R.id.cat1);
        fin = v.findViewById(R.id.cat2);
        health = v.findViewById(R.id.cat3);
        tech = v.findViewById(R.id.cat4);
        amount = v.findViewById(R.id.sortbyamount);
        name = v.findViewById(R.id.sortbyname);
        noin = v.findViewById(R.id.sortbynoin);
        per = v.findViewById(R.id.sortbyper);
        time = v.findViewById(R.id.sortbtime);
        sb = v.findViewById(R.id.showinactivedeals);
        Button show = v.findViewById(R.id.show);
        TextView clr = v.findViewById(R.id.clear);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                descending = spinner.getSelectedItemPosition() == 2;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                descending = true;
            }
        });

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
        });

        sb.setOnCheckedChangeListener((view, isChecked) -> {
            if(sb.isChecked())
                showInactiveDeals = true;
            else
                showInactiveDeals = false;
        });

        show.setOnClickListener(v1 -> {
            sbsListener.onButtonClicked(checkedCatButton, checkedSortButton, descending, showInactiveDeals);
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
            checkedCatButton = checkedSortButton = "";
            descending = showInactiveDeals = false;
        });

        return v;
    }
    public interface StartupSearchBottomSheetListener{
        void onButtonClicked(@Nullable String checkedCarButton, @Nullable String checkedSortButton, boolean descending, boolean showInactiveDeals);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{ sbsListener = (StartupSearchBottomSheetListener) getParentFragment(); }
        catch(ClassCastException e){ throw new ClassCastException(context + " must implement StartupSearchBottomSheetListener."); }
    }
}