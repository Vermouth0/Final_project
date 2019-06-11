package com.swufe.wy.slimming;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PlanAdapter extends FragmentPagerAdapter {

    private String[] title = new String[]{"任务打卡","编辑任务 ","我的纪录"};
    public PlanAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new PlanCheckFragment();
        }else if(position==1){
            return new PlanEditFragment();
        }else{
            return new PlanShowFragment();
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

}
