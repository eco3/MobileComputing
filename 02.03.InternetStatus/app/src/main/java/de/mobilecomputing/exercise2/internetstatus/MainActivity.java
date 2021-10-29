package de.mobilecomputing.exercise2.internetstatus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static MainActivity instance;
    public TextView internetStatusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;

        internetStatusTextView = (TextView) findViewById(R.id.internet_status_textview);

        BroadcastReceiver br = new InternetStatusReceiver();

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        this.registerReceiver(br, filter);
    }
    public static MainActivity getInstace(){
        return instance;
    }

    public void updateTextView(String message){
        MainActivity.this.runOnUiThread(() -> internetStatusTextView.setText(message));
    }
}