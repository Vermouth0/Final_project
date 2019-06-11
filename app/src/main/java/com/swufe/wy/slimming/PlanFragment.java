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
public class PlanFragment extends Fragment {


    public PlanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frame_plan, container);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView tv = getView().findViewById(R.id.planTextView1);
        tv.setText("计划");

        ViewPager viewPager = getView().findViewById(R.id.viewpager_plan);
        PlanAdapter pageAdapter = new PlanAdapter(getFragmentManager());
        viewPager.setAdapter(pageAdapter);

        TabLayout tabLayout = getView().findViewById(R.id.plan_tabs);
        tabLayout.setupWithViewPager(viewPager);

    }


}
