package com.swufe.wy.slimming;



import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class PlanEditFragment extends Fragment {

    public final String TAG="PlanEditFragment";

    public PlanEditFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_plan_edit,container, false);

    }


}
