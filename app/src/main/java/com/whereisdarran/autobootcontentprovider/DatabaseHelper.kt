package com.whereisdarran.autobootcontentprovider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

class DatabaseHelper(private val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        createDatabase()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) { // TODO Auto-generated method stub
    }

    fun createDatabase() {
        val databaseExists = checkDatabase()
        if (!databaseExists) {
            copyDatabase()
        }
    }

    private fun checkDatabase(): Boolean {
        try {
            val mPath = DATABASE_PATH + DATABASE_NAME
            val file = File(mPath)
            return file.exists()
        } catch (e: SQLiteException) {
            e.printStackTrace()
        }
        return false
    }

    private fun copyDatabase() {
        try {
            val mInputStream = context.assets.open(DATABASE_NAME)
            val outFileName = DATABASE_PATH + DATABASE_NAME
            val mOutputStream: OutputStream = FileOutputStream(outFileName)
            val buffer = ByteArray(1024)
            var length: Int
            while (mInputStream.read(buffer).also { length = it } > 0) {
                mOutputStream.write(buffer, 0, length)
            }
            mOutputStream.flush()
            mOutputStream.close()
            mInputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    companion object {
        private const val DATABASE_PATH = "/data/data/com.whereisdarran.autobootcontentprovider/databases/"
        private const val DATABASE_NAME = "autoboot.db"
        private const val DATABASE_VERSION = 1
    }

}