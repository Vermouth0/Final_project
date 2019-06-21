package com.swufe.wy.slimming;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class FrameActivity extends FragmentActivity {

    private Fragment mFragments[];
    private RadioGroup radioGroup;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private RadioButton rbtHome, rbtPlan, rbtHot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_frame);


        //初始化对象
        mFragments = new Fragment[3];  //三个fragment
        fragmentManager = getSupportFragmentManager();
        mFragments[0] = fragmentManager.findFragmentById(R.id.fragment_home);
        mFragments[1] = fragmentManager.findFragmentById(R.id.fragment_plan);
        mFragments[2] = fragmentManager.findFragmentById(R.id.fragment_hot);
        //控制fragment的隐藏和显示
        fragmentTransaction = fragmentManager.beginTransaction().hide(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]);
        fragmentTransaction.show(mFragments[0]).commit();

        //获取三个元素
        rbtHome= findViewById(R.id.radioHome);
        rbtPlan = (RadioButton)findViewById(R.id.radioPlan);
        rbtHot = (RadioButton)findViewById(R.id.radioHot);
        rbtHome.setBackgroundResource(R.drawable.shape);


        //设置按钮切换
        radioGroup = (RadioGroup) findViewById(R.id.bottomGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.i("radioGroup", "checkId=" + checkedId);
                fragmentTransaction = fragmentManager.beginTransaction().hide(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]);

                //修改背景颜色
                rbtHome.setBackgroundResource(R.drawable.shape1);
                rbtPlan.setBackgroundResource(R.drawable.shape1);
                rbtHot.setBackgroundResource(R.drawable.shape1);

                switch (checkedId) {
                    case R.id.radioHome:
                        fragmentTransaction.show(mFragments[0]).commit();
                        rbtHome.setBackgroundResource(R.drawable.shape);
                        break;
                    case R.id.radioPlan:
                        fragmentTransaction.show(mFragments[1]).commit();
                        rbtPlan.setBackgroundResource(R.drawable.shape);
                        break;
                    case R.id.radioHot:
                        fragmentTransaction.show(mFragments[2]).commit();
                        rbtHot.setBackgroundResource(R.drawable.shape);
                        break;
                    default:
                        break;
                }
            }
        });
    }

}
