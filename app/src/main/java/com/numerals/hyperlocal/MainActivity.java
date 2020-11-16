package com.numerals.hyperlocal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static com.numerals.hyperlocal.Splash.list;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private StatesAdapter adapter;
    private List<StatesHandler> statesHandlerList;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);

        statesHandlerList = new ArrayList<>();
        adapter = new StatesAdapter(this, statesHandlerList);
        mAdView = findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("EB203454216E802E235DDC7A728D19AF")
                .build();

        mAdView.loadAd(adRequest);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
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
            a = new StatesHandler(key,(String) stateDetails.get("name"),(String) stateDetails.get("thumbnail"));
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

    @Override
    public void onBackPressed() {

    }
}
