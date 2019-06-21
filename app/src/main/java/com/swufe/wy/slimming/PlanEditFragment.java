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

    //public final String TAG="PlanEditFragment";
    Handler handler;
    private List<HashMap<String,String>> listItems; //存放文字、图片信息
    private SimpleAdapter listItemAdapter;

    public final String TAG="PlanEditFragment";

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
                            new int[]{R.id.plan_title,R.id.plan_content}//布局里的id，k与id一一匹配
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
                getPlan(con);    //获取数据
            } catch (java.sql.SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        public void getPlan(Connection con1) throws java.sql.SQLException {

            List<HashMap<String, String>> planList=new ArrayList<HashMap<String, String>>();
            //HashMap<String, String> map =new HashMap<String, String>(); 不能定义在这里，要定义在循环里
            try {
                String sql = "select * from plan";        //查询表名为“user”的所有内容
                Statement stmt = con1.createStatement();        //创建Statement
                ResultSet rs = stmt.executeQuery(sql);          //ResultSet类似Cursor

                while (rs.next()) {

                    Log.i(TAG, "testConnection: title"+rs.getString("title"));
                    Log.i(TAG, "testConnection: content"+rs.getString("content"));
                    //Log.i(TAG, "testConnection: phoneNo"+rs.getString("times"));

                    String title=rs.getString("title");
                    String content=rs.getString("content");
                    HashMap<String, String> map =new HashMap<String, String>();
                    map.put("plan_title",title);
                    map.put("plan_content",content);
                    planList.add(map);
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
