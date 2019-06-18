package com.swufe.wy.slimming;

import android.app.ListActivity;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlanEditActivity extends ListActivity implements Runnable{


    Handler handler;
    private List<HashMap<String,String>> listItems; //存放文字、图片信息
    private SimpleAdapter listItemAdapter; //适配器

    public final String TAG="PlanEditActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initListView();

        PlanEditAdapter myAdapter=new PlanEditAdapter(this,R.layout.edit_items, (ArrayList<HashMap<String, String>>) listItems);  //
        this.setListAdapter(myAdapter); // 使用自定义的 myAdapter

        Thread t=new Thread(this);
        t.start();

       /* handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==7){
                    listItems=(List<HashMap<String, String>>)msg.obj;
                    listItemAdapter=new SimpleAdapter(PlanEditActivity.this,listItems, //listItem数据源
                            R.layout.edit_items, //ListItem的XML布局实现
                            new String[]{"ItemTitle","ItemDetail"}, //数据的key
                            new int[]{R.id.plan_title,R.id.plan_content}//布局里的id，k与id一一匹配
                    );
                    setListAdapter(listItemAdapter);
                }
                super.handleMessage(msg);
            }
        };//注：有分号 */

    }

    private void initListView(){
        int i;
        listItems=new ArrayList<HashMap<String, String>>();
        for(i=0;i<10;i++);{
            HashMap<String, String> map =new HashMap<String, String>();
            map.put("plan_title","Title:"+ i); //标题文字
            map.put("plan_content","Content:" + i); //详情描述
            listItems.add(map); //往列表里添加数据
        }
        //生成适配器Item和动态数组对应元素
        listItemAdapter=new SimpleAdapter(this,listItems, //listItem数据源
                R.layout.edit_items, //ListItem的XML布局实现
                new String[]{"plan_title","plan_content"}, //数据的key
                new int[]{R.id.plan_title,R.id.plan_content} //布局里的id，k与id一一匹配
        );
    }

    @Override
    public void run() {
    }
}
