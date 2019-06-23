package com.swufe.wy.slimming;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class PlanEditActivityEdit extends AppCompatActivity implements View.OnClickListener {

    String title;
    String content = null;
    EditText editTitle, editContent;
    String TAG = "PlanEditFragmentEdit";
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Dialog dialog = new Dialog();
        dialog.showProgressDialog(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_edit_edit);

        //获取控件
        editTitle = findViewById(R.id.edit_title);
        editContent = findViewById(R.id.edit_content);

        //接收参数：接收被点击的listView中的title
        Intent intent = getIntent();
        title = intent.getStringExtra("plan_title");
        Log.i(TAG, "onCreate:传递过来的title "+title);

        //创建属于主线程的handler
        handler=new Handler();

        //开启线程获取内容和标题
        new Thread(runnable).start();

        //点击按钮
        Button btnCheck =findViewById(R.id.btn_check_edit);
        btnCheck.setOnClickListener(this);
    }

    //通过线程获取内容和标题
    Runnable runnable = new Runnable() {

        @Override
        public void run() {

            //获取数据库中的数据
            SQLHelper sqlHelper = new SQLHelper();
            Connection con = sqlHelper.getConnection();
            try {
                content = sqlHelper.queryPlanContent(con,title);
                handler.post(getEditView);
                //显示数据
                Log.i(TAG, "setEditText: 从数据库中获取的内容："+content);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    };

    Runnable  getEditView = new  Runnable(){
        @Override
        public void run() {
            //更新界面
            editTitle.setText(title);
            editContent.setText(content);
        }
    };


    @Override
    public void onClick(View v) {

        new Thread(editData).start();
    }

    Runnable editData = new Runnable() {

        @Override
        public void run() {

            //获取用户输入的数据
            title = editTitle.getText().toString();
            content = editContent.getText().toString();
            String contentBefore = null;

            //修改数据库中的数据
            SQLHelper sqlHelper1 = new SQLHelper();
            Connection con =sqlHelper1.getConnection();
            try {
                contentBefore = sqlHelper1.queryPlanContent(con,title);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(contentBefore != null){
                try {
                    sqlHelper1.editContent(con,title,content);
                    Looper.prepare();
                    Toast.makeText(PlanEditActivityEdit.this,
                            "修改成功！",
                            Toast.LENGTH_SHORT).show();
                    Looper.loop();
                } catch (SQLException e) {
                    e.printStackTrace();
                    Looper.prepare();
                    Toast.makeText(PlanEditActivityEdit.this,
                            "修改失败！",
                            Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }else {
                try {
                    sqlHelper1.addPlan(con,title,content);
                    Looper.prepare();
                    Toast.makeText(PlanEditActivityEdit.this,
                            "修改成功！",
                            Toast.LENGTH_SHORT).show();
                    Looper.loop();
                } catch (SQLException e) {
                    e.printStackTrace();
                    Looper.prepare();
                    Toast.makeText(PlanEditActivityEdit.this,
                            "修改失败！",
                            Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }

        }
    };
}
