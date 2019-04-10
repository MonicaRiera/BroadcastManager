package com.example.broadcastmanager;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnReceiver;
    private SystemBroadcastReceiver mySystemReceiver;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnReceiver = findViewById(R.id.activity_main__btn__enable_receiver);
        btnReceiver.setOnClickListener(this);

        constraintLayout = findViewById(R.id.activity_main__cl_layout);
        constraintLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main__btn__enable_receiver:
                this.mySystemReceiver = new SystemBroadcastReceiver();
                this.registerReceiver(this.mySystemReceiver, new IntentFilter(Intent.ACTION_BATTERY_LOW));
            case R.id.activity_main__cl_layout:
                Intent customIntent = new Intent();
                customIntent.setAction("org.bts_netmind.CUSTOM_INTENT");
                this.sendBroadcast(customIntent);
            default:
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mySystemReceiver);
    }
}
