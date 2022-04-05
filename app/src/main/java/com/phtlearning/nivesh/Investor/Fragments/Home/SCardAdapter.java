package com.phtlearning.nivesh.Investor.Fragments.Home;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.phtlearning.nivesh.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class SCardAdapter<M extends SCard, V extends SCardAdapter.ViewHolder> extends FirebaseRecyclerAdapter<SCard, V> {

    FirebaseRecyclerOptions<SCard> options;

    public SCardAdapter(@Nullable FirebaseRecyclerOptions<SCard> options) {
        super(options);
        this.options = options;
    }

    @Override
    protected void onBindViewHolder(V holder, @SuppressLint("RecyclerView") final int position, SCard model) {
        holder.setName(model.getsName());
        holder.setDesc(model.getsDesc());
        holder.setMinam(String.valueOf(model.getsMinAmount()));
        holder.setNoin(String.valueOf(model.getsNoOfInvestors()));
        holder.setPerRaised(String.format(Locale.US,"%.2f", getPerraised(model)));
        holder.setRaisingProgBar(getPerraised(model));
        try {
            holder.setEndDate(model.getsEndDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.setSimg2(holder, model);
    }

    @Override
    public int getItemCount() {
        return options.getSnapshots().size();
    }

    @Override
    public V onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.startup_search_card, parent, false);

        return (V) new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView simg2;
        TextView sname, sdesc, senddate, sperraised, snoin, sminam;
        ProgressBar sraisingprogbar;
        public ViewHolder(View itemView) {
            super(itemView);
            simg2 = itemView.findViewById(R.id.simg2);
            sname = itemView.findViewById(R.id.sname);
            sdesc = itemView.findViewById(R.id.sdesc);
            senddate = itemView.findViewById(R.id.days);
            sperraised = itemView.findViewById(R.id.perraised);
            snoin = itemView.findViewById(R.id.noin);
            sminam = itemView.findViewById(R.id.minam);
            sraisingprogbar = itemView.findViewById(R.id.raisingprogbar);
        }

        public void setSimg2(V holder, SCard model){
            Glide.with(holder.simg2.getContext())
                    .load(model.getsCoverImage())
                    .fitCenter().
                    into(holder.simg2);
        }

        public void setName(String string) {
            sname.setText(string);
        }

        public void setDesc(String string) {
            sdesc.setText(string);
        }

        public void setEndDate(String string) throws ParseException {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat obj = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

            Date d = obj.parse(string);
            Log.i("sEndDate1", String.valueOf(d));

            Date cur = (Date) (Calendar.getInstance().getTime());
            Log.i("sEndDate2", String.valueOf(cur));

            String endDate;
            long time_difference = Objects.requireNonNull(d).getTime() - Objects.requireNonNull(cur).getTime();

            long days_difference = (time_difference / (1000*60*60*24)) % 365;

            long hours_difference = (time_difference / (1000*60*60)) % 24;

            long minutes_difference = (time_difference / (1000*60)) % 60;

            long seconds_difference = (time_difference / 1000)% 60;

            if(days_difference<=0){
                if(hours_difference<=0){
                    if(minutes_difference<=0){
                        if(seconds_difference<=0) {
                            endDate = "Ended";
                            itemView.findViewById(R.id.startupcard).setVisibility(View.GONE);
                        }
                        else
                            endDate = seconds_difference + "s";
                    }
                    else
                        endDate = minutes_difference + "m " + seconds_difference + "s";
                }
                else {
                    endDate = hours_difference + "h " + minutes_difference + "m";
                }
            }
            else
                endDate = days_difference + "d " + hours_difference + "h";
            senddate.setText(endDate);
        }

        public void setNoin(String string) {
            snoin.setText(string);
        }

        public void setMinam(String string) {
            sminam.setText(string);
        }

        public void setPerRaised(String string) {
            sperraised.setText(string);
        }

        public void setRaisingProgBar(float f) {
            if(f>=100.00)
                itemView.findViewById(R.id.startupcard).setVisibility(View.GONE);
            else {
                setProgressMax(sraisingprogbar, 100);
                sraisingprogbar.setProgress((int) f);
                setProgressAnimate(sraisingprogbar, (int) f);
            }
        }
    }

    protected float getPerraised(SCard model) {
        float raisedAmount = (float) model.getsRaisedAmount();
        float target = (float) model.getsTarget();
        return (raisedAmount/target) * 100;
    }

    protected void setProgressMax(ProgressBar pb, int max) {
        pb.setMax(max * 100);
    }

    protected void setProgressAnimate(ProgressBar pb, int progressTo) {
        ObjectAnimator anim = ObjectAnimator.ofInt(pb, "progress", pb.getProgress(), progressTo * 100);
        anim.setDuration(500);
        anim.setAutoCancel(true);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.start();
    }
}