package de.mobilecomputing.exercise2.sendmessage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSendMessage(View view) {
        Intent intent = new Intent(this, MessageDisplayActivity.class);
        EditText message = (EditText) findViewById(R.id.message);

        intent.putExtra(MessageDisplayActivity.MESSAGE, message.getText().toString());
        startActivity(intent);
    }
}