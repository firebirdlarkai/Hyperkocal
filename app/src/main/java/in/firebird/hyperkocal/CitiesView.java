package in.firebird.hyperkocal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static in.firebird.hyperkocal.Splash.list;

public class CitiesView extends AppCompatActivity {

    RecyclerView recyclerView;
    private CitiesAdapter adapter;
    private List<City> cityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities_view);

        Log.d("Neel", "onCreate: Inside City view");
        recyclerView = (RecyclerView) findViewById(R.id.myRecyclerViewCities);

        cityList = new ArrayList<>();
        adapter = new CitiesAdapter(this, cityList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareCities();
    }

    private void prepareCities() {

        City a;

        Intent mIntent = getIntent();
        int intValue = mIntent.getIntExtra("intVariableName", 0);

        HashMap cityMap = (HashMap) list.get(intValue);

        ArrayList cityArray = (ArrayList) cityMap.get("cities");

        int numberOfCities = cityArray.size();
        for (int i = 0; i<numberOfCities; i++)
        {
            HashMap cityDetails = (HashMap) cityArray.get(i);
            a = new City((String) cityDetails.get("name"), (String) cityDetails.get("thumbnail")
                    , (ArrayList) cityDetails.get("services"));
            cityList.add(a);
        }
        adapter.notifyDataSetChanged();
    }
}
