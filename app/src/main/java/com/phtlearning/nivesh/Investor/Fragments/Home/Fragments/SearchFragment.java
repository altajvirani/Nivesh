package com.phtlearning.nivesh.Investor.Fragments.Home.Fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.phtlearning.nivesh.Investor.Fragments.Home.SCard;
import com.phtlearning.nivesh.Investor.Fragments.Home.SCardAdapter;
import com.phtlearning.nivesh.Investor.Fragments.Home.StartupSearchBottomSheet;
import com.phtlearning.nivesh.R;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class SearchFragment extends Fragment implements StartupSearchBottomSheet.StartupSearchBottomSheetListener{
    private RecyclerView recyclerView;
    private SCardAdapter adapter;
    private SwipeRefreshLayout search_srl;
    private EditText searchBar;
    private String searchString = "", category = "", sortingCriteria = "";
    private boolean descending = false, showInactiveDeals = false;
    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = v.findViewById(R.id.search_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        search_srl = v.findViewById(R.id.search_swiperefresh);
        search_srl.setColorSchemeResources(R.color.blue, R.color.gradneon, R.color.orange, R.color.darkpink);
        search_srl.setOnRefreshListener(() -> new RefreshStartups().execute(new FetchOnExecute(searchString, category, sortingCriteria, descending, showInactiveDeals)));

        searchBar = v.findViewById(R.id.searchpage_searchbar);
        searchBar.setOnEditorActionListener((v1, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchString = searchBar.getText().toString();
                Log.i("searchstring: ", searchString);
                if(!searchString.equals("")) {
                    searchString = searchString.toLowerCase();
                    searchString = searchString.replace(".", "");
                    searchString = searchString.replace("'", "");
                    searchString = searchString.replace("’", "");
                    search(searchString, category, sortingCriteria, descending, showInactiveDeals);
                }else
                    search("", "", "", descending, showInactiveDeals);
                return true;
            }
            return false;
        });
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                searchString = searchBar.getText().toString();
                if(searchString.equals(""))
                    search("", "", "", descending, showInactiveDeals);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {}
        });

        ImageButton filterStartups = v.findViewById(R.id.filterButton);
        filterStartups.setOnClickListener(view -> {
            StartupSearchBottomSheet sbs = new StartupSearchBottomSheet();
            if(getChildFragmentManager().findFragmentByTag(StartupSearchBottomSheet.TAG) == null)
                sbs.show(getChildFragmentManager(), StartupSearchBottomSheet.TAG);
        });
        if(isConnectedToInternet()) search("","" , "", false, false);
        return v;
    }

    @Override
    public void onButtonClicked(@Nullable String cat, @Nullable String sort, boolean desc, boolean sid) {
        category = cat;
        sortingCriteria = sort;
        descending = desc;
        showInactiveDeals = sid;

        if(Objects.equals(cat, "") || cat==null){
            if(Objects.equals(sort, "") || sort==null)
                search(searchString, "", "", descending, showInactiveDeals);
            else
                search(searchString, "", sort, descending, showInactiveDeals);
        }
        else if(Objects.equals(sort, "") || sort==null)
            search(searchString, "", "", descending, showInactiveDeals);
        else
            search(searchString, cat, sort, descending, showInactiveDeals);
    }

    protected void search(@Nullable String ss, @Nullable String cat, @Nullable String sort, boolean desc, boolean sid){
        FirebaseDatabase.getInstance().getReference().child("Category_wise").addValueEventListener(new ValueEventListener() {
            protected boolean foundFlag;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    ArrayList<SCard> sCardList = new ArrayList<>();
                    ArrayList<DataSnapshot> dsal = new ArrayList<>();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        if (snapshot.exists()){
                            if(!Objects.equals(cat, "")){
                                if(Objects.equals(snapshot.getKey(), cat)){
                                    for (DataSnapshot shot : snapshot.getChildren()){
                                        if (shot.exists()){
                                            if (!Objects.equals(ss, "")){
                                                if(Objects.equals(Objects.requireNonNull(shot.getKey()), ss)){
                                                    dsal.add(shot);
                                                    foundFlag = true;
                                                    Log.i("786110", "search string: " + searchString + ", string to be compared: " + Objects.requireNonNull(shot.getKey()) + ", found flag: " + foundFlag);
                                                }
                                            }else{
                                                dsal.add(shot);
                                                foundFlag = false;
                                            }
                                        }
                                    }
                                }
                            }else{
                                for (DataSnapshot shot : snapshot.getChildren()){
                                    if (shot.exists()){
                                        if (!Objects.equals(ss, "")){
                                            if(Objects.equals(Objects.requireNonNull(shot.getKey()), ss)){
                                                dsal.add(shot);
                                                foundFlag = true;
                                                Log.i("786110", "search string: " + ss + ", string to be compared: " + Objects.requireNonNull(shot.getKey()) + ", found flag: " + foundFlag);
                                            }
                                        }else{
                                            dsal.add(shot);
                                            foundFlag = false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (sort != null) {
                        if(!sort.equals("")){
                            if(sort.equals("minAmount") || sort.equals("perRaised") || sort.equals("totalInvestors")) {
                                if(!descending)
                                    dsal.sort((ds1, ds2) -> {
                                        return (int) (Float.parseFloat(Objects.requireNonNull(ds1.child(sort).getValue()).toString()) - Float.parseFloat(Objects.requireNonNull(ds2.child(sort).getValue()).toString())); // sort order
                                    });
                                else
                                    dsal.sort((ds1, ds2) -> {
                                        return (int) (Float.parseFloat(Objects.requireNonNull(ds2.child(sort).getValue()).toString()) - Float.parseFloat(Objects.requireNonNull(ds1.child(sort).getValue()).toString())); // sort order
                                    });
                            }else{
                                if(!desc)
                                    dsal.sort(Comparator.comparing(o -> Objects.requireNonNull(o.child(sort).getValue()).toString()));
                                else
                                    dsal.sort((o1, o2) -> Objects.requireNonNull(o2.child(sort).getValue()).toString().compareTo(Objects.requireNonNull(o1.child(sort).getValue()).toString()));
                            }
                        }else{
                            if(!desc)
                                dsal.sort(Comparator.comparing(o -> Objects.requireNonNull(o.child("companyName").getValue()).toString()));
                            else
                                dsal.sort((o1, o2) -> Objects.requireNonNull(o2.child("companyName").getValue()).toString().compareTo(Objects.requireNonNull(o1.child("companyName").getValue()).toString()));
                        }
                    }else{
                        if(!desc)
                            dsal.sort(Comparator.comparing(o -> Objects.requireNonNull(o.child("companyName").getValue()).toString()));
                        else
                            dsal.sort((o1, o2) -> Objects.requireNonNull(o2.child("companyName").getValue()).toString().compareTo(Objects.requireNonNull(o1.child("companyName").getValue()).toString()));
                    }

                    for (DataSnapshot shot : dsal) {
                        final SCard scard =  new SCard(Objects.requireNonNull(shot.child("companyLogo").getValue()).toString(),
                                Objects.requireNonNull(shot.child("companyDescription").getValue()).toString(),
                                Objects.requireNonNull(shot.child("endDate").getValue()).toString(),
                                Integer.parseInt(Objects.requireNonNull(shot.child("minAmount").getValue()).toString()),
                                Objects.requireNonNull(shot.child("companyName").getValue()).toString(),
                                Integer.parseInt(Objects.requireNonNull(shot.child("totalInvestors").getValue()).toString()),
                                Integer.parseInt(Objects.requireNonNull(shot.child("raisedAmount").getValue()).toString()),
                                Integer.parseInt(Objects.requireNonNull(shot.child("totalTargetAmount").getValue()).toString()),
                                Objects.requireNonNull(shot.child("status").getValue()).toString());
                        sCardList.add(scard);
                    }
                    adapter = new SCardAdapter(sCardList, sid);
                    recyclerView.setAdapter(adapter);

                    if(!Objects.equals(ss, "")){
                        if(!foundFlag){
                            Log.i("dsal: ", dsal.toString());
                            Toast.makeText(requireContext(), "No deals with the given name found.", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    static class FetchOnExecute {
        String ss, cat, sort;
        boolean desc, sid;

        FetchOnExecute(@Nullable String ss, @Nullable String cat, @Nullable String sort, boolean desc, boolean sid) {
            this.ss = ss;
            this.cat = cat;
            this.sort = sort;
            this.desc = desc;
            this.sid = sid;
        }
    }

    public class RefreshStartups extends AsyncTask<FetchOnExecute, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(FetchOnExecute... params) {
            try {
                if(isConnectedToInternet()){
                    String searchString = params[0].ss, category = params[0].cat, sortingCriteria = params[0].sort;
                    boolean descending = params[0].desc, showInactiveDeals = params[0].sid;
                    if(Objects.equals(category, "") || category==null){
                        if(Objects.equals(sortingCriteria, "") || sortingCriteria==null) {
                            if (Objects.equals(searchString, "") || searchString == null)
                                search("", "", "", descending, showInactiveDeals);
                            else
                                search(searchString, "", "", descending, showInactiveDeals);
                        }else{
                            if (Objects.equals(searchString, "") || searchString == null)
                                search("", "", sortingCriteria, descending, showInactiveDeals);
                            else
                                search(searchString, "", sortingCriteria, descending, showInactiveDeals);
                        }
                    }else if(Objects.equals(sortingCriteria, "") || sortingCriteria==null){
                        if (Objects.equals(searchString, "") || searchString == null)
                            search("", category, "", descending, showInactiveDeals);
                        else
                            search(searchString, category, "", descending, showInactiveDeals);
                    }else{
                        if (Objects.equals(searchString, "") || searchString == null)
                            search("", category, sortingCriteria, descending,showInactiveDeals);
                        else
                            search(searchString, category, sortingCriteria, descending,showInactiveDeals);
                    }
                }
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            search_srl.setRefreshing(false);
        }
    }

    protected boolean isConnectedToInternet() {
        ConnectivityManager conMgr = (ConnectivityManager) requireContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){
            Toast.makeText(requireContext(),"No internet connection.\nPlease check your connection status and try again.",Toast. LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
