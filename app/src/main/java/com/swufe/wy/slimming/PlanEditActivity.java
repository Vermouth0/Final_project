package com.swufe.wy.slimming;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class PlanEditActivity extends ListActivity implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener {

    Handler handler;
    private List<HashMap<String, String>> listItems; //存放文字、图片信息
    private SimpleAdapter listItemAdapter;
    String TAG = "PlanEditFragment";
    SQLHelper sqlHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Dialog dialog = new Dialog();
        dialog.showProgressDialog(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_edit);

        //载入数据
        this.setListAdapter(listItemAdapter);

        new Thread(runnable).start();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 7) {
                    listItems = (List<HashMap<String, String>>) msg.obj;
                    listItemAdapter = new SimpleAdapter(PlanEditActivity.this, listItems, //listItem数据源
                            R.layout.edit_items, //ListItem的XML布局实现
                            new String[]{"plan_title", "plan_content"}, //数据的key
                            new int[]{R.id.plan_title_edit, R.id.plan_content_edit}//布局里的id，k与id一一匹配
                    );
                    setListAdapter(listItemAdapter);
                }
                super.handleMessage(msg);
            }
        };

        getListView().setOnItemClickListener(this);
        getListView().setOnItemLongClickListener(this);
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

        Intent intent=new Intent(this,PlanEditActivityEdit.class);

        //传递参数：被点击的listView中的title被传递到修改界面
        TextView planTitle = view.findViewById(R.id.plan_title_edit);
        String title = planTitle.getText().toString();
        intent.putExtra("plan_title",title);

        startActivity(intent);
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

        //构造对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("确认删除").setMessage("确定要放弃这个计划吗？").setPositiveButton("Yes",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                TextView planTitle = findViewById(R.id.plan_title_edit);
                final String title=planTitle.getText().toString();
                //Android4.0以后不支持在主线程进行耗时操作，所以需要新开一条线程操作数据库

                Runnable sqlRunnable = new Runnable() {
                    @Override
                    public void run() {
                        SQLHelper sqlHelper = new SQLHelper();
                        Connection con = sqlHelper.getConnection();
                        try {
                            sqlHelper.deletePlan(con,title);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                    }
                };
                new Thread(sqlRunnable).start();
                listItems.remove(position);
                listItemAdapter.notifyDataSetChanged();
                Toast.makeText(PlanEditActivity.this,
                        "删除成功！",
                        Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(PlanEditActivity.this,
                        "继续保持，坚持就是胜利！",
                        Toast.LENGTH_SHORT).show();
            }
        });
        builder.setIcon(R.drawable.plan_1);
        builder.create().show();
        return true;
    }
}
