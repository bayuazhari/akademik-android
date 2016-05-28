package com.example.bayu.akademik;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateMhsActivity extends ActionBarActivity {
    protected Cursor cursor;
    DBHelper dbHelper;
    Button buttonUpdate, buttonKembali;
    EditText textNPM, textNama, textProdi, textKelas, textAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_mhs);

        dbHelper = new DBHelper(this);
        textNPM = (EditText) findViewById(R.id.editText8);
        textNama = (EditText) findViewById(R.id.editText9);
        textProdi = (EditText) findViewById(R.id.editText10);
        textKelas = (EditText) findViewById(R.id.editText11);
        textAlamat = (EditText) findViewById(R.id.editText12);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM mahasiswa WHERE nama = '" +
                getIntent().getStringExtra("nama") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            cursor.moveToPosition(0);
            textNPM.setText(cursor.getString(0).toString());
            textNama.setText(cursor.getString(1).toString());
            textProdi.setText(cursor.getString(2).toString());
            textKelas.setText(cursor.getString(3).toString());
            textAlamat.setText(cursor.getString(4).toString());
        }
        buttonUpdate = (Button) findViewById(R.id.button10);
        buttonKembali = (Button) findViewById(R.id.button11);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(textNPM.getText().toString().trim().length()==0
                        || textNama.getText().toString().trim().length()==0
                        || textProdi.getText().toString().trim().length()==0
                        || textKelas.getText().toString().trim().length()==0
                        || textAlamat.getText().toString().trim().length()==0)
                {
                    Toast.makeText(UpdateMhsActivity.this, "Mohon datanya dilengkapi terlebih dahulu", Toast.LENGTH_LONG).show();
                } else {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.execSQL("update mahasiswa set nama='" +
                            textNama.getText().toString() + "', prodi='" +
                            textProdi.getText().toString() + "', kelas='" +
                            textKelas.getText().toString() + "', alamat='" +
                            textAlamat.getText().toString() + "' where npm='" +
                            textNPM.getText().toString() + "'");
                    Toast.makeText(getApplicationContext(), "Data berhasil diupdate", Toast.LENGTH_LONG).show();
                    DataMhsActivity.dm.RefreshList();
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
