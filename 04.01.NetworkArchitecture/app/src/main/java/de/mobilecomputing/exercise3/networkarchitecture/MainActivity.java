package de.mobilecomputing.exercise3.networkarchitecture;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private RecyclerView userListRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userListRecyclerView = findViewById(R.id.user_recyclerview);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        UserAdapter userAdapter = new UserAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        userListRecyclerView.setLayoutManager(layoutManager);
        userListRecyclerView.setAdapter(userAdapter);

        userViewModel.getUsers().observe(this, userAdapter::updateData);
    }
}