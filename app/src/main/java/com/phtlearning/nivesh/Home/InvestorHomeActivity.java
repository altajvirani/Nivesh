package com.phtlearning.nivesh.Home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.phtlearning.nivesh.Founder.Fragments.Home.FounderHomeFragment;
import com.phtlearning.nivesh.Founder.Fragments.Profile.FounderProfileFragment;
import com.phtlearning.nivesh.Founder.Fragments.Profile.FounderUserProfileView;
import com.phtlearning.nivesh.Investor.Fragments.Home.Fragments.SearchFragment;
import com.phtlearning.nivesh.Investor.Fragments.InvestFund.InvestorInvestFundFragment;
import com.phtlearning.nivesh.Investor.Fragments.Logout.InvestorLogoutFragment;
import com.phtlearning.nivesh.Investor.Fragments.Profile.InvestorName;
import com.phtlearning.nivesh.Investor.Fragments.Profile.InvestorProfileFragment;
import com.phtlearning.nivesh.Investor.Fragments.Profile.InvestorProfileView;
import com.phtlearning.nivesh.Investor.Fragments.Search.InvestorSearchFragment;
import com.phtlearning.nivesh.R;

import java.util.Objects;

public class InvestorHomeActivity extends AppCompatActivity {
    BubbleNavigationLinearView bubbleNavigationLinearView;
    FragmentTransaction fragmentTransaction;
    DatabaseReference investorReference, userTypeReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investor_home);

        ProgressDialog progressDialog =  new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please Wait");

        userTypeReference = FirebaseDatabase.getInstance().getReference("UserType");
        investorReference = FirebaseDatabase.getInstance().getReference().child("Investor");

        String CurrentUserUid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        Toast.makeText(this, CurrentUserUid, Toast.LENGTH_SHORT).show();

        bubbleNavigationLinearView = findViewById(R.id.bottom_navigation_view_linear);
        bubbleNavigationLinearView.setBadgeValue(0,null);
        bubbleNavigationLinearView.setBadgeValue(1,null);
        bubbleNavigationLinearView.setBadgeValue(2,null);
        bubbleNavigationLinearView.setBadgeValue(3,null);
        bubbleNavigationLinearView.setBadgeValue(4,null);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_investor_container_view_tag, new SearchFragment());
        fragmentTransaction.commit();
        bubbleNavigationLinearView.setNavigationChangeListener((view, position) -> {
            switch (position)
            {
                case 0:
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_investor_container_view_tag, new SearchFragment());
                    fragmentTransaction.commit();
                    break;

                case 1:
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_investor_container_view_tag, new InvestorSearchFragment());
                    fragmentTransaction.commit();
                    break;

                case 2:
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_investor_container_view_tag, new InvestorInvestFundFragment());
                    fragmentTransaction.commit();
                    break;
                case 3:
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_investor_container_view_tag, new InvestorLogoutFragment());
                    fragmentTransaction.commit();
                    break;

                case 4:
                    userTypeReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String UserType = Objects.requireNonNull(snapshot.child(CurrentUserUid).child("userType").getValue()).toString();
                            if(UserType.equals("Investor"))
                            {
                                investorReference.child(CurrentUserUid).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if(snapshot.getChildrenCount() == 1)
                                        {
                                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                            fragmentTransaction.replace(R.id.fragment_investor_container_view_tag, new InvestorProfileFragment());
                                            fragmentTransaction.commit();
                                        }
                                        else
                                        {
                                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                            fragmentTransaction.replace(R.id.fragment_investor_container_view_tag, new InvestorProfileView());
                                            fragmentTransaction.commit();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }

                                });
                            }
                            else
                            {
                                progressDialog.hide();
                                Toast.makeText(InvestorHomeActivity.this, "User Not Found!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            progressDialog.hide();
                            Toast.makeText(InvestorHomeActivity.this, "Please Check Your Internet Connectivity!", Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;

            }

        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_investor_container_view_tag);
        Objects.requireNonNull(fragment).onActivityResult(requestCode, resultCode, data);
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        Intent a = new Intent(Intent.ACTION_MAIN);
//        a.addCategory(Intent.CATEGORY_HOME);
//        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(a);
//    }
}