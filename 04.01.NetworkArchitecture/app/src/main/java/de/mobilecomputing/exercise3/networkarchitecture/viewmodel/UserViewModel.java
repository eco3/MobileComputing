package de.mobilecomputing.exercise3.networkarchitecture.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import de.mobilecomputing.exercise3.networkarchitecture.models.User;
import de.mobilecomputing.exercise3.networkarchitecture.repositories.UserRepository;

public class UserViewModel extends AndroidViewModel {
    private final LiveData<List<User>> users;
    private final UserRepository userRepository;

    public UserViewModel(Application application) {
        super(application);

        userRepository = new UserRepository(application);
        users = userRepository.getUsers();
    }

    public LiveData<List<User>> getUsers() {
        return users;
    }

    public LiveData<User> getUser(long id) {
        return userRepository.getUser(id);
    }

    public void saveUser(User user) {
        userRepository.insertUser(user);
    }
}
