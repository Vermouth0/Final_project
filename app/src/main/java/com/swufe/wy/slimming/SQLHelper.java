package com.swufe.wy.slimming;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SQLHelper {

    Connection con;
    public final String TAG="SQLHelper";
    public List<HashMap<String,String>> planList;

    public SQLHelper(){

    }

    //连接数据库
    public Connection getConnection(){
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
        return con;
    }
    public void getPlan(Connection con1) throws java.sql.SQLException {
         planList = new ArrayList<HashMap<String, String>>();
        //HashMap<String, String> map =new HashMap<String, String>(); 不能定义在这里，要定义在循环里
        try {
            String sql = "select * from plan";        //查询表名为“user”的所有内容
            Statement stmt = con1.createStatement();        //创建Statement
            ResultSet rs = stmt.executeQuery(sql);          //ResultSet类似Cursor

            while (rs.next()) {

                Log.i(TAG, "testConnection: title" + rs.getString("title"));
                Log.i(TAG, "testConnection: content" + rs.getString("content"));
                //Log.i(TAG, "testConnection: phoneNo"+rs.getString("times"));

                String title = rs.getString("title");
                String content = rs.getString("content");
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("plan_title", title);
                map.put("plan_content", content);
                planList.add(map);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {

        } finally {
            if (con1 != null)
                try {
                    con1.close();
                } catch (SQLException e) {
                }
        }

    }
}
