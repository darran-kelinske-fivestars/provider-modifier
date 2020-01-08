package com.whereisdarran.autobootcontentprovider

import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fivestars.rootutil.RootUtil
import java.io.File


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val copiedDatabase: File = File.createTempFile("autoboot", "db", cacheDir)
        RootUtil.executeAsRoot("cp $AUTOBOOT_DATABASE_PATH ${copiedDatabase.absolutePath}")
        val sqLiteDatabase = openDataBase(copiedDatabase)
        val cursor = sqLiteDatabase.rawQuery("UPDATE autobootinfo set grant=1 where packagename= ?", arrayOf("fs.cpay"))
        cursor.moveToFirst()
        cursor.close()

        RootUtil.executeAsRoot("cp ${copiedDatabase.absolutePath} $AUTOBOOT_DATABASE_PATH")
    }

    @Throws(SQLException::class)
    fun openDataBase(database: File): SQLiteDatabase {
        return SQLiteDatabase.openDatabase(database.absolutePath, null,
                SQLiteDatabase.OPEN_READWRITE)
    }

    companion object {
        private const val AUTOBOOT_DATABASE_PATH = "/data/data/com.archer.providers.autoboot/databases/autoboot.db"
    }
}