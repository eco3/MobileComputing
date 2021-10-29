package de.mobilecomputing.exercise2.savereadfiles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private static final String FILENAME = "saveread_content";

    private File file;

    public EditText contentTextEdit;
    public TextView contentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentTextEdit = (EditText)findViewById(R.id.contentTextEdit);
        contentTextView = (TextView)findViewById(R.id.contentTextView);

        file = new File(this.getApplicationContext().getFilesDir(), FILENAME);
    }

    public void onSave(View view) {
        try {
            FileWriter fw = new FileWriter(file);

            fw.write(contentTextEdit.getText().toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        contentTextEdit.setText("");
        contentTextView.setText("");
    }

    public void onRead(View view) {
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);

            StringBuilder stringBuilder = new StringBuilder();
            String line = reader.readLine();

            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }

            contentTextView.setText(stringBuilder.toString());

            reader.close();
            isr.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}