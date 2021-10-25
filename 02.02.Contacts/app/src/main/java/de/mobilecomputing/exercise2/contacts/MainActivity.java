package de.mobilecomputing.exercise2.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    static final int EDIT_CONTACT_REQUEST_CODE = 0;

    ArrayAdapter<Contact> contactListAdapter;

    public Contact[] contacts = {
            new Contact("Brooke Cox", "+1 312-929-3628", "brooke.cox@kangaroo.com"),
            new Contact("Alexander Browning", "+1 228-368-4323", "alexander.browning@camel.com"),
            new Contact("Jocelyn Chambers", "+1 206-627-1402", "jocelyn.chambers@panda.com"),
            new Contact("Bobby Grant", "+1 425-321-2987", "bobby.grant@rabbit.com"),
            new Contact("Lester Weiss", "+1 582-482-3002", "lester.weiss@owl.com"),
            new Contact("Coy Stokes", "+1 505-293-2255", "coy.stokes@fish.com"),
            new Contact("Nora Sparks", "+1 217-631-1450", "nora.sparks@bird.com"),
            new Contact("Stephanie Hatfield", "+1 203-276-0168", "stephanie.hatfield@tiger.com"),
            new Contact("Sharlene Moran", "+1 334-928-2809", "sharlene.moran@lobster.com"),
            new Contact("Johnson Molina", "+1 217-846-8249", "johnson.molina@pig.com"),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contacts);
        contactListAdapter.sort(Comparator.comparing(Contact::getName));

        AdapterView.OnItemClickListener contactClickListener =
                (listView, itemView, position, id) -> {
                    Intent intent = new Intent(MainActivity.this, EditContactActivity.class);
                    intent.putExtra(EditContactActivity.CONTACT_ID, position);
                    intent.putExtra(EditContactActivity.CONTACT_SERIALIZED_OBJECT, contacts[position]);

                    //noinspection deprecation
                    startActivityForResult(intent, EDIT_CONTACT_REQUEST_CODE);
                };

        ListView listContacts = (ListView) findViewById(R.id.list_contacts);
        listContacts.setAdapter(contactListAdapter);
        listContacts.setOnItemClickListener(contactClickListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_CONTACT_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                int contactID = data.getIntExtra(EditContactActivity.CONTACT_ID, -1);
                Contact editedContact = (Contact) data.getSerializableExtra(EditContactActivity.CONTACT_SERIALIZED_OBJECT);

                contacts[contactID].setName(editedContact.getName());
                contacts[contactID].setEmail(editedContact.getEmail());
                contacts[contactID].setTelephone(editedContact.getTelephone());

                contactListAdapter.notifyDataSetChanged();
                contactListAdapter.sort(Comparator.comparing(Contact::getName));
            }
        }
    }
}