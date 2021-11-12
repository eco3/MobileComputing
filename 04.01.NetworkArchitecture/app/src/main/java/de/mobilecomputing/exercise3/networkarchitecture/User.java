package de.mobilecomputing.exercise3.networkarchitecture;

import com.google.gson.annotations.SerializedName;

public class User {
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
