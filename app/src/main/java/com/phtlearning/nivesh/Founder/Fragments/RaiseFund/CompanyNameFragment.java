package com.phtlearning.nivesh.Founder.Fragments.RaiseFund;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.phtlearning.nivesh.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CompanyNameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CompanyNameFragment extends Fragment {

    EditText CompanyEditText;
    Button CompanyName;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CompanyNameFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RaiseFundFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CompanyNameFragment newInstance(String param1, String param2) {
        CompanyNameFragment fragment = new CompanyNameFragment();
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
        View view = inflater.inflate(R.layout.fragment_company_name, container, false);

        CompanyEditText = (EditText) view.findViewById(R.id.company_edit_txt);
        CompanyName = (Button)view.findViewById(R.id.company_next_btn);




        CompanyName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String CompanyName = CompanyEditText.getText().toString().trim().toLowerCase();

                if(TextUtils.isEmpty(CompanyName))
                {
                    Toast.makeText(getContext(), "This Field be Can't Empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Query query = FirebaseDatabase.getInstance().getReference().child("Startups").orderByChild("companyName").equalTo(CompanyName);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.getChildrenCount() > 0)
                            {
                                Toast.makeText(getContext(), "This Company Already Registered!", Toast.LENGTH_SHORT).show();
                                CompanyEditText.setText("");
                            }
                            else
                            {
                                FounderNameFragment obj = new FounderNameFragment();
                                Bundle args = new Bundle();
                                args.putString("CompanyName", CompanyName);
                                obj.setArguments(args);
                                getFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag, obj).commit();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(getContext(), "Please Check Your Internet Connectivity!", Toast.LENGTH_SHORT).show();
                        }
                    });


                }

            }
        });

        return view;


    }
}