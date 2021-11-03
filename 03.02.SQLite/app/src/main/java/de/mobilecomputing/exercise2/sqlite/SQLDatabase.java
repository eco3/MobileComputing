package de.mobilecomputing.exercise2.sqlite;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {RecordEntry.class}, version = 1)
public abstract class SQLDatabase extends RoomDatabase {
    public abstract RecordEntryDao recordEntryDao();
}