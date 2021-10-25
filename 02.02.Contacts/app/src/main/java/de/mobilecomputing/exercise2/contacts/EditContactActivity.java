package de.mobilecomputing.exercise2.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EditContactActivity extends AppCompatActivity {
    public static final String CONTACT_ID = "CONTACT_ID";
    public static final String CONTACT_SERIALIZED_OBJECT = "CONTACT_SERIALIZED_OBJECT";

    private int contactID;

    public TextView nameTextView;
    public TextView emailTextView;
    public TextView telephoneTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        Intent intent = getIntent();
        Contact contactToEdit = (Contact) intent.getSerializableExtra(CONTACT_SERIALIZED_OBJECT);
        contactID = intent.getIntExtra(CONTACT_ID, -1);

        if (contactID == -1) {
            throw new IllegalStateException();
        }

        nameTextView = (TextView)findViewById(R.id.name_edit);
        emailTextView = (TextView)findViewById(R.id.email_edit);
        telephoneTextView = (TextView)findViewById(R.id.telephone_edit);

        nameTextView.setText(contactToEdit.getName());
        emailTextView.setText(contactToEdit.getEmail());
        telephoneTextView.setText(contactToEdit.getTelephone());
    }

    public void onSaveContact(View view) {
        Contact editedContact = new Contact(
                nameTextView.getText().toString(),
                telephoneTextView.getText().toString(),
                emailTextView.getText().toString()
        );

        Intent intent = new Intent();
        intent.putExtra(CONTACT_ID, contactID);
        intent.putExtra(CONTACT_SERIALIZED_OBJECT, editedContact);

        setResult(RESULT_OK, intent);
        finish();
    }

    public void onCancel(View view) {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }
}