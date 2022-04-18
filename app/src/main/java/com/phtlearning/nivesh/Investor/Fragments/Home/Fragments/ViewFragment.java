package com.phtlearning.nivesh.Investor.Fragments.Home.Fragments;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.phtlearning.nivesh.Investor.Fragments.Home.InvestBottomSheet;
import com.phtlearning.nivesh.R;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class ViewFragment extends Fragment implements InvestBottomSheet.InvestBottomSheetListener {
    private SwipeRefreshLayout view_srl;
    private ViewPager viewPager;
    private String cname;
    private View view;
    private boolean ended;
    DatabaseReference startupReference, investorReference, userTypeReference;
    public ViewFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_view, container, false);
        startupReference = FirebaseDatabase.getInstance().getReference().child("Startups");
        investorReference = FirebaseDatabase.getInstance().getReference().child("Investor");
        userTypeReference = FirebaseDatabase.getInstance().getReference("UserType");
        TabLayout tabLayout = v.findViewById(R.id.viewtabbar);
        Button invest = v.findViewById(R.id.invest);
        viewPager = v.findViewById(R.id.viewpager);
        view_srl = v.findViewById(R.id.view_swiperefresh);

        view_srl.setColorSchemeResources(R.color.blue, R.color.gradneon, R.color.orange, R.color.darkpink);
        view_srl.setOnRefreshListener(() -> new RefreshViewStartup().execute(new FetchOnExecute()));

        tabLayout.setupWithViewPager(viewPager);

        invest.setOnClickListener(view -> {
            InvestBottomSheet is = new InvestBottomSheet();
            if(getChildFragmentManager().findFragmentByTag(InvestBottomSheet.TAG) == null) {
                TextView nametv = v.findViewById(R.id.cname);
                TextView eqtv = v.findViewById(R.id.invisibletextviewforequity);
                TextView minAmtv = v.findViewById(R.id.invisibletextviewforminam);
                TextView raisedAmtv = v.findViewById(R.id.invisibletextviewforraisedam);
                TextView totalAmtv = v.findViewById(R.id.invisibletextviewfortotalam);

                Bundle b = new Bundle();
                String name = nametv.getText().toString().toLowerCase();
                name = name.replace(".", "");
                name = name.replace("'", "");
                name = name.replace("’", "");
                b.putString("cname", name);
                b.putString("equity", eqtv.getText().toString());
                b.putString("minAm", minAmtv.getText().toString());
                b.putString("raisedAm", raisedAmtv.getText().toString());
                b.putString("totalAm", totalAmtv.getText().toString());

                is.setArguments(b);
                is.show(getChildFragmentManager(), InvestBottomSheet.TAG);
            }
        });

        if(isConnectedToInternet()){
            try {
                cname = requireArguments().getString("companyName");
                ended = requireArguments().getString("ended").equals("true");
                view = v;
                getDataforSelectedStartup(view, cname, ended);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return v;
    }

    private void getDataforSelectedStartup(View v, String cname, boolean b) throws ParseException {
        final boolean[] foundFlag = {false};

        FirebaseDatabase.getInstance().getReference().child("Category_wise").addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    if(!foundFlag[0]){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            if(!foundFlag[0]){
                                if (snapshot.exists()){
                                    for (DataSnapshot shot : snapshot.getChildren()){
                                        if(!foundFlag[0]){
                                            if (shot.exists()){
                                                if(Objects.requireNonNull(shot.child("companyName").getValue()).toString().equals(cname)){
                                                    ViewPageHolder vph = new ViewPageHolder(v);
                                                    try {
                                                        int minAm = Integer.parseInt(Objects.requireNonNull(String.valueOf(shot.child("minAmount").getValue())));
                                                        Format format = com.ibm.icu.text.NumberFormat.getCurrencyInstance(new Locale("en", "in"));
                                                        String raisedAmount = format.format(Integer.valueOf(Objects.requireNonNull(String.valueOf(shot.child("raisedAmount").getValue()))));
                                                        String totalTargetAmount = format.format(Integer.valueOf(Objects.requireNonNull(String.valueOf(shot.child("totalTargetAmount").getValue()))));
                                                        DecimalFormat form = new DecimalFormat("0.##");

                                                        vph.setCName(cname);
                                                        vph.setCoverImg(Objects.requireNonNull(shot.child("companyLogo").getValue()).toString());
                                                        vph.setRaisedam(raisedAmount);
                                                        if((vph.setTimeleft(Objects.requireNonNull(shot.child("endDate").getValue()).toString())).equals("Ended") || b){
                                                            vph.investbtn.setEnabled(false);
                                                            vph.investbtn.setClickable(false);
                                                            vph.investbtn.setOnClickListener(null);
                                                        }
                                                        if(minAm>999){
                                                            if(minAm>99999){
                                                                if(minAm>9999999) {
                                                                    vph.setMinam(form.format(Float.valueOf(String.format(Locale.US,"%.2f", ((float) minAm) / 10000000))) + "Cr");
                                                                }
                                                                else {
                                                                    vph.setMinam(form.format(Float.valueOf(String.format(Locale.US,"%.3f", ((float) minAm) /100000))) + "L");
                                                                }
                                                            }else {
                                                                vph.setMinam(form.format(Float.valueOf(String.format(Locale.US,"%.2f", ((float) minAm) /1000))) + "K");
                                                            }
                                                        }else {
                                                            vph.setMinam(String.valueOf(minAm));
                                                        }

                                                        vph.setRaisingProgBar(getPerraised(Float.parseFloat(Objects.requireNonNull(shot.child("raisedAmount").getValue()).toString()), Float.parseFloat(Objects.requireNonNull(shot.child("totalTargetAmount").getValue()).toString())));
                                                        vph.setNoin(Objects.requireNonNull(shot.child("totalInvestors").getValue()).toString());
                                                        vph.setTotalam(totalTargetAmount);
                                                        setTabLayoutAndViewPager(Objects.requireNonNull(shot.child("companyDescription").getValue()).toString(), Objects.requireNonNull(shot.child("pitchLink").getValue()).toString(), Objects.requireNonNull(shot.child("problemStatement").getValue()).toString(), Objects.requireNonNull(shot.child("solutionStatement").getValue()).toString(), Objects.requireNonNull(shot.child("equity").getValue()).toString(), Objects.requireNonNull(shot.child("webSiteLink").getValue()).toString());

                                                        vph.setInvisibleTextViewForEquity(Objects.requireNonNull(shot.child("equity").getValue()).toString());
                                                        vph.setInvisibleTextViewForMinamount(Objects.requireNonNull(shot.child("minAmount").getValue()).toString());
                                                        vph.setInvisibleTextViewForRaisedAmount(Objects.requireNonNull(shot.child("raisedAmount").getValue()).toString());
                                                        vph.setInvisibleTextViewForTotalAmount(Objects.requireNonNull(shot.child("totalTargetAmount").getValue()).toString());

                                                    } catch (ParseException e) {
                                                        e.printStackTrace();
                                                    }
                                                    foundFlag[0] = true;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


    protected class ViewPageHolder{
        private final ImageView coverimg;
        private final TextView cname, raisedam, totalam, per, timeleft, noin, minam, eqtv, minAmtv, raisedAmtv, totalAmtv;
        private final ProgressBar raisingprogbar;
        private final Button investbtn;

        protected ViewPageHolder(View itemView) {
            coverimg = itemView.findViewById(R.id.coverimg);
            cname = itemView.findViewById(R.id.cname);
            raisedam = itemView.findViewById(R.id.amtraised);
            totalam = itemView.findViewById(R.id.totalraised);
            timeleft = itemView.findViewById(R.id.timeleft);
            per = itemView.findViewById(R.id.per);
            noin = itemView.findViewById(R.id.noin);
            minam = itemView.findViewById(R.id.minam);
            raisingprogbar = itemView.findViewById(R.id.raisingprogbar);
            investbtn = itemView.findViewById(R.id.invest);
            eqtv = itemView.findViewById(R.id.invisibletextviewforequity);
            minAmtv = itemView.findViewById(R.id.invisibletextviewforminam);
            raisedAmtv = itemView.findViewById(R.id.invisibletextviewforraisedam);
            totalAmtv = itemView.findViewById(R.id.invisibletextviewfortotalam);
        }

        protected void setCoverImg(String string){
            Glide.with(coverimg.getContext())
                    .load(string)
                    .fitCenter().
                    into(coverimg);
        }

        protected void setCName(String string) {
            cname.setText(string);
        }

        protected void setRaisedam(String string) {
            raisedam.setText(string);
        }

        protected void setTotalam(String string) {
            totalam.setText(string);
        }

        protected String setTimeleft(String string) throws ParseException {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat obj = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

            Date d = obj.parse(string);
            Date cur = Calendar.getInstance().getTime();
            String endDate;

            long time_difference = Objects.requireNonNull(d).getTime() - Objects.requireNonNull(cur).getTime();
            long days_difference = (time_difference / (1000*60*60*24)) % 365;
            long hours_difference = (time_difference / (1000*60*60)) % 24;
            long minutes_difference = (time_difference / (1000*60)) % 60;
            long seconds_difference = (time_difference / 1000)% 60;

            if(days_difference<=0) {
                if (hours_difference <= 0) {
                    if (minutes_difference <= 0) {
                        if (seconds_difference <= 0)
                            endDate = "Ended";
                        else
                            endDate = seconds_difference + "s";
                    } else
                        endDate = minutes_difference + "m " + seconds_difference + "s";
                }else
                    endDate = hours_difference + "h " + minutes_difference + "m";
            }else
                endDate = days_difference + "d " + hours_difference + "h";

            timeleft.setText(endDate);
            return endDate;
        }

        protected void setPer(String string){
            per.setText(string);
        }

        protected void setNoin(String string) {
            noin.setText(string);
        }

        protected void setMinam(String string) {
            minam.setText(string);
        }

        protected void setRaisingProgBar(float f) {
            setProgressMax(raisingprogbar);
            raisingprogbar.setProgress((int) f);
            setProgressAnimate(raisingprogbar, (int) f);
            setPer(String.format(Locale.US,"%.2f", f));
        }

        protected void setInvisibleTextViewForEquity(String string) {
            eqtv.setText(string);
            eqtv.setVisibility(View.INVISIBLE);
        }

        public void setInvisibleTextViewForMinamount(String string) {
            minAmtv.setText(string);
            minAmtv.setVisibility(View.INVISIBLE);
        }

        public void setInvisibleTextViewForRaisedAmount(String string) {
            raisedAmtv.setText(string);
            raisedAmtv.setVisibility(View.INVISIBLE);
        }

        public void setInvisibleTextViewForTotalAmount(String string) {
            totalAmtv.setText(string);
            totalAmtv.setVisibility(View.INVISIBLE);
        }
    }

    protected float getPerraised(float raisedAmount, float target) {
        return (raisedAmount/target) * 100;
    }

    protected void setProgressMax(ProgressBar pb) {
        pb.setMax(100 * 100);
    }

    protected void setProgressAnimate(ProgressBar pb, int progressTo) {
        ObjectAnimator anim = ObjectAnimator.ofInt(pb, "progress", pb.getProgress(), progressTo * 100);
        anim.setDuration(500);
        anim.setAutoCancel(true);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.start();
    }

    private void setTabLayoutAndViewPager(String desc, String pitch, String problem, String solution, String equity, String websiteLink) {
        VPAdapter vpAdapter = new VPAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragmentToFal(new DescriptionFragment(desc), "Description");
        vpAdapter.addFragmentToFal(new PitchFragment(pitch), "Pitch");
        vpAdapter.addFragmentToFal(new ProblemStatementFragment(problem), "Problem Statement");
        vpAdapter.addFragmentToFal(new SolutionFragment(solution), "Solution");
        vpAdapter.addFragmentToFal(new BusinessModelFragment(), "Business Model");
        vpAdapter.addFragmentToFal(new CustomersFragment(), "Customer");
        vpAdapter.addFragmentToFal(new MarketValueFragment(), "Market Value");
        vpAdapter.addFragmentToFal(new RevenueDistributionFragment(equity), "Revenue Distribution");
        vpAdapter.addFragmentToFal(new FAQFragment(), "FAQs");
        vpAdapter.addFragmentToFal(new AboutTheTeamFragment(websiteLink), "About the Team");
        viewPager.setAdapter(vpAdapter);
    }

    @Override
    public void onButtonClicked(String name, String amount, String equity, String raisedAmount, String imageurl, boolean completedRaising) {
        Log.i("onButtonClicked in ViewFragment: ", name + " " + equity + " " + raisedAmount + " " + imageurl + " " + completedRaising);

        ProgressDialog progressDialog =  new ProgressDialog(getContext());
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please Wait");

        String CurrentUserUid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        userTypeReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String UserType = Objects.requireNonNull(snapshot.child(CurrentUserUid).child("userType").getValue()).toString();
                if(UserType.equals("Investor")){
                    InvestedDB investedDB = new InvestedDB("Amazon","10000","0.1","");
                    investorReference.child(CurrentUserUid).child("Startups").child("Amazon").setValue(investedDB);
                    Toast.makeText(getContext(), "Investment successful.", Toast.LENGTH_SHORT).show();
                    progressDialog.hide();
                }
                else{
                    progressDialog.hide();
                    Toast.makeText(getContext(), "User Not Found!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.hide();
                Toast.makeText(getContext(), "Please check your internet connectivity.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static class FetchOnExecute {
        FetchOnExecute() {}
    }

    private class RefreshViewStartup extends AsyncTask<FetchOnExecute, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(FetchOnExecute... params) {
            try {
                if(isConnectedToInternet())
                    getDataforSelectedStartup(view, cname, ended);
                Thread.sleep(2000);
            } catch (InterruptedException | ParseException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            view_srl.setRefreshing(false);
        }
    }

    protected boolean isConnectedToInternet() {
        ConnectivityManager conMgr = (ConnectivityManager) requireContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){
            Toast.makeText(requireContext(),"No internet connection.\nPlease check your connection status and try again.",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}



//        Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
//        ProgressDialog progressDialog =  new ProgressDialog(getContext());
//        progressDialog.setTitle("Loading...");
//        progressDialog.setMessage("Please Wait");
//        startupReference = FirebaseDatabase.getInstance().getReference().child("Startups");
//        investorReference = FirebaseDatabase.getInstance().getReference().child("Investor");
//        userTypeReference = FirebaseDatabase.getInstance().getReference("UserType");
//        String CurrentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
//        userTypeReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String UserType = snapshot.child(CurrentUserUid).child("userType").getValue().toString();
//                if(UserType.equals("Investor"))
//                {
//                    InvestedDB investedDB = new InvestedDB(cname,amount,equity);
//                    investorReference.child(CurrentUserUid).child("Startups").child(cname).setValue(investedDB);
//                    Toast.makeText(getContext(), "Successfully!", Toast.LENGTH_SHORT).show();
//                    progressDialog.hide();
//                }
//                else
//                {
//                    progressDialog.hide();
//                    Toast.makeText(getContext(), "User Not Found!", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                progressDialog.hide();
//                Toast.makeText(getContext(), "Please Check Your Internet Connectivity!", Toast.LENGTH_SHORT).show();
//            }
//        });