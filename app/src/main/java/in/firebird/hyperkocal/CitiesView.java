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
        String key = mIntent.getStringExtra("intVariableName");

        HashMap cityMap = (HashMap) list.get(key);
        Log.d("TAG", "prepareCities: "+cityMap);
        HashMap hashMap = (HashMap) cityMap.get("cities");
        Log.d("TAG", "prepareCities: "+hashMap);

        if (hashMap==null)
        {
            return;
        }

        //Getting Set of keys from HashMap
        Set<String> keySet = hashMap.keySet();
        //Creating an ArrayList of keys by passing the keySet
        ArrayList<String> listOfKeys = new ArrayList<String>(keySet);
        Log.d("TAG", "onDataChange: "+listOfKeys);
        Log.d("TAG", "onDataChange: "+listOfKeys.getClass());
        //Log.d("TAG", "onDataChange: "+listOfKeys.size());
        int numberOfCities = listOfKeys.size();

        for (int i = 0; i < numberOfCities; i++)
        {
            String keyCity = listOfKeys.get(i);
            if (hashMap.get(keyCity)!=null)
            {
                HashMap city = (HashMap) hashMap.get(keyCity);
                a = new City((String) city.get("name"), (String) city.get("thumbnail")
                        , (HashMap) city.get("services"));
                cityList.add(a);
            }
            Log.d("TAG", "onDataChange: "+i);
        }

        /***
        for (int i = 0; i<numberOfCities; i++)
        {
            HashMap cityDetails = (HashMap) cityArray.get(i);
            a = new City((String) cityDetails.get("name"), (String) cityDetails.get("thumbnail")
                    , (ArrayList) cityDetails.get("services"));
            cityList.add(a);
        }
         ***/
        adapter.notifyDataSetChanged();
    }
}
