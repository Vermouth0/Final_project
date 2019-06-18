package com.swufe.wy.slimming;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlanEditAdapter extends ArrayAdapter {


    public PlanEditAdapter(Context context, int resource,ArrayList<HashMap<String,String>> list) {
        super(context, resource, list);
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        //position:当前位置,根据不同的position返回不同的view；convertView：当前控件；parent：父类控件

        View itemView = convertView; //用于显示每一行的数据
        if(itemView == null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.edit_items,parent,false);
            //如果没有内容，向布局文件中填充对象；false：不覆盖根布局
        }

        Map<String,String> map = (Map<String,String>)getItem(position);
        TextView title=itemView.findViewById(R.id.plan_title);
        TextView detail=itemView.findViewById(R.id.plan_content);

        title.setText("Title:"+map.get("plan_title"));
        detail.setText("detail:"+map.get("plan_content"));
        return itemView;
    }
}
