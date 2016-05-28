package com.example.bayu.akademik;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateDosenActivity extends ActionBarActivity {
    protected Cursor cursor;
    DBHelper dbHelper;
    Button buttonUpdate, buttonKembali;
    EditText textNik, textNama, textStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_dosen);

        dbHelper = new DBHelper(this);
        textNik = (EditText) findViewById(R.id.editText22);
        textNama = (EditText) findViewById(R.id.editText23);
        textStatus = (EditText) findViewById(R.id.editText24);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM dosen WHERE nama = '" +
                getIntent().getStringExtra("nama") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            cursor.moveToPosition(0);
            textNik.setText(cursor.getString(0).toString());
            textNama.setText(cursor.getString(1).toString());
            textStatus.setText(cursor.getString(2).toString());
        }
        buttonUpdate = (Button) findViewById(R.id.button28);
        buttonKembali = (Button) findViewById(R.id.button29);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(textNik.getText().toString().trim().length()==0
                        || textNama.getText().toString().trim().length()==0
                        || textStatus.getText().toString().trim().length()==0)
                {
                    Toast.makeText(UpdateDosenActivity.this, "Mohon datanya dilengkapi terlebih dahulu", Toast.LENGTH_LONG).show();
                } else {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.execSQL("update dosen set nama='" +
                            textNama.getText().toString() + "', status='" +
                            textStatus.getText().toString() + "' where nik='" +
                            textNik.getText().toString() + "'");
                    Toast.makeText(getApplicationContext(), "Data berhasil diupdate", Toast.LENGTH_LONG).show();
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
