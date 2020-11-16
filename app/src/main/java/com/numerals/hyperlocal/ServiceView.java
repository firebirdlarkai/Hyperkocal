package com.numerals.hyperlocal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ServiceView extends AppCompatActivity {

    ArrayList<Service> serviceArrayList = new ArrayList<>();
    ArrayList<Service> allServices = new ArrayList<>();
    ServiceAdapter adapter;
    RecyclerView recyclerView;
    EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_view);
        populateData();

        recyclerView = findViewById(R.id.serviceRecycler);
        search = findViewById(R.id.search);
        adapter = new ServiceAdapter(getApplicationContext(), serviceArrayList);
        recyclerView.setAdapter(adapter);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

    }

    public void populateData()
    {
        HashMap services = Constants.serviceHashMap;

        if (services==null)
        {
            return;
        }

        Set keySet = services.keySet();

        ArrayList<String> listOfKeys = new ArrayList<String>(keySet);



        try {
            services.size();
        }
        catch (NullPointerException e)
        {
            return;
        }

        int keyCount = listOfKeys.size();

        for (int i = 0; i < keyCount; i++)
        {
            String key = listOfKeys.get(i);
            HashMap service = (HashMap) services.get(key);
            String appstore = "";
            String playstore = "";
            String web = "";
            try {
                appstore = (String) service.get("appstore");
            }
            catch (Exception e)
            {

            }
            try {
                playstore = (String) service.get("playstore");
            }
            catch (Exception e)
            {

            }

            try {
                web = (String) service.get("web");
            }
            catch (Exception e)
            {

            }

            serviceArrayList.add(new Service((String)
                    service.get("name"),
                    (String) service.get("description"),
                    playstore,
                    appstore,
                    web,
                    (String) service.get("type"),
                    (String) service.get("logo"),
                    "false"
                    ));
            allServices.add(new Service((String)
                    service.get("name"),
                    (String) service.get("description"),
                    playstore,
                    appstore,
                    web,
                    (String) service.get("type"),
                    (String) service.get("logo"),
                    "false"
            ));
        }
        //Collections.shuffle(serviceArrayList);

        fetchAutoServices();
    }


    void filter(String text){
        serviceArrayList.clear();
        for(Service d: allServices){
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if(d.getName().toLowerCase().contains(text.toLowerCase()) ||
                    d.getType().toLowerCase().contains(text.toLowerCase())
            ){
                serviceArrayList.add(d);
            }
            Log.d("Neel", "filter: "+d.getName());
        }
        if (text.equals(""))
        {
            //serviceArrayList = (ArrayList<Service>)allServices.clone();
        }
        adapter.notifyDataSetChanged();
        Log.d("TAG", "filter: "+serviceArrayList);
        Log.d("TAG", "filter: "+serviceArrayList);
    }
    RequestQueue mRequestQueue;
    public void fetchAutoServices()
    {
        mRequestQueue = Volley.newRequestQueue(this);
        String restaurantsAPI = "http://firebirdlarkai.pythonanywhere.com/search/";
        JSONObject object = new JSONObject();
        try {
            object.put("token","f1mB4Z2IZzee1eiIczZN");
            object.put("key",Constants.citySelected);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, restaurantsAPI, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("TAG", "onResponse: JSON: " +response);
                try {
                    JSONObject json = response;
                    JSONArray jsonArray = json.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length();i++)
                    {
                        JSONObject object1 = jsonArray.getJSONObject(i);
                        Log.d("TAG", "onResponse: "+object1);
                        serviceArrayList.add(new Service((String)
                                object1.get("title"),
                                (String) object1.get("description"),
                                "https://play.google.com"+object1.get("url"),
                                "",
                                "",
                                "",
                                (String) object1.get("icon"),
                                "true"
                        ));
                        allServices.add(new Service((String)
                                object1.get("title"),
                                (String) object1.get("description"),
                                "https://play.google.com"+object1.get("url"),
                                "",
                                "",
                                "",
                                (String) object1.get("icon"),
                                "true"
                        ));
                        if (i==5)
                        {
                            break;
                        }
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("TAG", "onResponse: "+e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i("TAG","Error :" + error.toString());
            }
        }
        );
        mRequestQueue.add(jsonObjectRequest);
    }
}