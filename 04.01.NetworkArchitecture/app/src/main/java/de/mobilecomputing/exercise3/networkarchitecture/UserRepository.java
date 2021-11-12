package de.mobilecomputing.exercise3.networkarchitecture;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private static final String TAG = "UserRepository";
    private static final UserRepository instance = new UserRepository();
    private final ReqResApi api;

    private MutableLiveData<List<User>> userListLiveData = new MutableLiveData<>();

    public static UserRepository getInstance() {
        return instance;
    }

    private UserRepository() {
        api = ApiBuilder.create(ReqResApi.class);
    }

    public LiveData<List<User>> getUsers() {
        api.getUsers().enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(@NonNull Call<UserList> call, @NonNull Response<UserList> response) {
                Log.d(TAG, "onResponse: " + response.message());

                if (response.body() != null) {
                    userListLiveData.setValue(response.body().users);
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserList> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: Failed to fetch user list from server.");
            }
        });

        return userListLiveData;
    }
}
