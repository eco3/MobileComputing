package de.mobilecomputing.exercise2.livedata;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "record_entry")
public class RecordEntry {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String record;

    @Override
    public String toString() {
        return record;
    }
}
