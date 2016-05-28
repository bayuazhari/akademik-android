package com.example.bayu.akademik;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TambahMhsActivity extends ActionBarActivity {
    DBHelper dbHelper;
    Button buttonSimpan, buttonKembali;
    EditText textNPM, textNama, textProdi, textKelas, textAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_mhs);

        dbHelper = new DBHelper(this);
        textNPM = (EditText) findViewById(R.id.editText3);
        textNama = (EditText) findViewById(R.id.editText4);
        textProdi = (EditText) findViewById(R.id.editText5);
        textKelas = (EditText) findViewById(R.id.editText6);
        textAlamat = (EditText) findViewById(R.id.editText7);
        buttonSimpan = (Button) findViewById(R.id.button7);
        buttonKembali = (Button) findViewById(R.id.button8);

        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (textNPM.getText().toString().trim().length() == 0
                        || textNama.getText().toString().trim().length() == 0
                        || textProdi.getText().toString().trim().length() == 0
                        || textKelas.getText().toString().trim().length() == 0
                        || textAlamat.getText().toString().trim().length() == 0) {
                    Toast.makeText(TambahMhsActivity.this, "Mohon datanya dilengkapi terlebih dahulu", Toast.LENGTH_LONG).show();
                } else {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.execSQL("insert into mahasiswa(npm, nama, prodi, kelas, alamat) values('" +
                            textNPM.getText().toString() + "','" +
                            textNama.getText().toString() + "','" +
                            textProdi.getText().toString() + "','" +
                            textKelas.getText().toString() + "','" +
                            textAlamat.getText().toString() + "')");
                    Toast.makeText(getApplicationContext(), "Data berhasil diinput", Toast.LENGTH_LONG).show();
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
