package com.swufe.wy.slimming;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class SQLActivity extends AppCompatActivity {

    public final String TAG="SQLActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);
        new Thread(runnable).start();
    }

    Handler myHandler=new Handler(){

        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            if(msg.what==7) {
                List<String> list2 = (List<String>) msg.obj;
                Iterator it = list2.iterator();
                while (it.hasNext()) {
                    String i = (String) it.next();
                    Log.i(TAG, "handleMessage: 姓名：" + i);
                }
            }
            //Bundle data=new Bundle();
            //data=msg.getData();

            //String studentId = data.get("studentId").toString();
           // String userName = data.get("userName").toString();

            //System.out.println("studentId:"+data.get("studentId").toString());    //输出第n行，列名为“studentId”的值
            //Log.i("TAG","studentId:"+data.get("studentId").toString());
            //Log.i("TAG","userName:"+data.get("userName").toString());
           // Log.i("TAG","phoneNo:"+data.get("phoneNo").toString());
            //Log.i("TAG","dormitory:"+data.get("dormitory").toString());
        }
    };

    Runnable runnable=new Runnable() {
        private Connection con = null;

        @Override
        public void run() {
            // TODO Auto-generated method stub

            try {
                Class.forName("com.mysql.jdbc.Driver");
                //连接，address为数据IP，Port为端口号，DBName为数据名称，UserName为数据库登录账户，Password为数据库登录密码
                con = DriverManager.getConnection("jdbc:mysql://119.3.167.204:3306/slimming",
                                "root","mySQL@2019");
                Log.i(TAG, "run: 连接成功");

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            try {
                testConnection(con);    //测试数据库连接
            } catch (java.sql.SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        public void testConnection(Connection con1) throws java.sql.SQLException {
            try {
                String sql = "select * from plan";        //查询表名为“user”的所有内容
                Statement stmt = con1.createStatement();        //创建Statement
                ResultSet rs = stmt.executeQuery(sql);          //ResultSet类似Cursor

                Bundle bundle=new Bundle();

                List<String> nameList=new ArrayList<String>();

                while (rs.next()) {

                    //bundle.clear();
                    //bundle.putString("studentId",rs.getString("studentId"));
                    //bundle.putString("userName",rs.getString("userName"));
                    //bundle.putString("phoneNo",rs.getString("phoneNo"));
                    //bundle.putString("dormitory",rs.getString("dormitory"));
                    Log.i(TAG, "testConnection: userName"+rs.getString("userName"));
                    Log.i(TAG, "testConnection: studentId"+rs.getString("studentId"));
                    Log.i(TAG, "testConnection: phoneNo"+rs.getString("phoneNo"));

                    String userName=rs.getString("userName");
                    nameList.add(userName);


                    Message msg = myHandler.obtainMessage(7);
                    msg.obj=nameList;
                    myHandler.sendMessage(msg);
                }

                rs.close();
                stmt.close();
            } catch (SQLException e) {

            } finally {
                if (con1 != null)
                    try {
                        con1.close();
                    } catch (SQLException e) {}
            }
        }
    };


}
