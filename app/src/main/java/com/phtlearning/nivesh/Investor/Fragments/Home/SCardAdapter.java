package com.phtlearning.nivesh.Investor.Fragments.Home;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.phtlearning.nivesh.Investor.Fragments.Home.Fragments.ViewFragment;
import com.phtlearning.nivesh.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class SCardAdapter extends RecyclerView.Adapter<SCardAdapter.SCardViewHolder> {
    @Nullable private final ArrayList<SCard> sCardList;
    private final boolean showInactiveDeals;

    public SCardAdapter(@Nullable final ArrayList<SCard> sCardList, boolean showInactiveDeals) {
        this.sCardList = sCardList;
        this.showInactiveDeals = showInactiveDeals;
    }

    @NonNull
    @Override
    public SCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.startup_search_card, parent, false);
        return new SCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SCardViewHolder holder, final int position) {
        final SCard model = Objects.requireNonNull(sCardList).get(position);
        try {
            String sname = model.getsName();
            holder.startupcard.setTag(sname);
            holder.setDesc(model.getsDesc());
            holder.setImage(holder, model);
            holder.setMinam(String.valueOf(model.getsMinAmount()));
            holder.setName(sname);
            holder.setNoin(String.valueOf(model.getsNoOfInvestors()));
            holder.setPerRaised(String.format(Locale.US,"%.2f", getPerraised(model)));
            holder.setRaisingProgBar(getPerraised(model));
            holder.setCardVisibility(holder.getEndDate(model.getsEndDate()), model.getsStatus(), getPerraised(model), holder.sraisingprogbar.getContext());
        }
        catch (ParseException e) { e.printStackTrace(); }
    }

    protected class SCardViewHolder extends RecyclerView.ViewHolder {
        public final View startupcard;
        private final ImageButton dealclosedbtn;
        private final ImageView simg2;
        private final TextView sname, sdesc, senddate, sperraised, snoin, sminam;
        private final ProgressBar sraisingprogbar;

        protected SCardViewHolder(View itemView) {
            super(itemView);
            dealclosedbtn = itemView.findViewById(R.id.dealclosedbtn);
            simg2 = itemView.findViewById(R.id.simg2);
            sname = itemView.findViewById(R.id.sname);
            sdesc = itemView.findViewById(R.id.sdesc);
            senddate = itemView.findViewById(R.id.days);
            sperraised = itemView.findViewById(R.id.perraised);
            snoin = itemView.findViewById(R.id.noin);
            sminam = itemView.findViewById(R.id.minam);
            sraisingprogbar = itemView.findViewById(R.id.raisingprogbar);
            startupcard = itemView.findViewById(R.id.startupcard);
            startupcard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String sname = (String) view.getTag();
                    if(sname!=null) {
                        AppCompatActivity activity = (AppCompatActivity) view.getContext();
                        Fragment myFragment = new ViewFragment();

                        Bundle args = new Bundle();
                        args.putString("companyName", sname);
                        if(dealclosedbtn.getVisibility() == View.VISIBLE)
                            args.putString("ended", "true");
                        else
                            args.putString("ended", "false");
                        myFragment.setArguments(args);
                        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                        transaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                        transaction.replace(R.id.fragment_investor_container_view_tag, myFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                }
            });
        }

        protected void setImage(SCardViewHolder holder, SCard model){
            Glide.with(holder.simg2.getContext())
                    .load(model.getsCoverImage())
                    .fitCenter().
                    into(holder.simg2);
        }

        protected void setName(String string) {
            sname.setText(string);
        }

        protected void setDesc(String string) {
            sdesc.setText(string);
        }

        protected String getEndDate(String string) throws ParseException {
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

            senddate.setText(endDate);
            return endDate;
        }

        protected void setNoin(String string) {
            snoin.setText(string);
        }

        protected void setMinam(String string) {
            sminam.setText(string);
        }

        protected void setPerRaised(String string) {
            sperraised.setText(string);
        }

        protected void setRaisingProgBar(float f) {
            setProgressMax(sraisingprogbar);
            sraisingprogbar.setProgress((int) f);
            setProgressAnimate(sraisingprogbar, (int) f);
        }

        protected void setCardVisibility(@NonNull String endDate, @NonNull String status, float percent, @Nullable Context context){
            if(status.equals("running")){
                if(endDate.equals("Ended")){
                    if(showInactiveDeals){
                        senddate.setText(endDate);
                        dealclosedbtn.setVisibility(View.VISIBLE);
                        Resources r = startupcard.getResources();
                        int px = (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP,
                                30,
                                r.getDisplayMetrics()
                        );
                        RecyclerView.LayoutParams rvparams = new  RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        rvparams.setMargins(px, 0, px, px);
                        itemView.setLayoutParams(rvparams);
                    }else
                        itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
                }else if(percent>=100.00) {
                    if (showInactiveDeals) {
                        endDate = "Ended";
                        senddate.setText(endDate);
                        dealclosedbtn.setVisibility(View.VISIBLE);
                        Resources r = startupcard.getResources();
                        int px = (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP,
                                30,
                                r.getDisplayMetrics()
                        );
                        RecyclerView.LayoutParams rvparams = new  RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        rvparams.setMargins(px, 0, px, px);
                        itemView.setLayoutParams(rvparams);
                    }else
                        itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
                }else{
                    if(dealclosedbtn.getVisibility() == View.VISIBLE)
                        dealclosedbtn.setVisibility(View.GONE);
                    Resources r = startupcard.getResources();
                    int px = (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP,
                            30,
                            r.getDisplayMetrics()
                    );
                    RecyclerView.LayoutParams rvparams = new  RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    rvparams.setMargins(px, 0, px, px);
                    itemView.setLayoutParams(rvparams);
                }
            }else if(status.equals("created") || status.equals("paused"))
                    itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
            else{
                if(showInactiveDeals){
                    endDate = "Ended";
                    senddate.setText(endDate);
                    dealclosedbtn.setVisibility(View.VISIBLE);
                    Resources r = startupcard.getResources();
                    int px = (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP,
                            30,
                            r.getDisplayMetrics()
                    );
                    RecyclerView.LayoutParams rvparams = new  RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    rvparams.setMargins(px, 0, px, px);
                    itemView.setLayoutParams(rvparams);
                }
                else
                    itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
            }
        }
    }

    @Override
    public int getItemCount() {
        return sCardList.size();
    }

    protected float getPerraised(SCard model) {
        float raisedAmount = (float) model.getsRaisedAmount();
        float target = (float) model.getsTarget();
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
}

//                        sraisingprogbar.setProgressTintList(ColorStateList.valueOf(Color.rgb(100, 100, 100)));
//                        sraisingprogbar.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(context), R.drawable.progbg));
