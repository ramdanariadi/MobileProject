package com.example.ramdanariadi.mobileproject.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ramdanariadi.mobileproject.Activity.DestDescriptActivity;
import com.example.ramdanariadi.mobileproject.Class.DestinationAdapter;
import com.example.ramdanariadi.mobileproject.Class.DestinationSqlHandler;
import com.example.ramdanariadi.mobileproject.R;

public class TwoFragment extends Fragment {
    Button button;

    public TwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_two,container,false);

        DestinationSqlHandler destinationSqlHandler = new DestinationSqlHandler(getContext());

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.favorite_recyclerview);
        DestinationAdapter destinationAdapter = new DestinationAdapter(destinationSqlHandler.getAllDestination(),getContext());
        recyclerView.setAdapter(destinationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

}
