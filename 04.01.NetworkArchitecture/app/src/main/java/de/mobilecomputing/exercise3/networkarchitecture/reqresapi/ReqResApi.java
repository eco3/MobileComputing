package de.mobilecomputing.exercise3.networkarchitecture.reqresapi;

import java.util.List;

import de.mobilecomputing.exercise3.networkarchitecture.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ReqResApi {
    @GET("api/users?per_page=1000")
    Call<List<User>> getUsers();

    @GET("api/users/{id}")
    Call<User> getUser(@Path("id") long userId);

    @POST("api/users")
    Call<User> postUser(@Body User user);
}
