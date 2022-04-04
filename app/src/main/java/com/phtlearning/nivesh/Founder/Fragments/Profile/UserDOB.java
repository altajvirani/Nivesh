package com.phtlearning.nivesh.Founder.Fragments.Profile;

import android.app.DatePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.phtlearning.nivesh.Founder.Fragments.RaiseFund.EndDateFragment;
import com.phtlearning.nivesh.R;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserDOB#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserDOB extends Fragment {
    EditText StartDateEditText;
    Button StartDateNxtBtn;
    final Calendar myCalendar= Calendar.getInstance();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserDOB() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserDOB.
     */
    // TODO: Rename and change types and number of parameters
    public static UserDOB newInstance(String param1, String param2) {
        UserDOB fragment = new UserDOB();
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
        View view = inflater.inflate(R.layout.fragment_user_d_o_b, container, false);
        StartDateEditText=(EditText) view.findViewById(R.id.start_date_edt_xml);
        StartDateNxtBtn = (Button)view.findViewById(R.id.start_next_btn);

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        StartDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(),date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        String UserName = getArguments().getString("UserName");
        String UserProfession = getArguments().getString("UserProfession");


        StartDateNxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserDOB = StartDateEditText.getText().toString();

                if(TextUtils.isEmpty(UserDOB))
                {
                    Toast.makeText(getContext(), "This Field be Can't Empty", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    UserGender obj = new UserGender();
                    Bundle args = new Bundle();
                    args.putString("UserName", UserName);
                    args.putString("UserProfession", UserProfession);
                    args.putString("UserDOB", UserDOB);

                    obj.setArguments(args);
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag, obj).commit();
                }
            }
        });

        return view;
    }
    private void updateLabel() {
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        StartDateEditText.setText(dateFormat.format(myCalendar.getTime()));

    }
}