package in.firebird.hyperkocal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static in.firebird.hyperkocal.Splash.list;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private StatesAdapter adapter;
    private List<StatesHandler> statesHandlerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);

        statesHandlerList = new ArrayList<>();
        adapter = new StatesAdapter(this, statesHandlerList);

        //RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        //recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        prepareStates();
    }

    private void prepareStates() {

        StatesHandler a;

        int numberOfStates = list.keySet().size();
        Set<String> keySet = list.keySet();

        for(String key : keySet)
        {
            Log.d("Neel", "prepareStates: "+key);
            HashMap stateDetails = (HashMap) list.get(key);
            a = new StatesHandler((String) stateDetails.get("name"), (String) stateDetails.get("thumbnail"));
            statesHandlerList.add(a);
        }

        for (int i = 0; i<numberOfStates; i++)
        {
          //  HashMap stateDetails = (HashMap) list.get();
          //  a = new StatesHandler((String) stateDetails.get("name"), (String) stateDetails.get("thumbnail"));
          //  statesHandlerList.add(a);
        }

        adapter.notifyDataSetChanged();
    }
}
