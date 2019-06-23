package com.swufe.wy.slimming;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.SQLException;

public class PlanAddActivity extends Activity implements View.OnClickListener {

    EditText editTitle, editContent;
    String TAG = "PlanAddActivity";
    String title,content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_add);

        //获取控件
        editTitle = findViewById(R.id.add_title);
        editContent = findViewById(R.id.add_content);

        //点击按钮，写入数据库
        Button btnAdd =findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        new Thread(addData).start();
    }

    Runnable addData = new Runnable() {

        @Override
        public void run() {

            //获取输入内容
            title = editTitle.getText().toString();
            content = editContent.getText().toString();

            //在数据库中新增数据
            SQLHelper sqlHelper1 = new SQLHelper();
            Connection con =sqlHelper1.getConnection();

            if(title.equals("")){
                Looper.prepare();
                Toast.makeText(PlanAddActivity.this,
                        "请输入计划标题哦！O(∩_∩)O~~",
                        Toast.LENGTH_SHORT).show();
                Looper.loop();

            }else{
                try {
                    sqlHelper1.addPlan(con,title,content);
                    Looper.prepare();
                    Toast.makeText(PlanAddActivity.this,
                            "新增成功！",
                            Toast.LENGTH_SHORT).show();
                    Looper.loop();
                } catch (SQLException e) {
                    e.printStackTrace();
                    Looper.prepare();
                    Toast.makeText(PlanAddActivity.this,
                            "出错啦！",
                            Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        }
    };
}
