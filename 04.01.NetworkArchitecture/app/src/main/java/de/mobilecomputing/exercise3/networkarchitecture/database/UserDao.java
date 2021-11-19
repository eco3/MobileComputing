package de.mobilecomputing.exercise3.networkarchitecture.database;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import de.mobilecomputing.exercise3.networkarchitecture.User;

@Dao
public interface UserDao {
    @Insert(onConflict = REPLACE)
    void insertUsers(List<User> users);

    @Query("SELECT * FROM user")
    LiveData<List<User>> getUsers();

    @Query("SELECT COUNT(*) FROM user WHERE id == :id")
    boolean hasUser(long id);

    @Insert(onConflict = REPLACE)
    void insertUser(User user);

    @Query("SELECT * FROM user WHERE id == :id")
    LiveData<User> getUser(long id);
}
