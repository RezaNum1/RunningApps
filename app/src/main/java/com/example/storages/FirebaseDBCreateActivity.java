package com.example.storages;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class FirebaseDBCreateActivity extends AppCompatActivity {

    // variable yang merefers ke Firebase Realtime Database
    private DatabaseReference database;

    // variable fields EditText dan Button
    private Button btSubmit;
    private EditText etPosition;
    private EditText etDestination;
    private EditText etJarak;
    private EditText etWaktutempuh;
    private EditText etTanggal;
    private TextView tvJudul;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_create);

        // inisialisasi fields EditText dan Button
        etPosition = (EditText) findViewById(R.id.et_position);
        etDestination = (EditText) findViewById(R.id.et_destination);
        etJarak = (EditText) findViewById(R.id.et_jarak);
        etWaktutempuh = (EditText) findViewById(R.id.et_waktutempuh);
        etTanggal = (EditText) findViewById(R.id.et_tanggal);
        btSubmit = (Button) findViewById(R.id.bt_submit);
        tvJudul = (TextView) findViewById(R.id.tv_judul);

        // mengambil referensi ke Firebase Database
        database = FirebaseDatabase.getInstance().getReference();

        final Running running = (Running) getIntent().getSerializableExtra("data");

        if (running != null) {
            btSubmit.setText("Update");
            tvJudul.setText("Update Record");
            etPosition.setText(running.getPosition());
            etDestination.setText(running.getDestination());
            etJarak.setText(running.getJarak());
            etWaktutempuh.setText(running.getWaktutempuh());
            etTanggal.setText(running.getTanggal());
            btSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    running.setPosition(etPosition.getText().toString());
                    running.setDestination(etDestination.getText().toString());
                    running.setJarak(etJarak.getText().toString());
                    running.setWaktutempuh(etWaktutempuh.getText().toString());
                    running.setTanggal(etTanggal.getText().toString());

                    updateBarang(running);
                }
            });
        }else {

            // kode yang dipanggil ketika tombol Submit diklik
            btSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!isEmpty(etPosition.getText().toString()) && !isEmpty(etDestination.getText().toString()) && !isEmpty(etJarak.getText().toString()) && !isEmpty(etWaktutempuh.getText().toString()) && !isEmpty(etTanggal.getText().toString())) {

                        submitBarang(new Running(etPosition.getText().toString(), etDestination.getText().toString(), etJarak.getText().toString(), etWaktutempuh.getText().toString(), etTanggal.getText().toString()));
                    } else {
                        Snackbar.make(findViewById(R.id.bt_submit), "Data running tidak boleh kosong", Snackbar.LENGTH_LONG).show();

                        InputMethodManager imm = (InputMethodManager)
                                getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(
                                etPosition.getWindowToken(), 0);
                    }
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(
                            etPosition.getWindowToken(), 0);
                }
            });
        }
    }

    private boolean isEmpty(String s) {
        // Cek apakah ada fields yang kosong, sebelum disubmit
        return TextUtils.isEmpty(s);
    }

    private void updateBarang(Running running){
        database.child("running")
                .child(running.getKey())
                .setValue(running)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Snackbar.make(findViewById(R.id.bt_submit), "Data Berhasil Di Update", Snackbar.LENGTH_LONG).setAction("Oke", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                            }
                        }).show();
                    }
                });
    }


    private void submitBarang(Running running){
        database.child("running").push().setValue(running).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                etPosition.setText("");
                etDestination.setText("");
                etJarak.setText("");
                etWaktutempuh.setText("");
                etTanggal.setText("");
                Snackbar.make(findViewById(R.id.bt_submit), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, FirebaseDBCreateActivity.class);
    }
}
