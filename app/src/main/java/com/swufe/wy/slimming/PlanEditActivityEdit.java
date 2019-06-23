package com.swufe.wy.slimming;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class PlanEditActivityEdit extends AppCompatActivity {

    String title;
    String content = null;
    EditText editTitle, editContent;
    String TAG = "PlanEditFragmentEdit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_edit_edit);

        //获取控件
        editTitle = findViewById(R.id.edit_title);
        editContent = findViewById(R.id.edit_content);

        //接收参数：接收被点击的listView中的title
        Intent intent = getIntent();
        title = intent.getStringExtra("plan_title");
        Log.i(TAG, "onCreate:传递过来的title "+title);


        Runnable runnable = new Runnable() {
            private Connection con = null;

            @Override
            public void run() {
                //显示数据

                setEditText(title);

                //获取用户输入的数据
                title = editTitle.getText().toString();
                content = editContent.getText().toString();

                //修改数据库中的数据
                SQLHelper sqlHelper1 = new SQLHelper();
                Connection con =sqlHelper1.getConnection();
                try {
                    content = sqlHelper1.queryPlanContent(con,title);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if(content != null){
                    try {
                        sqlHelper1.editContent(con,title,content);
                        Toast.makeText(PlanEditActivityEdit.this,
                                "修改成功！",
                                Toast.LENGTH_SHORT).show();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        Toast.makeText(PlanEditActivityEdit.this,
                                "修改失败！",
                                Toast.LENGTH_SHORT).show();
                    }
                }else {
                    try {
                        sqlHelper1.addPlan(con,title,content);
                        Toast.makeText(PlanEditActivityEdit.this,
                                "修改成功！",
                                Toast.LENGTH_SHORT).show();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        Toast.makeText(PlanEditActivityEdit.this,
                                "修改失败！",
                                Toast.LENGTH_SHORT).show();
                    }
                }

            }
        };

    }

    public void setEditText(String title){
        if(title != ""){
            //获取数据库中的数据
            SQLHelper sqlHelper = new SQLHelper();
            Connection con = sqlHelper.getConnection();
            try {
                content = sqlHelper.queryPlanContent(con,title);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //显示数据
            Log.i(TAG, "setEditText: 从数据库中获取的内容："+content);
            editTitle.setText(title);
            editContent.setText(content);
        }else{
            Toast.makeText(PlanEditActivityEdit.this,
                    "修改失败！",
                    Toast.LENGTH_SHORT).show();
        }
    }


}
