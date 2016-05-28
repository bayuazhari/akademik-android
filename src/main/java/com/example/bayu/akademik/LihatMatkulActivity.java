package com.example.bayu.akademik;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LihatMatkulActivity extends ActionBarActivity {
    protected Cursor cursor;
    DBHelper dbHelper;
    Button buttonKembali;
    TextView tvKode, tvNama, tvSks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_matkul);

        dbHelper = new DBHelper(this);
        tvKode = (TextView) findViewById(R.id.textView33);
        tvNama = (TextView) findViewById(R.id.textView34);
        tvSks = (TextView) findViewById(R.id.textView35);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM matkul WHERE nama_matkul = '" +
                getIntent().getStringExtra("nama_matkul") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            cursor.moveToPosition(0);
            tvKode.setText(cursor.getString(0).toString());
            tvNama.setText(cursor.getString(1).toString());
            tvSks.setText(cursor.getString(2).toString());
        }

        buttonKembali = (Button) findViewById(R.id.button18);
        buttonKembali.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
}
