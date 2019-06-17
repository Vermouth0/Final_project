package com.swufe.wy.slimming;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class PlanEditAdapter extends BaseAdapter {

    private List<PlanItem> planItems;
    private Context planTitle;
    private Context planContent;
    private Context finishiTimes;

    public PlanEditAdapter(List<PlanItem> planItems, Context planTitle) {
        this.planItems = planItems;
        this.planTitle = planTitle;
        this.planContent = planContent;
        this.finishiTimes = finishiTimes;
    }

    @Override
    public int getCount() {
        return planItems.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(planTitle).inflate(R.layout.fragment_plan_edit,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.plan_title = convertView.findViewById(R.id.plan_title);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.plan_title.setText(planItems.get(position).getPlanTitle());
        return convertView;
    }

    private class ViewHolder{
        TextView plan_title;
    }
}
