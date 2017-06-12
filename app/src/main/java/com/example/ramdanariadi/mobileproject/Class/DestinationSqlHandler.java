package com.example.ramdanariadi.mobileproject.Class;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by ramdan ariadi on 09/05/2017.
 */

public class DestinationSqlHandler extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "carimakan";
    static final int DATABASE_VERSION = 1;
    static final String TABLE_NAME = "destination";
    public static final String COLUMN_PICTURE = "picture";
    public static final String COLUMN_DESTINATION_NAME = "destination_name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_COST = "cost";
    public static final String COLUMN_IS_FAVORITE = "is_favorite";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLOM_ID = "id";

    public DestinationSqlHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ( "+COLOM_ID+" INTEGER, "+COLUMN_DESTINATION_NAME+" TEXT,"+
                COLUMN_DESCRIPTION+" TEXT, "+COLUMN_COST+" INTEGER, "+COLUMN_IS_FAVORITE+" INTEGER,"+
                COLUMN_PICTURE +" TEXT, "+COLUMN_LOCATION+" TEXT)";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addDesination(Destination destination){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLOM_ID,"1");
        contentValues.put(COLUMN_DESTINATION_NAME,destination.getDestinationName());
        contentValues.put(COLUMN_DESCRIPTION,destination.getDescription());
        contentValues.put(COLUMN_COST,destination.getCost());

        int isFavorit = destination.getFavorite() ? 1 : 0;

        contentValues.put(COLUMN_IS_FAVORITE,isFavorit);
        contentValues.put(COLUMN_PICTURE,destination.getPicture());
        contentValues.put(COLUMN_LOCATION,destination.getLocation());

        Log.i("location add",destination.getLocation());

        if(sqLiteDatabase.insert(TABLE_NAME,null,contentValues) > 0){
            sqLiteDatabase.close();
            return true;
        }else{
            return false;
        }

    }

    public ArrayList<Destination> getAllDestination(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ArrayList<Destination> destinations = new ArrayList<>();
        String SQL = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(SQL,null);
        int destination_name = cursor.getColumnIndex(COLUMN_DESTINATION_NAME);
        int destination_description = cursor.getColumnIndex(COLUMN_DESCRIPTION);
//        int destination_categori = cursor.getColumnIndex(COLUMN)
        int destination_cost = cursor.getColumnIndex(COLUMN_COST);
        int destination_is_favorite = cursor.getColumnIndex(COLUMN_IS_FAVORITE);
        int destination_picture = cursor.getColumnIndex(COLUMN_PICTURE);
        int destination_location = cursor.getColumnIndex(COLUMN_LOCATION);
        while(cursor.moveToNext()){
            destinations.add(new Destination(cursor.getString(destination_name),cursor.getString(destination_description),
                    Integer.parseInt(cursor.getString(destination_cost)), Boolean.parseBoolean(cursor.getString(destination_is_favorite)),
                    cursor.getString(destination_location),cursor.getString(destination_picture)));
            Log.i("location add all",cursor.getString(destination_location));
        }
        return destinations;
    }
}
