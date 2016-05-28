package com.example.bayu.akademik;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TambahDosenActivity extends ActionBarActivity {
    DBHelper dbHelper;
    Button buttonSimpan, buttonKembali;
    EditText textNik, textNama, textStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_dosen);

        dbHelper = new DBHelper(this);
        textNik = (EditText) findViewById(R.id.editText19);
        textNama = (EditText) findViewById(R.id.editText20);
        textStatus = (EditText) findViewById(R.id.editText21);
        buttonSimpan = (Button) findViewById(R.id.button25);
        buttonKembali = (Button) findViewById(R.id.button26);

        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (textNik.getText().toString().trim().length() == 0
                        || textNama.getText().toString().trim().length() == 0
                        || textStatus.getText().toString().trim().length() == 0) {
                    Toast.makeText(TambahDosenActivity.this, "Mohon datanya dilengkapi terlebih dahulu", Toast.LENGTH_LONG).show();
                } else {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.execSQL("insert into dosen(nik, nama, status) values('" +
                            textNik.getText().toString() + "','" +
                            textNama.getText().toString() + "','" +
                            textStatus.getText().toString() + "')");
                    Toast.makeText(getApplicationContext(), "Data berhasil diinput", Toast.LENGTH_LONG).show();
                    DataDosenActivity.dd.RefreshList();
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
