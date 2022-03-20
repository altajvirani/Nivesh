package com.phtlearning.nivesh.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.phtlearning.nivesh.Founder.Fragments.Home.FounderHomeFragment;
import com.phtlearning.nivesh.Founder.Fragments.Logout.FounderLogoutFragment;
import com.phtlearning.nivesh.Founder.Fragments.Profile.FounderProfileFragment;
import com.phtlearning.nivesh.Founder.Fragments.RaiseFund.CompanyNameFragment;
import com.phtlearning.nivesh.Founder.Fragments.Search.FounderSearchFragment;
import com.phtlearning.nivesh.Investor.Fragments.Home.InvestorHomeFragment;
import com.phtlearning.nivesh.Investor.Fragments.InvestFund.InvestorInvestFundFragment;
import com.phtlearning.nivesh.Investor.Fragments.Logout.InvestorLogoutFragment;
import com.phtlearning.nivesh.Investor.Fragments.Profile.InvestorProfileFragment;
import com.phtlearning.nivesh.Investor.Fragments.Search.InvestorSearchFragment;
import com.phtlearning.nivesh.R;

public class InvestorHomeActivity extends AppCompatActivity {
    BubbleNavigationLinearView bubbleNavigationLinearView;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investor_home);
        bubbleNavigationLinearView = findViewById(R.id.bottom_navigation_view_linear);

        bubbleNavigationLinearView.setBadgeValue(0,null);
        bubbleNavigationLinearView.setBadgeValue(1,null);
        bubbleNavigationLinearView.setBadgeValue(2,null);
        bubbleNavigationLinearView.setBadgeValue(3,null);
        bubbleNavigationLinearView.setBadgeValue(4,null);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_investor_container_view_tag, new FounderHomeFragment());
        fragmentTransaction.commit();
        bubbleNavigationLinearView.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                switch (position)
                {
                    case 0:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_investor_container_view_tag, new InvestorHomeFragment());
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
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_investor_container_view_tag, new InvestorProfileFragment());
                        fragmentTransaction.commit();
                        break;

                }

            }
        });
    }
}