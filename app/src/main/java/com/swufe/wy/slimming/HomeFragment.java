package com.swufe.wy.slimming;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frame_home, container);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView tv = getView().findViewById(R.id.homeTextView1);
        tv.setText("主页");

        ViewPager viewPager = getView().findViewById(R.id.viewpager_home);
        HomeAdapter pageAdapter = new HomeAdapter(getFragmentManager());
        viewPager.setAdapter(pageAdapter);

        TabLayout tabLayout = getView().findViewById(R.id.home_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }


}
