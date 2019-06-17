package com.swufe.wy.slimming;



import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class PlanEditFragment extends Fragment {
    private FragmentManager fManager;
    private ArrayList<PlanItem> planItems;
    private ListView list_plan;

    public PlanEditFragment(){

    }
    public PlanEditFragment(android.support.v4.app.FragmentManager fManager, ArrayList<PlanItem> planItems) {
        this.fManager = fManager;
        this.planItems = planItems;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_plan_edit, container, false);
        list_plan = view.findViewById(R.id.plan_list_view);
        PlanEditAdapter myAdapter = new PlanEditAdapter(planItems, getActivity());
        list_plan.setAdapter(myAdapter);
        //list_plan.setOnItemClickListener(this);
        return view;
    }


}
