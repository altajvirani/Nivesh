package com.phtlearning.nivesh.Investor.Fragments.Home.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class VPAdapter extends FragmentPagerAdapter {
    private final ArrayList<Fragment> fal = new ArrayList<>();
    private final ArrayList<String> ftitle = new ArrayList<>();

    public VPAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fal.get(position);
    }

    @Override
    public int getCount() {
        return fal.size();
    }

    public void addFragmentToFal(Fragment f, String s){
        fal.add(f);
        ftitle.add(s);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return ftitle.get(position);
    }
}

