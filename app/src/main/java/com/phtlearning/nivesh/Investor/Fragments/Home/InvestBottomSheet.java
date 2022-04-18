package com.phtlearning.nivesh.Investor.Fragments.Home;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.phtlearning.nivesh.R;
import java.util.Locale;
import java.util.Objects;

public class InvestBottomSheet extends BottomSheetDialogFragment{
    private InvestBottomSheetListener ibsListener;
    public static String TAG = "InvestBottomSheet";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  @Nullable ViewGroup container, @Nullable  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.invest_bottomsheet, container, false);

        Button inv = v.findViewById(R.id.show);
        TextView psym = v.findViewById(R.id.psym);
        TextView availableequitytv = v.findViewById(R.id.avaialbleequity);
        TextView equityforamttv = v.findViewById(R.id.equityforamount);
        TextView totalequityforinvtv = v.findViewById(R.id.totaleqforinv);

        Bundle bundle = getArguments();
        String name = Objects.requireNonNull(bundle).getString("cname");
        float totalequityforinv = Float.parseFloat(Objects.requireNonNull(bundle).getString("equity"));
        float minAm = /*8000;*/ Float.parseFloat(Objects.requireNonNull(bundle).getString("minAm"));
        float raisedAm = /*50000;*/ Float.parseFloat(Objects.requireNonNull(bundle).getString("raisedAm"));
        float totalAm = /*100000;*/ Float.parseFloat(Objects.requireNonNull(bundle).getString("totalAm"));
        float availableequity = (float) ((((raisedAm/totalAm) * 100.00) * totalequityforinv)/ 100.00);

        availableequitytv.setText(String.format(Locale.US, "%.3f", availableequity));
        totalequityforinvtv.setText(String.format(Locale.US, "%.3f", totalequityforinv));

        EditText getamted = v.findViewById(R.id.getamt);
        getamted.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                float getinp;
                if(!getamted.getText().toString().equals(""))
                    getinp = Float.parseFloat(getamted.getText().toString());
                else
                    getinp = 0;
                float equityforamt = (float) ((((getinp/totalAm) * 100.00) * totalequityforinv)/ 100.00);
                if(equityforamt<availableequity){
                    equityforamttv.setText(String.format(Locale.US, "%.3f", equityforamt));
                    equityforamttv.setTextColor(getResources().getColor(R.color.darkmutedblue));
                    psym.setTextColor(getResources().getColor(R.color.darkmutedblue));
                }else {
                    equityforamttv.setText(String.format(Locale.US, "%.3f", availableequity));
                    equityforamttv.setTextColor(getResources().getColor(R.color.mutedred));
                    psym.setTextColor(getResources().getColor(R.color.mutedred));
                }
            }
        });

        inv.setOnClickListener(view -> {
            float equityforamt = Float.parseFloat((equityforamttv).getText().toString());
            float getamt;
            if(!getamted.getText().toString().equals(""))
                getamt = Float.parseFloat((getamted).getText().toString());
            else
                getamt = 0;

            Log.i("InvestBottomSheet values: ", "name: " + name + ", getamt: " + getamt + ", totalequityforinv: " + totalequityforinv + ", minAm: " + minAm + ", raisedAm: " + raisedAm + ", totalAm: " + totalAm + ", equityforamt: " + equityforamt + ", availableequity: " + availableequity);
            if(getamt == 0) {
                Toast.makeText(getApplicationContext(), "Please enter valid amount.", Toast.LENGTH_LONG).show();
            }else {
                if(getamt >=minAm){
                    if(raisedAm+ getamt <= totalAm){
                        if(raisedAm+ getamt == totalAm)
                            ibsListener.onButtonClicked(name, String.valueOf(getamt) , String.valueOf(availableequity), String.valueOf(raisedAm+getamt), "", true);
                        else
                            ibsListener.onButtonClicked(name, String.valueOf(getamt) , String.valueOf(equityforamt), String.valueOf(raisedAm+getamt), "", false);
                    }else if(raisedAm+ getamt > totalAm){
                        AlertDialog.Builder alert = new AlertDialog.Builder(requireContext());
                        alert.setTitle("Alert");
                        alert.setMessage("The amount you want to invest is more than the price of available equity, are you sure you want to invest for the amount?");
                        alert.setPositiveButton("Yes", (dialog, whichButton) -> {
                            ibsListener.onButtonClicked(name, String.valueOf(getamt), availableequitytv.getText().toString(), String.valueOf(raisedAm+getamt), "", true);
                        });
                        alert.setNegativeButton("No", (dialog, whichButton) -> {
                            getamted.setText("");
                            equityforamttv.setText("0.000");
                            dialog.dismiss();
                        });
                        alert.show();
                    }
                }else
                    Toast.makeText(getApplicationContext(), "Please enter amount more than minimum amount: ₹" + String.format(Locale.US, "%d", (long) minAm) , Toast.LENGTH_LONG).show();
            }
        });


        return v;
    }
    public interface InvestBottomSheetListener{
        void onButtonClicked(@Nullable String cname, @Nullable String amount, @Nullable String equity, @Nullable String raisedAmount, @Nullable String imageurl, boolean completedRaising);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{ ibsListener = (InvestBottomSheetListener) getParentFragment(); }
        catch(ClassCastException e){ throw new ClassCastException(context + " must implement StartupSearchBottomSheetListener."); }
    }
}