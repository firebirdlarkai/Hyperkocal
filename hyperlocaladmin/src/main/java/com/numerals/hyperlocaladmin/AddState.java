package com.numerals.hyperlocaladmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddState extends AppCompatActivity {

    int count;
    EditText stateName, thumbnailUrl;
    Button addButtton;
    FirebaseDatabase database;

    public static ArrayList list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_state);

        stateName = findViewById(R.id.stateName);
        thumbnailUrl = findViewById(R.id.stateImgUrl);
        addButtton = findViewById(R.id.stateAddButton);
        addButtton.setEnabled(false);

        addButtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stateName.getText().toString().equals("") || thumbnailUrl.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                    Map<String, Object> childMap = new HashMap<>();
                    childMap.put("name", stateName.getText().toString());
                    childMap.put("thumbnail", thumbnailUrl.getText().toString());
                    DatabaseReference reference = database.getReference("States");
                    reference.push().setValue(childMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(getApplicationContext(), "Stated Added",
                                    Toast.LENGTH_LONG).show();
                            stateName.setText("");
                            thumbnailUrl.setText("");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }).addOnCanceledListener(new OnCanceledListener() {
                        @Override
                        public void onCanceled() {
                            Toast.makeText(getApplicationContext(), "Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    })
                    ;

            }
        });

        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("States");

        //myRef.setValue("Hello, World!");

        Log.d("Neel", "onCreate: Fetching data");
        // Read from the database
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                HashMap value = (HashMap) dataSnapshot.getValue();

                //list = (ArrayList) dataSnapshot.getValue();
                //count = list.size();
                Log.d("Neel", "onDataChange: "+value);
                addButtton.setEnabled(true);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}