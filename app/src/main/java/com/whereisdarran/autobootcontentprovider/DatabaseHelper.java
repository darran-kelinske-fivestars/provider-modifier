package com.whereisdarran.autobootcontentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_PATH = "/data/data/com.whereisdarran.autobootcontentprovider/databases/";
    private static final String DATABASE_NAME = "autoboot.db";
    private static final int DATABASE_VERSION = 1;
    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createDatabase();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

    void createDatabase() {
        boolean databaseExists = checkDatabase();
        if (!databaseExists) {
            copyDatabase();
        }
    }

    private boolean checkDatabase() {
        try {
            final String mPath = DATABASE_PATH + DATABASE_NAME;
            final File file = new File(mPath);
            return file.exists();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void copyDatabase() {
        try {

            InputStream mInputStream = context.getAssets().open(DATABASE_NAME);
            String outFileName = DATABASE_PATH + DATABASE_NAME;
            OutputStream mOutputStream = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = mInputStream.read(buffer)) > 0) {
                mOutputStream.write(buffer, 0, length);
            }
            mOutputStream.flush();
            mOutputStream.close();
            mInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
