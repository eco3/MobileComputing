package de.mobilecomputing.exercise3.networkarchitecture.reqresapi;

import java.util.List;

import de.mobilecomputing.exercise3.networkarchitecture.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ReqResApi {
    @GET("api/users?per_page=1000")
    Call<List<User>> getUsers();

    @GET("api/users/{id}")
    Call<User> getUser(@Path("id") int userId);
}
