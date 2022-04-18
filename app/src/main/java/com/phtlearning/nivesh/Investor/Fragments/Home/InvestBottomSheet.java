package com.phtlearning.nivesh.Investor.Fragments.Home;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.phtlearning.nivesh.R;

import java.util.Objects;

public class InvestBottomSheet extends BottomSheetDialogFragment{
    private InvestBottomSheetListener ibsListener;
    public static String TAG = "InvestBottomSheet";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  @Nullable ViewGroup container, @Nullable  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.invest_bottomsheet, container, false);

        EditText ed = v.findViewById(R.id.getamt);
        Button inv = v.findViewById(R.id.show);
        TextView eq = v.findViewById(R.id.equity);

        float equity;
        double maxAm;
        String raisedAm;
        String totalAm;
        final String[] minAm = new String[1];

        Bundle bundle = getArguments();
        equity = Float.parseFloat(Objects.requireNonNull(bundle).getString("equity"));
        eq.setText(String.valueOf(equity));

        raisedAm = bundle.getString("raisedAm").replaceAll("[₹,]", "");
        totalAm = bundle.getString("totalAm").replaceAll("[₹,]", "");
        minAm[0] = bundle.getString("minAm");
        double eqrem = ((Double.parseDouble(raisedAm)) / Double.parseDouble(totalAm) * equity) / 10;
        maxAm = eqrem * Double.parseDouble(totalAm);

        inv.setOnClickListener(view -> {
            if(ed.getText().toString().equals("") || ed.getText().toString().equals("0"))
                Toast.makeText(getApplicationContext(), "Please enter valid amount.", Toast.LENGTH_LONG).show();
            else {
                float eqtoinvest = Float.parseFloat(ed.getText().toString()) * Float.parseFloat(String.valueOf(equity));

                if (minAm[0].contains("K"))
                    minAm[0] = String.valueOf(Double.parseDouble(minAm[0].replace("K", "")) * 1000);
                else if (minAm[0].contains("L"))
                    minAm[0] = String.valueOf(Double.parseDouble(minAm[0].replace("L", "")) * 100000);
                else if (minAm[0].contains("Cr"))
                    minAm[0] = String.valueOf(Double.parseDouble(minAm[0].replace("Cr", "")) * 10000000);

                if(Float.parseFloat(minAm[0])< Float.parseFloat(ed.getText().toString())) {
                    if (maxAm < Double.parseDouble(ed.getText().toString()) || eqrem < eqtoinvest)
                        Toast.makeText(getApplicationContext(), "Please select amount less than ₹" + maxAm + " (maximum amount for available equity of " + eqrem + "%).", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getApplicationContext(), "Please select amount more than minimum investable amount: ₹" + minAm[0], Toast.LENGTH_LONG).show();
                }else{
                    ibsListener.onButtonClicked(Integer.parseInt(ed.getText().toString()));
                }
            }
        });


        return v;
    }
    public interface InvestBottomSheetListener{
        void onButtonClicked(int amount);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{ ibsListener = (InvestBottomSheetListener) getParentFragment(); }
        catch(ClassCastException e){ throw new ClassCastException(context + " must implement StartupSearchBottomSheetListener."); }
    }
}