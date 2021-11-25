package de.mobilecomputing.exercise3.networkarchitecture;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import de.mobilecomputing.exercise3.networkarchitecture.models.User;
import de.mobilecomputing.exercise3.networkarchitecture.viewmodel.UserViewModel;

public class UserAddActivity extends AppCompatActivity {

    private TextInputEditText firstnameTextEdit;
    private TextInputEditText lastnameTextEdit;
    private TextInputEditText emailTextEdit;

    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_add);

        firstnameTextEdit = (TextInputEditText) findViewById(R.id.firstname_textinput);
        lastnameTextEdit = (TextInputEditText) findViewById(R.id.lastname_textinput);
        emailTextEdit = (TextInputEditText) findViewById(R.id.email_textinput);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
    }

    public void onClickSaveUser(View view) {
        if (firstnameTextEdit.getText().toString().equals("") ||
            lastnameTextEdit.getText().toString().equals("") ||
            emailTextEdit.getText().toString().equals("")
        ) {
            Toast.makeText(getApplicationContext(), "Please fill in name and email.", Toast.LENGTH_SHORT).show();
        }

        User newUser = new User();
        newUser.firstName = firstnameTextEdit.getText().toString();
        newUser.lastName = lastnameTextEdit.getText().toString();
        newUser.email = emailTextEdit.getText().toString();

        userViewModel.saveUser(newUser);

        finish();
    }
}