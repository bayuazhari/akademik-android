package com.example.bayu.akademik;

/**
 * Created by Bayu on 5/28/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "akademik.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USER = "user";
    private static final String TABLE_MHS = "mahasiswa";
    private static final String TABLE_MATKUL = "matkul";
    private static final String TABLE_DOSEN = "dosen";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private SQLiteDatabase db;

    private static final String CREATE_TABLE_USER = "create table " + TABLE_USER +
            "(username string primary key, password text);";

    private static final String CREATE_TABLE_MHS = "create table " + TABLE_MHS +
            "(npm integer primary key, " +
            "nama varchar(50) not null, " +
            "prodi varchar(20) not null," +
            "kelas varchar(5) not null, " +
            "alamat varchar(50) not null);";

    private static final String CREATE_TABLE_MATKUL = "create table " + TABLE_MATKUL +
            "(kode_matkul varchar(10) primary key, " +
            "nama_matkul varchar(50) not null, " +
            "sks integer not null);";

    private static final String CREATE_TABLE_DOSEN = "create table " + TABLE_DOSEN +
            "(nik integer primary key, " +
            "nama varchar(50) not null, " +
            "status varchar(20) not null);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_MHS);
        db.execSQL(CREATE_TABLE_MATKUL);
        db.execSQL(CREATE_TABLE_DOSEN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(), "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MHS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATKUL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOSEN);
        onCreate(db);
    }

    public void SimpanUser(String Username, String Password) {
        ContentValues values = new ContentValues();
        values.put(USERNAME, Username);
        values.put(PASSWORD, Password);
        db.insert(TABLE_USER, null, values);
    }
    public void HapusUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_USER);
    }
}