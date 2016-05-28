package com.example.bayu.akademik;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LihatMhsActivity extends ActionBarActivity {
    protected Cursor cursor;
    DBHelper dbHelper;
    Button buttonKembali;
    TextView tvNPM, tvNama, tvProdi, tvKelas, tvAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_mhs);

        dbHelper = new DBHelper(this);
        tvNPM = (TextView) findViewById(R.id.textView14);
        tvNama = (TextView) findViewById(R.id.textView15);
        tvProdi = (TextView) findViewById(R.id.textView16);
        tvKelas = (TextView) findViewById(R.id.textView17);
        tvAlamat = (TextView) findViewById(R.id.textView18);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM mahasiswa WHERE nama = '" +
                getIntent().getStringExtra("nama") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            cursor.moveToPosition(0);
            tvNPM.setText(cursor.getString(0).toString());
            tvNama.setText(cursor.getString(1).toString());
            tvProdi.setText(cursor.getString(2).toString());
            tvKelas.setText(cursor.getString(3).toString());
            tvAlamat.setText(cursor.getString(4).toString());
        }

        buttonKembali = (Button) findViewById(R.id.button9);
        buttonKembali.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
}
