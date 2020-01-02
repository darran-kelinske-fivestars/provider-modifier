package com.whereisdarran.autobootcontentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri autoBootUri = Uri.parse("content://com.archer.autobootprovider/autobootinfo");
        Uri launcherFavoritesUri = Uri.parse("content://com.teslacoilsw.launcher.settings/favorites");

        Cursor cursor = getContentResolver().query(launcherFavoritesUri, null, null, null, null);


//        Cursor cursor = getContentResolver().query(autoBootUri, null, "packagename=?", new String[]{"fs.cpay"}, null);
//        if (cursor.moveToFirst()) {
//            ContentValues contentValues = new ContentValues();
//            contentValues.put("grant", 0);
//            getContentResolver().update(autoBootUri, contentValues, "packagename=?", new String[]{"fs.cpay"});
//        }
        cursor.close();
    }
}
