package solutions.prantae.hyperlocaladmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminSplash extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_splash);

        Button state = findViewById(R.id.gotoState);
        Button city = findViewById(R.id.gotoCity);
        Button service = findViewById(R.id.gotoService);

        state.setOnClickListener(this);
        city.setOnClickListener(this);
        service.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        if (view.getId()==R.id.gotoState)
        {
            intent = new Intent(getApplicationContext(), AddState.class);
        }
        else if (view.getId()==R.id.gotoCity)
        {
            intent = new Intent(getApplicationContext(), AddCity.class);
        }
        else if (view.getId()==R.id.gotoService)
        {
            intent = new Intent(getApplicationContext(), AddService.class);
        }
        else
        {
            return;
        }
        startActivity(intent);
    }
}
