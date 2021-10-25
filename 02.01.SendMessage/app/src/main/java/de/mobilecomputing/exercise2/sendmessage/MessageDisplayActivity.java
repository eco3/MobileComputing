package de.mobilecomputing.exercise2.sendmessage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MessageDisplayActivity extends AppCompatActivity {

    public static final String MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_display);

        Intent intent = getIntent();
        TextView messageView = (TextView)findViewById(R.id.message);

        messageView.setText(intent.getStringExtra(MESSAGE));
    }
}