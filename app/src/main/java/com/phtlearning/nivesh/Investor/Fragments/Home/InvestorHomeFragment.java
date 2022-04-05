package com.phtlearning.nivesh.Investor.Fragments.Home;

import static android.content.ContentValues.TAG;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.phtlearning.nivesh.R;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InvestorHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InvestorHomeFragment extends Fragment implements StartupSearchBottomSheet.StartupSearchBottomSheetListener{
    private RecyclerView recyclerView;
    private ImageButton filterStartups;
    private Query query;
    private SCardAdapter<SCard, SCardAdapter.ViewHolder> adapter;
    private SwipeRefreshLayout search_srl;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InvestorHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InvestorHomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InvestorHomeFragment newInstance(String param1, String param2) {
        InvestorHomeFragment fragment = new InvestorHomeFragment();
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
        View v = inflater.inflate(R.layout.fragment_investor_home, container, false);
        recyclerView = v.findViewById(R.id.search_recyclerview); //Note this line
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        search_srl = v.findViewById(R.id.search_swiperefresh);
        search_srl.setColorSchemeResources(R.color.blue, R.color.gradneon, R.color.orange, R.color.darkpink);
        search_srl.setOnRefreshListener(() -> new GetStartups().execute());

        filterStartups = v.findViewById(R.id.filterButton);
        filterStartups.setOnClickListener(view -> {
            StartupSearchBottomSheet bs = new StartupSearchBottomSheet();
            bs.show(this.getChildFragmentManager(), bs.getTag());

        });
//        recyclerView.setItemAnimator(null);
        setQuery("finance");
        fetchResult(query);

        return  v;

    }


    @Override
    public void onButtonClicked(String cat, String sort) {
        Log.i(TAG, "78641: onButtonClicked invoked in HomeFragment.");
        if(cat != null) {
            if (sort != null)
                setQueryByOrder(cat, sort);
            else
                setQuery(cat);
        }
        else{
            Toast.makeText(getActivity(), "No category and sorting criteria selected, so showing default result.",
                    Toast.LENGTH_LONG).show();
            setQuery("finance");
        }
        fetchResult(query);
    }

    protected void setQueryByOrder(String choice, String order){
        query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Category_wise").child(choice).orderByChild(order);
    }

    protected void setQuery(String choice){
        query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Category_wise").child(choice);
    }

    protected void fetchResult(Query query) {
        FirebaseRecyclerOptions<SCard> options =
                new FirebaseRecyclerOptions.Builder<SCard>()
                        .setQuery(query, snapshot -> new SCard(Objects.requireNonNull(snapshot.child("companyLogo").getValue()).toString(),
                                Objects.requireNonNull(snapshot.child("companyDescription").getValue()).toString(),
                                Objects.requireNonNull(snapshot.child("endDate").getValue()).toString(),
                                Integer.parseInt(Objects.requireNonNull(snapshot.child("minAmount").getValue()).toString()),
                                Objects.requireNonNull(snapshot.child("companyName").getValue()).toString(),
                                Integer.parseInt(Objects.requireNonNull(snapshot.child("totalInvestors").getValue()).toString()),
                                Integer.parseInt(Objects.requireNonNull(snapshot.child("raisedAmount").getValue()).toString()),
                                Integer.parseInt(Objects.requireNonNull(snapshot.child("totalTargetAmount").getValue()).toString())
                        ))
                        .build();
        adapter = new SCardAdapter<>(options);
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public class GetStartups extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                setQuery("finance");
                fetchResult(query);
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

}