package com.example.bayu.akademik;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateMatkulActivity extends ActionBarActivity {
    protected Cursor cursor;
    DBHelper dbHelper;
    Button buttonUpdate, buttonKembali;
    EditText textKode, textNama, textSks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_matkul);

        dbHelper = new DBHelper(this);
        textKode = (EditText) findViewById(R.id.editText16);
        textNama = (EditText) findViewById(R.id.editText17);
        textSks = (EditText) findViewById(R.id.editText18);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM matkul WHERE nama_matkul = '" +
                getIntent().getStringExtra("nama_matkul") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            cursor.moveToPosition(0);
            textKode.setText(cursor.getString(0).toString());
            textNama.setText(cursor.getString(1).toString());
            textSks.setText(cursor.getString(2).toString());
        }
        buttonUpdate = (Button) findViewById(R.id.button19);
        buttonKembali = (Button) findViewById(R.id.button20);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(textKode.getText().toString().trim().length()==0
                        || textNama.getText().toString().trim().length()==0
                        || textSks.getText().toString().trim().length()==0)
                {
                    Toast.makeText(UpdateMatkulActivity.this, "Mohon datanya dilengkapi terlebih dahulu", Toast.LENGTH_LONG).show();
                } else {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.execSQL("update matkul set nama_matkul='" +
                            textNama.getText().toString() + "', sks='" +
                            textSks.getText().toString() + "' where kode_matkul='" +
                            textKode.getText().toString() + "'");
                    Toast.makeText(getApplicationContext(), "Data berhasil diupdate", Toast.LENGTH_LONG).show();
                    DataMatkulActivity.dmk.RefreshList();
                    finish();
                }
            }
        });

        buttonKembali.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
}
