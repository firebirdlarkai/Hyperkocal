package in.firebird.hyperkocal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

public class ServiceView extends AppCompatActivity {

    ArrayList<Service> serviceArrayList = new ArrayList<>();
    ServiceAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_view);
        populateData();

        recyclerView = findViewById(R.id.serviceRecycler);
        adapter = new ServiceAdapter(getApplicationContext(), serviceArrayList);
        recyclerView.setAdapter(adapter);
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
                    (String) service.get("logo")));
        }
        Collections.shuffle(serviceArrayList);
    }
}