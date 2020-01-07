package com.whereisdarran.autobootcontentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri autoBootUri = Uri.parse("content://com.archer.autobootprovider/autobootinfo");
        Uri launcherFavoritesUri = Uri.parse("content://com.teslacoilsw.launcher.settings/favorites");


        DatabaseHelper sqLiteOpenHelper = new DatabaseHelper(this);
//        try {
//            sqLiteOpenHelper.createDatabase();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Cursor cursor = sqLiteOpenHelper.getReadableDatabase().rawQuery("select * from autobootinfo where packagename='fs.cpay'", null);
        Log.d("darran","The cursor size is not empty: " + cursor.moveToFirst());
        cursor = sqLiteOpenHelper.getWritableDatabase().rawQuery("UPDATE autobootinfo set grant=1 where packagename='fs.cpay'", null);
        cursor.moveToFirst();
        cursor.close();


//        Cursor cursor = getContentResolver().query(autoBootUri, null, "packagename=?", new String[]{"fs.cpay"}, null);
//        if (cursor.moveToFirst()) {
//            ContentValues contentValues = new ContentValues();
//            contentValues.put("grant", 0);
//            getContentResolver().update(autoBootUri, contentValues, "packagename=?", new String[]{"fs.cpay"});
//        }
//        cursor.close();
    }
}
