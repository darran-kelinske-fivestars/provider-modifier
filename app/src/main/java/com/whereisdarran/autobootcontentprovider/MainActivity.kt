package com.whereisdarran.autobootcontentprovider

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.fivestars.rootutil.RootUtil

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Pseudocode
        // Copy database from secure location on lenovo
        // Remove autoboot grant from all products
        // Grant autoboot to selected product
        val databaseHelper = DatabaseHelper(this)
        var cursor = databaseHelper.readableDatabase.rawQuery("select * from autobootinfo where packagename='fs.cpay'", null)
        Log.d("darran", "The cursor size is not empty: " + cursor.moveToFirst())
        cursor = databaseHelper.writableDatabase.rawQuery("UPDATE autobootinfo set grant=1 where packagename='fs.cpay'", null)
        cursor.moveToFirst()
        cursor.close()
        RootUtil.executeAsRoot("asdf")
    }
}