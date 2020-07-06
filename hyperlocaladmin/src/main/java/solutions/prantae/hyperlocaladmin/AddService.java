package solutions.prantae.hyperlocaladmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AddService extends AppCompatActivity {

    String citySelected;
    String stateSelected;
    FirebaseDatabase database;
    SearchableSpinner searchableSpinnerState;
    SearchableSpinner searchableSpinnerCity;
    EditText serviceName, serviceDescription, serviceLogo, servicePlaystore, serviceAppstore,
            serviceWeb;
    Button addServiceButton;
    HashMap statesValue;

    ArrayList keysState;
    ArrayList<String> arrayListState = new ArrayList<>();
    ArrayList<String> arrayListCity = new ArrayList<>();
    ArrayAdapter<String> arrayAdapterState;
    ArrayAdapter<String> arrayAdapterCity;

    ArrayList<String> listOfKeysCities;
    ArrayList<String> listOfKeys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);
        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("States");

        searchableSpinnerState = findViewById(R.id.searchState);
        searchableSpinnerCity = findViewById(R.id.searchCity);
        addServiceButton = findViewById(R.id.addService);
        serviceName = findViewById(R.id.nameService);
        serviceDescription = findViewById(R.id.descriptionService);
        serviceLogo = findViewById(R.id.logoURLService);
        serviceAppstore = findViewById(R.id.appstoreURL);
        servicePlaystore = findViewById(R.id.playstoreURL);
        serviceWeb = findViewById(R.id.webURL);

        addServiceButton.setEnabled(false);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HashMap value = (HashMap) dataSnapshot.getValue();

                statesValue = value;
                //Getting Set of keys from HashMap
                Set<String> keySet = value.keySet();
                //Creating an ArrayList of keys by passing the keySet
                listOfKeys = new ArrayList<String>(keySet);
                Log.d("TAG", "onDataChange: "+listOfKeys);
                Log.d("TAG", "onDataChange: "+listOfKeys.getClass());
                //Log.d("TAG", "onDataChange: "+listOfKeys.size());
                int size = listOfKeys.size();
                keysState = listOfKeys;
                Log.d("TAG", "onDataChange: "+size);
                for (int i = 0; i < size; i++)
                {
                    String key = listOfKeys.get(i);
                    if (value.get(key)!=null)
                    {
                        HashMap state = (HashMap) value.get(key);
                        String name = (String) state.get("name");
                        arrayListState.add(name);
                    }
                    Log.d("TAG", "onDataChange: "+i);
                }
                arrayAdapterState = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_spinner_item, arrayListState);
                searchableSpinnerState.setAdapter(arrayAdapterState);

                HashMap state = (HashMap) value.get(listOfKeys.get(0));

                HashMap cities = (HashMap) state.get("cities");
                Log.d("TAG", "onDataChange: "+cities);

                Set<String> keySetCities = cities.keySet();
                //Creating an ArrayList of keys by passing the keySet
                listOfKeysCities = new ArrayList<String>(keySetCities);
                Log.d("TAG", "onDataChange: "+listOfKeysCities);
                int sizeOfCities = listOfKeysCities.size();
                for (int i =0; i < sizeOfCities; i++ )
                {
                    String cityKey = listOfKeysCities.get(i);
                    HashMap city = (HashMap) cities.get(cityKey);
                    String cityName = (String) city.get("name");
                    arrayListCity.add(cityName);
                }

                arrayAdapterCity = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_spinner_item, arrayListCity);
                searchableSpinnerCity.setAdapter(arrayAdapterCity);

                stateSelected = listOfKeys.get(0);
                citySelected = listOfKeysCities.get(0);
                addServiceButton.setEnabled(true);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        searchableSpinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                stateSelected = listOfKeys.get(i);
                Toast.makeText(getApplicationContext(), stateSelected, Toast.LENGTH_SHORT ).show();

                HashMap state = (HashMap) statesValue.get(listOfKeys.get(i));

                HashMap cities = (HashMap) state.get("cities");
                Log.d("TAG", "onDataChange: "+cities);

                if (cities==null)
                {
                    arrayListCity.clear();
                    arrayAdapterCity.notifyDataSetChanged();
                    citySelected = "";
                    return;
                }

                Set<String> keySetCities = cities.keySet();
                //Creating an ArrayList of keys by passing the keySet
                listOfKeysCities = new ArrayList<String>(keySetCities);
                Log.d("TAG", "onDataChange: "+listOfKeysCities);
                int sizeOfCities = listOfKeysCities.size();
                for (int x =0; x < sizeOfCities; x++ )
                {
                    String cityKey = listOfKeysCities.get(x);
                    HashMap city = (HashMap) cities.get(cityKey);
                    String cityName = (String) city.get("name");
                    arrayListCity.add(cityName);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        searchableSpinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                citySelected = listOfKeysCities.get(i);
                Toast.makeText(getApplicationContext(), citySelected, Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        addServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference myCityRef = database.getReference("States")
                        .child(stateSelected)
                        .child("cities").child(citySelected).child("services");

                Map<String, Object> childMap = new HashMap<>();
                childMap.put("name", serviceName.getText().toString());
                childMap.put("logo", serviceLogo.getText().toString());
                childMap.put("description", serviceDescription.getText().toString());
                childMap.put("playstore", servicePlaystore.getText().toString());
                childMap.put("appstore", serviceAppstore.getText().toString());
                childMap.put("web", serviceWeb.getText().toString());

                myCityRef.push().setValue(childMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(),
                                "City Service to "+searchableSpinnerCity.getSelectedItem(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


}