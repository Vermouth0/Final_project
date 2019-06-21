package com.swufe.wy.slimming;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class PlanEditFragment extends ListFragment {


    Handler handler;
    private List<HashMap<String,String>> listItems; //存放文字、图片信息
    private SimpleAdapter listItemAdapter;
    public final String TAG="PlanEditFragment";
    SQLHelper sqlHelper;

    public PlanEditFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setListAdapter(listItemAdapter);
        new Thread(runnable).start();
        
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==7){
                    listItems=(List<HashMap<String, String>>)msg.obj;
                    listItemAdapter=new SimpleAdapter(getActivity(),listItems, //listItem数据源
                            R.layout.edit_items, //ListItem的XML布局实现
                            new String[]{"plan_title","plan_content"}, //数据的key
                            new int[]{R.id.plan_title_edit,R.id.plan_content_edit}//布局里的id，k与id一一匹配
                    );
                    setListAdapter(listItemAdapter);
                }
                super.handleMessage(msg);
            }
        };
    }

    Runnable runnable=new Runnable() {
        private Connection con = null;

        @Override
        public void run() {

            sqlHelper=new SQLHelper();
            Connection con =sqlHelper.getConnection();
            try {
                sqlHelper.getPlan(con);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            List<HashMap<String,String>> planList = sqlHelper.planList;
            Message msg=handler.obtainMessage(7);
            //msg.what=5;
            msg.obj=planList;
            handler.sendMessage(msg);

        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_plan_edit,container, false);


    }


}
