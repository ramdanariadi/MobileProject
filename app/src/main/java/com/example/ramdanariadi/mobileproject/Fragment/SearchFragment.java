package com.example.ramdanariadi.mobileproject.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ramdanariadi.mobileproject.Class.Destination;
import com.example.ramdanariadi.mobileproject.Class.DestinationAdapter;
import com.example.ramdanariadi.mobileproject.Class.DestinationSqlHandler;
import com.example.ramdanariadi.mobileproject.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    EditText searchEditText;
    DestinationSqlHandler destinationSqlHandler;
    Context context;

    public SearchFragment() {
        // Required empty public constructor
        destinationSqlHandler = new DestinationSqlHandler(getContext());

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        searchEditText = (EditText) container.findViewById(R.id.edtSearch);
        ArrayList<Destination> destinations = new ArrayList<>();
//        destinations.add(new Destination("Geprek","geprek enak",1000,true, R.drawable.ic_action_cross));
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_search_recyler_view);
//        DestinationAdapter destinationAdapter = new DestinationAdapter(destinations);
//        recyclerView.setAdapter(destinationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("info ","text changed");
                Toast.makeText(getContext(),"Searching",Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
}
