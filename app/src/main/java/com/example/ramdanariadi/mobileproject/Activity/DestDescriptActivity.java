package com.example.ramdanariadi.mobileproject.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ramdanariadi.mobileproject.Class.DestinationSqlHandler;
import com.example.ramdanariadi.mobileproject.R;

public class DestDescriptActivity extends AppCompatActivity {
    ImageView imageView;
    AppCompatImageView imv;
    TextView nama,deskripsi,harga;
    Toolbar toolbar;
    String koordinat;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dest_description);
        intent = getIntent();


        toolbar = (Toolbar) findViewById(R.id.destcToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DestDescriptActivity.this,MainActivity.class));
                finish();
            }
        });



        nama = (TextView) findViewById(R.id.txtNamaTempat);
        deskripsi = (TextView) findViewById(R.id.txtDeskripsi);
        harga = (TextView) findViewById(R.id.txtHarga);
        imv = (AppCompatImageView) findViewById(R.id.imageDestination);

        nama.setText(intent.getStringExtra(DestinationSqlHandler.COLUMN_DESTINATION_NAME));
        harga.setText(intent.getStringExtra(DestinationSqlHandler.COLUMN_COST));
        deskripsi.setText(intent.getStringExtra(DestinationSqlHandler.COLUMN_LOCATION));
        Uri uri = Uri.parse(intent.getStringExtra(DestinationSqlHandler.COLUMN_PICTURE).toString());
        imv.setImageURI(uri);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.open_maps,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       // String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?saddr=%f,%f(%s)&daddr=%f,%f (%s)", sourceLatitude, sourceLongitude, "Home Sweet Home", destinationLatitude, destinationLongitude, "Where the party is at");
//        Log.i("location",koordinat.toString());
        koordinat = intent.getStringExtra(DestinationSqlHandler.COLUMN_LOCATION);
        Log.i("location",koordinat+"aaaaaa");
        String[] ltlg = koordinat.split(",");
        Double latitude = Double.parseDouble(ltlg[0]);
        Double longitude = Double.parseDouble(ltlg[1]);
//        LatLng latLng = new LatLng()
        String uri = String.format("geo:%f,%f", latitude, longitude);
        Log.i("location",uri+"aaaaaa");
        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(uri));
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}
