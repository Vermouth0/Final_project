package com.swufe.wy.slimming;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends Fragment {

    public HotFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frame_hot, container);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView tv = getView().findViewById(R.id.hotTextView1);
        tv.setText("胡桃夹子");

        Button btnW3c = getActivity().findViewById(R.id.btn_w3c);
        btnW3c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.w3school.com.cn/"));
                startActivity(intent);

            }
        });
        Button btnCSDN = getActivity().findViewById(R.id.btn_csdn);
        btnCSDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.csdn.net/"));
                startActivity(intent);

            }
        });
        Button btnRunoob = getActivity().findViewById(R.id.btn_runoob);
        btnRunoob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.runoob.com/"));
                startActivity(intent);

            }
        });
        Button btnWeibo = getActivity().findViewById(R.id.btn_weibo);
        btnWeibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("https://weibo.com/"));
                startActivity(intent);

            }
        });
        Button btnPeople = getActivity().findViewById(R.id.btn_people);
        btnPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.people.com.cn/"));
                startActivity(intent);

            }
        });
        Button btnSnow = getActivity().findViewById(R.id.btn_snow);
        btnSnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("https://xueqiu.com/"));
                startActivity(intent);

            }
        });
        Button btnFood = getActivity().findViewById(R.id.btn_food);
        btnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),FoodActivity.class);
                startActivity(intent);

            }
        });
        Button btnWeather = getActivity().findViewById(R.id.btn_weather);
        btnWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("https://tianqi.so.com/weather/101270101"));
                startActivity(intent);

            }
        });
        Button btnPlay = getActivity().findViewById(R.id.btn_play);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),PlayActivity.class);
                startActivity(intent);

            }
        });



    }


}
