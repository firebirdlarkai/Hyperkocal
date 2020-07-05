package solutions.prantae.hyperlocaladmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class AddCity extends AppCompatActivity {

    FirebaseDatabase database;
    SearchableSpinner searchableSpinner;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);

        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("States");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HashMap value = (HashMap) dataSnapshot.getValue();

                //Getting Set of keys from HashMap
                Set<String> keySet = value.keySet();
                //Creating an ArrayList of keys by passing the keySet
                ArrayList<String> listOfKeys = new ArrayList<String>(keySet);
                Log.d("TAG", "onDataChange: "+listOfKeys);
                Log.d("TAG", "onDataChange: "+listOfKeys.getClass());
                //Log.d("TAG", "onDataChange: "+listOfKeys.size());
                int size = listOfKeys.size();
                Log.d("TAG", "onDataChange: "+size);
                for (int i = 0; i < size; i++)
                {
                    String key = listOfKeys.get(i);
                    if (value.get(key)!=null)
                    {
                        HashMap state = (HashMap) value.get(key);
                        String name = (String) state.get("name");
                        arrayList.add(name);
                    }
                    Log.d("TAG", "onDataChange: "+i);
                }
                arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_spinner_item, arrayList);
                searchableSpinner.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        searchableSpinner = findViewById(R.id.searchSpinner);


        searchableSpinner.setTitle("Select Item");
        searchableSpinner.setPositiveButton("OK");

    }
}