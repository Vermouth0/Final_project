package com.swufe.wy.slimming;


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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlanCheckFragment extends ListFragment implements AdapterView.OnItemClickListener {

    Handler handler;
    private List<HashMap<String,String>> listItems; //存放文字、图片信息
    private SimpleAdapter listItemAdapter;
    String TAG="PlanCheckFragment";
    SQLHelper sqlHelper;

    public PlanCheckFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plan_check, container, false);
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
                            R.layout.check_items, //ListItem的XML布局实现
                            new String[]{"plan_title","plan_content"}, //数据的key
                            new int[]{R.id.plan_title_check,R.id.plan_content_check}//布局里的id，k与id一一匹配
                    );
                    setListAdapter(listItemAdapter);
                }
                super.handleMessage(msg);
            }
        };
        //getListView().setOnItemClickListener(this);
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_plan_check, null);
        ListView lv = view.findViewById(android.R.id.list);
        lv.setOnItemClickListener(this);
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(TAG, "onItemClick: 打卡");
    }
}
