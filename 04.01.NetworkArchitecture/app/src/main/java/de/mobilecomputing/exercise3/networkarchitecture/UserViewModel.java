package de.mobilecomputing.exercise3.networkarchitecture;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private final LiveData<List<User>> users;
    private final UserRepository repository;

    public UserViewModel(Application application) {
        super(application);

        repository = new UserRepository(application);
        users = repository.getUsers();
    }

    public LiveData<List<User>> getUsers() {
        return users;
    }
}
