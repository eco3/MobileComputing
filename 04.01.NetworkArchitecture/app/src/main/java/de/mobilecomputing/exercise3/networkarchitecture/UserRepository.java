package de.mobilecomputing.exercise3.networkarchitecture;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import de.mobilecomputing.exercise3.networkarchitecture.database.UserDao;
import de.mobilecomputing.exercise3.networkarchitecture.database.UserDatabase;
import de.mobilecomputing.exercise3.networkarchitecture.reqresapi.ApiBuilder;
import de.mobilecomputing.exercise3.networkarchitecture.reqresapi.ReqResApi;
import retrofit2.Response;

public class UserRepository {
    private static final String TAG = "UserRepository";
    private final ReqResApi reqResApi;
    private final UserDao userDao;
    private final Executor executor;

    public UserRepository(Application application) {
        UserDatabase db = UserDatabase.getDatabase(application);

        reqResApi = ApiBuilder.create(ReqResApi.class);
        userDao = db.userDao();
        executor = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<User>> getUsers() {
        refreshUsers();
        return userDao.getUsers();
    }

    public LiveData<User> getUser(long id) {
        refreshUser(id);
        return userDao.getUser(id);
    }

    private void refreshUser(final long id) {
        executor.execute(() -> {
            if (userDao.hasUser(id)) {
                try {
                    Response<User> fetchedUser = reqResApi.getUser(id).execute();

                    userDao.saveUser(fetchedUser.body());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void refreshUsers() {
        executor.execute(() -> {
            try {
                Response<List<User>> fetchedUsers = reqResApi.getUsers().execute();

                userDao.insertUsers(fetchedUsers.body());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


}
