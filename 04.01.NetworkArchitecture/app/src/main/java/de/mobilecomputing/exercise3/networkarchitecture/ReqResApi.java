package de.mobilecomputing.exercise3.networkarchitecture;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ReqResApi {
    @GET("api/users")
    Call<UserList> getUsers();
}
