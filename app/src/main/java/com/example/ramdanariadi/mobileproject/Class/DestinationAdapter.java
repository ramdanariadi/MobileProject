package com.example.ramdanariadi.mobileproject.Class;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ramdanariadi.mobileproject.Activity.DestDescriptActivity;
import com.example.ramdanariadi.mobileproject.R;

import java.net.URI;
import java.util.ArrayList;

/**
 * Created by ramdan ariadi on 10/05/2017.
 */

public class DestinationAdapter extends RecyclerView.Adapter<DestinationAdapter.ViewHolder> {
    ArrayList<Destination> destinations;
    Context context;
    public DestinationAdapter(ArrayList<Destination> destinations, Context context) {
        this.destinations = destinations;
        this.context = context;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Destination destination = destinations.get(position);
        holder.textTitle.setText(destination.getDestinationName());
        holder.textDescription.setText(destination.getDescription());
        holder.textCost.setText(destination.getLocation()+" ");
        Uri uriImage = Uri.parse(destination.getPicture().toString());
        Log.i("uri",destination.getPicture());
        holder.imageView.setImageURI(uriImage);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DestDescriptActivity.class);
                intent.putExtra(DestinationSqlHandler.COLUMN_DESTINATION_NAME,destination.getDestinationName());
                intent.putExtra(DestinationSqlHandler.COLUMN_DESCRIPTION,destination.getDescription());
                intent.putExtra(DestinationSqlHandler.COLUMN_COST,destination.getCost());
                intent.putExtra(DestinationSqlHandler.COLUMN_PICTURE,destination.getPicture());
                intent.putExtra(DestinationSqlHandler.COLUMN_IS_FAVORITE,destination.getFavorite());
                intent.putExtra(DestinationSqlHandler.COLUMN_LOCATION,destination.getLocation());
                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return destinations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textTitle;
        public TextView textDescription;
        public TextView textCost;
        public ImageView imageView;
        public View view;
        public ViewHolder(View view) {
            super(view);
            textTitle = (TextView) view.findViewById(R.id.txtTitle);
            textDescription = (TextView) view.findViewById(R.id.txtDescription);
            textCost = (TextView) view.findViewById(R.id.txtCost);
            imageView = (ImageView) view.findViewById(R.id.imageDestination);
            this.view = view;
        }
    }
}
