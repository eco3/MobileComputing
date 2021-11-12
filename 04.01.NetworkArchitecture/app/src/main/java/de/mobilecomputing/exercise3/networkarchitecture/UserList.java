package de.mobilecomputing.exercise3.networkarchitecture;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserList {
    @SerializedName("data")
    public List<User> users;
}
