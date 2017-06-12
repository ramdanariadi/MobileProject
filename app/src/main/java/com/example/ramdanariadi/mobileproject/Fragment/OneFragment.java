package com.example.ramdanariadi.mobileproject.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ScrollView;

import com.example.ramdanariadi.mobileproject.Activity.AddDestActivity;
import com.example.ramdanariadi.mobileproject.Activity.AddNewDestActivity;
import com.example.ramdanariadi.mobileproject.Activity.MapsActivity;
import com.example.ramdanariadi.mobileproject.Class.Destination;
import com.example.ramdanariadi.mobileproject.Class.DestinationAdapter;
import com.example.ramdanariadi.mobileproject.R;

import java.util.ArrayList;

public class OneFragment extends Fragment {
    ScrollView scrollView;
    FloatingActionButton fab;
    public OneFragment() {
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
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        scrollView = (ScrollView) view.findViewById(R.id.scrollView);
        scrollView.scrollTo(10,10);

        fab = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);

        ArrayList<Destination> destinations = new ArrayList<>();
//        destinations.add(new Destination("Geprek","geprek enak",1000,true, R.drawable.ic_action_cross));
//        destinations.add(new Destination("Geprek","geprek enak",1000,true, R.drawable.ic_action_cross));
//        destinations.add(new Destination("Geprek","geprek enak",1000,true, R.drawable.ic_action_cross));
//        destinations.add(new Destination("Geprek","geprek enak",1000,true, R.drawable.ic_action_cross));
//        destinations.add(new Destination("Geprek","geprek enak",1000,true, R.drawable.ic_action_cross));
//        destinations.add(new Destination("Geprek","geprek enak",1000,true, R.drawable.ic_action_cross));
//        destinations.add(new Destination("Geprek","geprek enak",1000,true, R.drawable.ic_action_cross));
//        destinations.add(new Destination("Geprek","geprek enak",1000,true, R.drawable.ic_action_cross));
//        destinations.add(new Destination("Geprek","geprek enak",1000,true, R.drawable.ic_action_cross));
//        destinations.add(new Destination("Geprek","geprek enak",1000,true, R.drawable.ic_action_cross));
//        destinations.add(new Destination("Geprek","geprek enak",1000,true, R.drawable.ic_action_cross));
//        destinations.add(new Destination("Geprek","geprek enak",1000,true, R.drawable.ic_action_cross));
//        destinations.add(new Destination("Geprek","geprek enak",1000,true, R.drawable.ic_action_cross));
//        destinations.add(new Destination("Geprek","geprek enak",1000,true, R.drawable.ic_action_cross));
//        destinations.add(new Destination("Geprek","geprek enak",1000,true, R.drawable.ic_action_cross));
//        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recent_recycler);
//        DestinationAdapter destinationAdapter = new DestinationAdapter(destinations);
//        recyclerView.setAdapter(destinationAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddDestActivity.class);
                startActivity(intent);
            }
        });

        return  view;
    }

}
