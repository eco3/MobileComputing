package de.mobilecomputing.exercise3.networkarchitecture;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "user")
public class User {
    @PrimaryKey
    @SerializedName("id")
    public long id;

    @SerializedName("email")
    public String email;

    @SerializedName("first_name")
    public String firstName;

    @SerializedName("last_name")
    public String lastName;

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
