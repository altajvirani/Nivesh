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
import com.phtlearning.nivesh.Founder.DatabaseHelper.ProfileHelper;
import com.phtlearning.nivesh.Founder.Fragments.Home.FounderHomeFragment;
import com.phtlearning.nivesh.Founder.Fragments.Logout.FounderLogoutFragment;
import com.phtlearning.nivesh.Founder.Fragments.Profile.FounderProfileFragment;
import com.phtlearning.nivesh.Founder.Fragments.Profile.UserProfileView;
import com.phtlearning.nivesh.Founder.Fragments.RaiseFund.CompanyNameFragment;
import com.phtlearning.nivesh.Founder.Fragments.Search.FounderSearchFragment;
import com.phtlearning.nivesh.R;

public class FounderHomeActivity extends AppCompatActivity {
    BubbleNavigationLinearView bubbleNavigationLinearView;
    FragmentTransaction fragmentTransaction;

    DatabaseReference founderReference, userTypeReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_founder_home);

        ProgressDialog progressDialog =  new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please Wait");

        bubbleNavigationLinearView = findViewById(R.id.bottom_navigation_view_linear);

        bubbleNavigationLinearView.setBadgeValue(0,null);
        bubbleNavigationLinearView.setBadgeValue(1,null);
        bubbleNavigationLinearView.setBadgeValue(2,null);
        bubbleNavigationLinearView.setBadgeValue(3,null);
        bubbleNavigationLinearView.setBadgeValue(4,null);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_view_tag, new FounderHomeFragment());
        fragmentTransaction.commit();

        userTypeReference = FirebaseDatabase.getInstance().getReference("UserType");
        founderReference = FirebaseDatabase.getInstance().getReference().child("Founder");

        String CurrentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();


        bubbleNavigationLinearView.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                switch (position)
                {
                    case 0:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container_view_tag, new FounderHomeFragment());
                        fragmentTransaction.commit();
                        break;

                    case 1:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container_view_tag, new FounderSearchFragment());
                        fragmentTransaction.commit();
                        break;

                    case 2:
                        userTypeReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String UserType = snapshot.child(CurrentUserUid).child("userType").getValue().toString();
                                if(UserType.equals("Founder"))
                                {
                                    founderReference.child(CurrentUserUid).addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if(snapshot.getChildrenCount() == 1)
                                            {
                                                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                                fragmentTransaction.replace(R.id.fragment_container_view_tag, new FounderProfileFragment());
                                                fragmentTransaction.commit();
                                            }
                                            else
                                            {
                                                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                                fragmentTransaction.replace(R.id.fragment_container_view_tag, new CompanyNameFragment());
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
                                    Toast.makeText(FounderHomeActivity.this, "User Not Found!", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                progressDialog.hide();
                                Toast.makeText(FounderHomeActivity.this, "Please Check Your Internet Connectivity!", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    case 3:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container_view_tag, new FounderLogoutFragment());
                        fragmentTransaction.commit();
                        break;

                    case 4:
                        userTypeReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String UserType = snapshot.child(CurrentUserUid).child("userType").getValue().toString();
                                if(UserType.equals("Founder"))
                                {
                                    founderReference.child(CurrentUserUid).addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if(snapshot.getChildrenCount() == 1)
                                            {
                                                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                                fragmentTransaction.replace(R.id.fragment_container_view_tag, new FounderProfileFragment());
                                                fragmentTransaction.commit();
                                            }
                                            else
                                            {
                                                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                                fragmentTransaction.replace(R.id.fragment_container_view_tag, new UserProfileView());
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
                                    Toast.makeText(FounderHomeActivity.this, "User Not Found!", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                progressDialog.hide();
                                Toast.makeText(FounderHomeActivity.this, "Please Check Your Internet Connectivity!", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;

                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container_view_tag);
        fragment.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}