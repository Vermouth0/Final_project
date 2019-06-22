package com.swufe.wy.slimming;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class PlanCheckActivity extends ListActivity implements AdapterView.OnItemClickListener {

    Handler handler;
    private List<HashMap<String, String>> listItems; //存放文字、图片信息
    private SimpleAdapter listItemAdapter;
    String TAG = "PlanCheckFragment";
    SQLHelper sqlHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_plan_check);

        this.setListAdapter(listItemAdapter);

        new Thread(runnable).start();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 7) {
                    listItems = (List<HashMap<String, String>>) msg.obj;
                    listItemAdapter = new SimpleAdapter(PlanCheckActivity.this, listItems, //listItem数据源
                            R.layout.check_items, //ListItem的XML布局实现
                            new String[]{"plan_title", "plan_content"}, //数据的key
                            new int[]{R.id.plan_title_check, R.id.plan_content_check}//布局里的id，k与id一一匹配
                    );
                    setListAdapter(listItemAdapter);
                }
                super.handleMessage(msg);
            }
        };

        getListView().setOnItemClickListener(this);

    }
    Runnable runnable = new Runnable() {
        private Connection con = null;

        @Override
        public void run() {

            sqlHelper = new SQLHelper();
            Connection con = sqlHelper.getConnection();
            sqlHelper.getPlan(con);

            List<HashMap<String, String>> planList = sqlHelper.planList;
            Message msg = handler.obtainMessage(7);
            //msg.what=5;
            msg.obj = planList;
            handler.sendMessage(msg);
        }
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(TAG, "onItemClick: 打卡");
        Log.i(TAG, "onItemClick: position="+position);

        //构造对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("打卡确认").setMessage("确定完成任务了吗？").setPositiveButton("Yes",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(PlanCheckActivity.this,
                        "打卡完成，继续坚持哟！",
                        Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(PlanCheckActivity.this,
                        "不要灰心，坚持就是胜利！",
                        Toast.LENGTH_SHORT).show();
            }
        });
        builder.setIcon(R.drawable.plan_1);
        builder.create().show();
    }
}
