package de.mobilecomputing.exercise3.networkarchitecture;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import de.mobilecomputing.exercise3.networkarchitecture.reqresapi.ApiBuilder;
import de.mobilecomputing.exercise3.networkarchitecture.reqresapi.ReqResApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private static final String TAG = "UserRepository";
    private final ReqResApi api;

    private MutableLiveData<List<User>> userListLiveData = new MutableLiveData<>();
    private MutableLiveData<User> userLiveData = new MutableLiveData<>();

    public UserRepository(Application application) {
        api = ApiBuilder.create(ReqResApi.class);
    }

    public LiveData<List<User>> getUsers() {
        api.getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                Log.d(TAG, "onResponse: " + response.message());

                if (response.body() != null) {
                    userListLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: Failed to fetch user list from server.");
            }
        });

        return userListLiveData;
    }

    public LiveData<User> getUser(int userId) {
        api.getUser(userId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        return userLiveData;
    }
}
