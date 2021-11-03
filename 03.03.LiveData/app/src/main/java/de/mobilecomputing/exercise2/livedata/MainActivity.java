package de.mobilecomputing.exercise2.livedata;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import de.mobilecomputing.exercise2.livedata.R;

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

        final Observer<List<RecordEntry>> recordEntriesObserver = newRecordEntries -> {
            recordEntries.clear();
            recordEntries.addAll(newRecordEntries);

            recordEntryArrayAdapter.notifyDataSetChanged();
        };

        db.recordEntryDao().loadAllRecordEntries().observe(this, recordEntriesObserver);
    }

    public void onAdd(View view) {
        RecordEntry recordEntry = new RecordEntry();
        recordEntry.record = mNewRecordTextEdit.getText().toString();

        db.recordEntryDao().insertRecordEntry(recordEntry);

        mNewRecordTextEdit.setText("");
    }
}