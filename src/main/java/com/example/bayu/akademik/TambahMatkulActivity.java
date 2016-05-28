package com.example.bayu.akademik;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TambahMatkulActivity extends ActionBarActivity {
    DBHelper dbHelper;
    Button buttonSimpan, buttonKembali;
    EditText textKode, textNama, textSks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_matkul);

        dbHelper = new DBHelper(this);
        textKode = (EditText) findViewById(R.id.editText13);
        textNama = (EditText) findViewById(R.id.editText14);
        textSks = (EditText) findViewById(R.id.editText15);
        buttonSimpan = (Button) findViewById(R.id.button16);
        buttonKembali = (Button) findViewById(R.id.button17);

        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (textKode.getText().toString().trim().length() == 0
                        || textNama.getText().toString().trim().length() == 0
                        || textSks.getText().toString().trim().length() == 0) {
                    Toast.makeText(TambahMatkulActivity.this, "Mohon datanya dilengkapi terlebih dahulu", Toast.LENGTH_LONG).show();
                } else {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.execSQL("insert into matkul(kode_matkul, nama_matkul, sks) values('" +
                            textKode.getText().toString() + "','" +
                            textNama.getText().toString() + "','" +
                            textSks.getText().toString() + "')");
                    Toast.makeText(getApplicationContext(), "Data berhasil diinput", Toast.LENGTH_LONG).show();
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
