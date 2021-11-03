package de.mobilecomputing.exercise2.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SQLDatabase db;
    private ArrayAdapter<RecordEntry> recordEntryArrayAdapter;
    private ArrayList<RecordEntry> recordEntries;

    public EditText mNewRecordTextEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNewRecordTextEdit = (EditText)findViewById(R.id.newRecordTextEdit);

        db = Room.databaseBuilder(getApplicationContext(),SQLDatabase.class,
                            "recordentry_database")
                .allowMainThreadQueries()
                .build();

        recordEntries = new ArrayList<>();
        recordEntryArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, recordEntries);

        ListView listRecordEntries = (ListView) findViewById(R.id.listRecordEntries);
        listRecordEntries.setAdapter(recordEntryArrayAdapter);
    }

    public void onAdd(View view) {
        RecordEntry recordEntry = new RecordEntry();
        recordEntry.record = mNewRecordTextEdit.getText().toString();

        db.recordEntryDao().insertRecordEntry(recordEntry);

        mNewRecordTextEdit.setText("");
    }

    public void onRead(View view) {
        recordEntries.clear();
        recordEntries.addAll(db.recordEntryDao().loadAllRecordEntries());

        recordEntryArrayAdapter.notifyDataSetChanged();
    }
}