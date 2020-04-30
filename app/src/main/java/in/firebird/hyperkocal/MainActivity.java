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

import static in.firebird.hyperkocal.Splash.list;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private StatesAdapter adapter;
    private List<StatesHandler> statesHandlerList;

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //TODO: Step 4 of 4: Finally call getTag() on the view.
            // This viewHolder will have all required values.
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            // viewHolder.getItemId();
            // viewHolder.getItemViewType();
            // viewHolder.itemView;

            Toast.makeText(MainActivity.this, "You Clicked: " +viewHolder.getLayoutPosition() , Toast.LENGTH_SHORT).show();

            Intent myIntent = new Intent(getApplicationContext(), CitiesView.class);
            myIntent.putExtra("intVariableName", viewHolder.getLayoutPosition());
            startActivity(myIntent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);

        statesHandlerList = new ArrayList<>();
        adapter = new StatesAdapter(this, statesHandlerList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.setItemClickListener(onItemClickListener);
        prepareStates();
    }

    private void prepareStates() {

        StatesHandler a;

        int numberOfStates = list.size();
        for (int i = 0; i<numberOfStates; i++)
        {
            HashMap stateDetails = (HashMap) list.get(i);
            a = new StatesHandler((String) stateDetails.get("name"), (String) stateDetails.get("thumbnail"));
            statesHandlerList.add(a);
        }

        adapter.notifyDataSetChanged();
    }
}
