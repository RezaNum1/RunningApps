package com.example.storages;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FirebaseDBReadSingleActivity extends AppCompatActivity {

    private Button btSubmit;
    private EditText etPosition;
    private EditText etDestination;
    private EditText etJarak;
    private EditText etWaktutempuh;
    private EditText etTanggal;
    private TextView tv_judul;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_create);

        etPosition = (EditText) findViewById(R.id.et_position);
        etDestination = (EditText) findViewById(R.id.et_destination);
        etJarak = (EditText) findViewById(R.id.et_jarak);
        etWaktutempuh = (EditText) findViewById(R.id.et_waktutempuh);
        etTanggal = (EditText) findViewById(R.id.et_tanggal);
        btSubmit = (Button) findViewById(R.id.bt_submit);
        tv_judul = (TextView) findViewById(R.id.tv_judul);

        etPosition.setEnabled(false);
        etDestination.setEnabled(false);
        etJarak.setEnabled(false);
        etWaktutempuh.setEnabled(false);
        etTanggal.setEnabled(false);
        btSubmit.setVisibility(View.GONE);

        Running running = (Running) getIntent().getSerializableExtra("data");
        if(running !=null){
            etPosition.setText(running.getPosition());
            etDestination.setText(running.getDestination());
            etJarak.setText(running.getJarak());
            etWaktutempuh.setText(running.getWaktutempuh());
            etTanggal.setText(running.getTanggal());
            tv_judul.setText("Detail " + running.getPosition());
        }
    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, FirebaseDBReadSingleActivity.class);
    }
}
