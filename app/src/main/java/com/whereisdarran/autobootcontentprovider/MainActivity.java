package com.whereisdarran.autobootcontentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Pseudocode
        // Copy database from secure location on lenovo
        // Remove autoboot grant from all products
        // Grant autoboot to selected product


        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        Cursor cursor = databaseHelper.getReadableDatabase().rawQuery("select * from autobootinfo where packagename='fs.cpay'", null);
        Log.d("darran","The cursor size is not empty: " + cursor.moveToFirst());
        cursor = databaseHelper.getWritableDatabase().rawQuery("UPDATE autobootinfo set grant=1 where packagename='fs.cpay'", null);
        cursor.moveToFirst();
        cursor.close();

    }
}
