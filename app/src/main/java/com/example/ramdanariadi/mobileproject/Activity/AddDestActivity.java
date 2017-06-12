package com.example.ramdanariadi.mobileproject.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ramdanariadi.mobileproject.Class.Destination;
import com.example.ramdanariadi.mobileproject.Class.DestinationSqlHandler;
import com.example.ramdanariadi.mobileproject.Manifest;
import com.example.ramdanariadi.mobileproject.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddDestActivity extends AppCompatActivity {
    final int PLACE_PICKER_REQUEST_CODE = 1;
    final int IMAGE_CAPTURE_REQUEST_CODE = 2;
    Toolbar toolbar;
    MenuItem menuItemSave;
    Uri uri;

    ImageButton buttonOpenMap,buttonAddImage;
    Spinner spnKategori;
    EditText edtNamaTempat, edtDesktipsi, edtHarga;
    CheckBox checkBoxFavorite;

    Uri uriImageResult;
    int count = 0;
    LatLng koordinat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dest);


        edtNamaTempat = (EditText) findViewById(R.id.add_nama_tempat);
        edtDesktipsi = (EditText) findViewById(R.id.addDeskripsi);
        edtHarga = (EditText) findViewById(R.id.addHarga);
        spnKategori = (Spinner) findViewById(R.id.spinnerKategori);
        checkBoxFavorite = (CheckBox) findViewById(R.id.checkBoxFavorite);

        buttonOpenMap = (ImageButton) findViewById(R.id.openMaps);
        buttonAddImage = (ImageButton) findViewById(R.id.addImage);
        toolbar = (Toolbar) findViewById(R.id.addDestToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddDestActivity.this,MainActivity.class));
                finish();
            }
        });

        buttonOpenMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(AddDestActivity.this,MapsActivity.class));
                PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();

                try {
                    Intent intent = intentBuilder.build(AddDestActivity.this);
                    startActivityForResult(intent,PLACE_PICKER_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });


        buttonAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
////                    takePictureButton.setEnabled(false);
//                    ActivityCompat.requestPermissions(AddDestActivity.this, new String[] { android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
//                }
//
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                uriImageResult = Uri.fromFile(getFile());
//                if(intent.resolveActivity(getPackageManager()) != null){
//                    intent.putExtra(MediaStore.EXTRA_OUTPUT,uriImageResult);
//                    startActivityForResult(intent,IMAGE_CAPTURE_REQUEST_CODE);
//                }

//                final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/";
//                File newdir = new File(dir);
//                newdir.mkdirs();
//
//                count++;
//                String file = dir+count+".jpg";
//                File newfile = new File(file);
//                try {
//                    newfile.createNewFile();
//                }
//                catch (IOException e)
//                {
//                }
//
//                Log.i("info : ",file);
//
//                Uri outputFileUri = Uri.fromFile(newfile);
//
//                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
//
//                startActivityForResult(cameraIntent, IMAGE_CAPTURE_REQUEST_CODE);


                Intent intent = new Intent();
// Show only images, no videos or anything else
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), IMAGE_CAPTURE_REQUEST_CODE);

            }
        });
    }

    File getFile(){

        File mediaStorageDir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),"DemoApp");
        if(!mediaStorageDir.exists()){
            if(!mediaStorageDir.mkdirs()){
                return null;
            }
        }

        String timeCaptured =  "asdf";

        return  new File(mediaStorageDir.getPath()+File.separator+"img_"+timeCaptured+".jpg");
    }

    Uri getUriForImage(){
        String state = Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED)){
            File mediaStorageDir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),"app");

            if(!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
                Log.d("app", "failed to create directory");
            }

            File file = new File(mediaStorageDir.getPath()+File.separator+"photo.jpg");

            return FileProvider.getUriForFile(AddDestActivity.this,"com.example.ramdanariadi.mobileproject.Activity",file);
        }
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == PLACE_PICKER_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            Place place = PlacePicker.getPlace(this,data);

            CharSequence name = place.getName();
            CharSequence addres = place.getAddress();
            CharSequence attribution = place.getAttributions();
            koordinat = place.getLatLng();
            String lagLat = koordinat.toString();
            String lat = koordinat.latitude+"";
            String lag = koordinat.longitude+"";
            Double llat = Double.parseDouble(lat);
            Double llag = Double.parseDouble(lag);
            if(attribution == null){
                attribution = "";
            }

            TextView koordinat = (TextView) findViewById(R.id.koordinat);
            koordinat.setText(llat+"-"+llag+"-"+addres);

        }else if(requestCode == IMAGE_CAPTURE_REQUEST_CODE  && resultCode == Activity.RESULT_OK){
//            ImageView imageView = (ImageView) findViewById(R.id.imageDest);
//            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
//            Log.i("info ","saved");
//            imageView.setImageBitmap(bitmap);

//            Uri takenPhotoUri = getUriForImage();
//            Bitmap bitmap1 = BitmapFactory.decodeFile(takenPhotoUri.getPath());
//            imageView.setImageURI(uriImageResult);

            uri = data.getData();
            String path = data.getData().getPath();
            String uriStr = uri.toString();
            Uri uriReal = Uri.parse(uriStr);
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));

                ImageView imageView = (ImageView) findViewById(R.id.imageDest);
                imageView.setImageURI(uriReal);
                Log.i("uri",uriReal.toString());
                Log.i("uristr",uriStr);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_icon,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_save){
            DestinationSqlHandler destinationSqlHandler = new DestinationSqlHandler(getApplicationContext());
            String namaTempat = edtNamaTempat.getText().toString();
            String kategori = spnKategori.getSelectedItem().toString();
            int harga = Integer.parseInt(edtHarga.getText().toString());
            String deskripsi = edtDesktipsi.getText().toString();
            boolean favorit = checkBoxFavorite.isChecked();
            String gambar = uri.toString();
            String location = koordinat.latitude+"";
            location+=",";
            location+=koordinat.longitude+"";
            Log.i("info",location);
            Destination newDestination = new Destination(namaTempat,deskripsi,harga,favorit,location,gambar);

            if(destinationSqlHandler.addDesination(newDestination)){
                Intent intent = new Intent(AddDestActivity.this,DestDescriptActivity.class);
                intent.putExtra(DestinationSqlHandler.COLUMN_DESTINATION_NAME,namaTempat);
                intent.putExtra(DestinationSqlHandler.COLUMN_DESCRIPTION,deskripsi);
                intent.putExtra(DestinationSqlHandler.COLUMN_COST,harga);
                intent.putExtra(DestinationSqlHandler.COLUMN_PICTURE,gambar);
                intent.putExtra(DestinationSqlHandler.COLUMN_IS_FAVORITE,favorit);
                intent.putExtra(DestinationSqlHandler.COLUMN_LOCATION,location);
                startActivity(intent);
                finish();
                Log.i("save","sekses");
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menuItemSave = menu.findItem(R.id.action_save);
        return super.onPrepareOptionsMenu(menu);
    }
}
