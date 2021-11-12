package de.mobilecomputing.exercise3.networkarchitecture;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class UserViewModel extends ViewModel {
    private LiveData<List<User>> users;
    private UserRepository repository;

    public UserViewModel() {
        super();

        repository = UserRepository.getInstance();
        users = repository.getUsers();
    }

    public LiveData<List<User>> getUsers() {
        return users;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
