package com.phtlearning.nivesh.Home;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.phtlearning.nivesh.Founder.Fragments.Home.FounderHomeFragment;
import com.phtlearning.nivesh.Founder.Fragments.Logout.FounderLogoutFragment;
import com.phtlearning.nivesh.Founder.Fragments.Profile.FounderProfileFragment;
import com.phtlearning.nivesh.Founder.Fragments.RaiseFund.CompanyNameFragment;
import com.phtlearning.nivesh.Founder.Fragments.Search.FounderSearchFragment;
import com.phtlearning.nivesh.R;

public class FounderHomeActivity extends AppCompatActivity {
    BubbleNavigationLinearView bubbleNavigationLinearView;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_founder_home);

        bubbleNavigationLinearView = findViewById(R.id.bottom_navigation_view_linear);

        bubbleNavigationLinearView.setBadgeValue(0,null);
        bubbleNavigationLinearView.setBadgeValue(1,null);
        bubbleNavigationLinearView.setBadgeValue(2,null);
        bubbleNavigationLinearView.setBadgeValue(3,null);
        bubbleNavigationLinearView.setBadgeValue(4,null);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_view_tag, new FounderHomeFragment());
        fragmentTransaction.commit();
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
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container_view_tag, new CompanyNameFragment());
                        fragmentTransaction.commit();
                        break;
                    case 3:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container_view_tag, new FounderLogoutFragment());
                        fragmentTransaction.commit();
                        break;

                    case 4:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container_view_tag, new FounderProfileFragment());
                        fragmentTransaction.commit();
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