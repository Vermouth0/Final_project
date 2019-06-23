package com.swufe.wy.slimming;

import android.app.ListActivity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

public class PlanReportActivity extends ListActivity {

    Handler handler;
    private List<HashMap<String, String>> listItems; //存放文字、图片信息
    private SimpleAdapter listItemAdapter;
    String TAG = "PlanCheckFragment";
    SQLHelper sqlHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Dialog dialog = new Dialog();
        dialog.showProgressDialog(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_report);

        this.setListAdapter(listItemAdapter);

        //获取数据
        new Thread(runnable).start();

        //把数据绑定到控件里
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 7) {
                    listItems = (List<HashMap<String, String>>) msg.obj;
                    listItemAdapter = new SimpleAdapter(PlanReportActivity.this, listItems, //listItem数据源
                            R.layout.report_items, //ListItem的XML布局实现
                            new String[]{"plan_title", "plan_times"}, //数据的key
                            new int[]{R.id.report_title, R.id.report_times}//布局里的id，k与id一一匹配
                    );
                    setListAdapter(listItemAdapter);
                }
                super.handleMessage(msg);
            }
        };

    }
    Runnable runnable = new Runnable() {
        private Connection con = null;

        @Override
        public void run() {

            sqlHelper = new SQLHelper();
            Connection con = sqlHelper.getConnection();
            sqlHelper.getPlanTimes(con);

            List<HashMap<String, String>> planList = sqlHelper.planList;
            Message msg = handler.obtainMessage(7);
            //msg.what=5;
            msg.obj = planList;
            handler.sendMessage(msg);
        }
    };
}
