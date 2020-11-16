package com.numerals.hyperlocal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static com.numerals.hyperlocal.Splash.list;

public class CitiesView extends AppCompatActivity {

    RecyclerView recyclerView;
    private CitiesAdapter adapter;
    private List<City> cityList;
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities_view);

        //RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList("EB203454216E802E235DDC7A728D19AF");
                mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-5706938673064084/8347400988");

        // Create an ad request.
        AdRequest.Builder adRequestBuilder = new AdRequest.Builder();

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("EB203454216E802E235DDC7A728D19AF")
                .build();
        // Set an AdListener.
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Toast.makeText(CitiesView.this,
                        "The interstitial is loaded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClosed() {
                // Proceed to the next level.
                //goToNextLevel();
            }
        });

        // Start loading the ad now so that it is ready by the time the user is ready to go to
        // the next level.
        mInterstitialAd.loadAd(adRequestBuilder.build());

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
