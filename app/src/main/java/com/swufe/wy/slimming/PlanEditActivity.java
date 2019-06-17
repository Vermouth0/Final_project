package com.swufe.wy.slimming;

import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PlanEditActivity extends AppCompatActivity {

    private TextView text_title,text_content;
    private FrameLayout fragment_plan;
    private PlanEditActivity mContext;
    private ArrayList<PlanItem> planItems = null;
    private FragmentManager fManager = null;
    private long exitTime = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = PlanEditActivity.this;
        fManager = getSupportFragmentManager();
        bindViews();

        planItems = new ArrayList<PlanItem>();
        //把数据写入planItems中


        PlanEditFragment nlFragment = new PlanEditFragment(fManager, planItems);
        FragmentTransaction ft = fManager.beginTransaction();
        ft.replace(R.id.fragment_plan , nlFragment);
        ft.commit();
    }

    private void bindViews() {
        text_title = findViewById(R.id.plan_title);
        text_content= findViewById(R.id.plan_content);
        fragment_plan = findViewById(R.id.fragment_plan);
    }


    @Override
    public void onBackPressed() {
        if (fManager.getBackStackEntryCount() == 0) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                super.onBackPressed();
            }
        } else {
            fManager.popBackStack();
        }
    }
}
