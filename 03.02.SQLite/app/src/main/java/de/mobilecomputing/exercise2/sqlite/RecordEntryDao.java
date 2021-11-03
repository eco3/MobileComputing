package de.mobilecomputing.exercise2.sqlite;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RecordEntryDao {
    @Query("SELECT * FROM record_entry")
    public List<RecordEntry> loadAllRecordEntries();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertRecordEntry(RecordEntry recordEntry);
}
