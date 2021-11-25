package de.mobilecomputing.exercise3.networkarchitecture;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import de.mobilecomputing.exercise3.networkarchitecture.repositories.UserAdapter;
import de.mobilecomputing.exercise3.networkarchitecture.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView userListRecyclerView = findViewById(R.id.user_recyclerview);
        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        UserAdapter userAdapter = new UserAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        userListRecyclerView.setLayoutManager(layoutManager);
        userListRecyclerView.setAdapter(userAdapter);

        userViewModel.getUsers().observe(this, userAdapter::updateData);
    }

    public void onClickAddUser(View view) {
        Intent intent = new Intent(this, UserAddActivity.class);
        startActivity(intent);
    }
}