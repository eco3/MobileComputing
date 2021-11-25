package de.mobilecomputing.exercise3.networkarchitecture;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import de.mobilecomputing.exercise3.networkarchitecture.models.User;
import de.mobilecomputing.exercise3.networkarchitecture.viewmodel.UserViewModel;

public class UserViewActivity extends AppCompatActivity {
    public static final String EXTRA_USER_ID = "USER_ID";

    private TextView fullnameTextView;
    private TextView emailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);

        fullnameTextView = (TextView) findViewById(R.id.fullname_textview);
        emailTextView = (TextView) findViewById(R.id.email_textview);

        Intent intent = getIntent();
        long fetchedUserId = intent.getLongExtra(EXTRA_USER_ID, -1);

        if (fetchedUserId == -1) {
            throw new IllegalStateException("No user id was set.");
        }

        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        userViewModel.getUser(fetchedUserId).observe(this, (User user) -> {
            fullnameTextView.setText(user.toString());
            emailTextView.setText(user.email);
        });
    }
}
