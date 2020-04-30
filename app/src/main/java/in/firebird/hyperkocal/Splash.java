package in.firebird.hyperkocal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Splash extends AppCompatActivity {

    public static ArrayList list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_layout);
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("States");

        //myRef.setValue("Hello, World!");

        Log.d("Neel", "onCreate: Fetching data");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //HashMap value = (HashMap) dataSnapshot.getValue();
                list = (ArrayList) dataSnapshot.getValue();
                Log.d("Neel", "Value is: " + list.get(0));

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Neel", "Failed to read value.", error.toException());
            }
        });
    }
}
