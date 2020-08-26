package in.firebird.hyperlocal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Service> dataModel;

    public ServiceAdapter(Context context, ArrayList<Service> dataModel) {
        this.context = context;
        this.dataModel = dataModel;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView logoView;
        private TextView title;
        private TextView description;
        private TextView serviceType;
        private ImageView playstore;
        private ImageView appstore;
        private ImageView web;
        private RelativeLayout card;
        private TextView source;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            logoView = itemView.findViewById(R.id.serviceLogo);
            title = itemView.findViewById(R.id.serviceName);
            description = itemView.findViewById(R.id.serviceDescription);
            serviceType = itemView.findViewById(R.id.serviceType);
            playstore = itemView.findViewById(R.id.buttonPlaystore);
            appstore = itemView.findViewById(R.id.buttonAppstore);
            web = itemView.findViewById(R.id.weblink);
            card = itemView.findViewById(R.id.serviceCard);
            source = itemView.findViewById(R.id.serviceSource);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_service, parent, false);
        return new ServiceAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Log.d("TAG", "onBindViewHolder: ONBIND");
        holder.title.setText(dataModel.get(position).getName());
        holder.description.setText(dataModel.get(position).getDescription());
        holder.serviceType.setText(dataModel.get(position).getType());
        Glide.with(context)
                .load(dataModel.get(position)
                .getLogo())
                .into(holder.logoView);



        final String playstoreLink = dataModel.get(position).getPlaystoreLink();
        final String appstoreLink = dataModel.get(position).getAppstoreLink();
        final String webLink = dataModel.get(position).getWebLink();

        Log.d("TAG", "onBindViewHolder: "+appstoreLink);
        if (webLink.equals(""))
        {
            holder.web.setVisibility(View.GONE);
        }

        if (appstoreLink.equals(""))
        {
            holder.appstore.setVisibility(View.GONE);
        }

        if (playstoreLink.equals(""))
        {
            holder.playstore.setVisibility(View.GONE);
        }
        holder.appstore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logAnalytics(dataModel.get(position));
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(appstoreLink));
                context.startActivity(i);
            }
        });

        holder.playstore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logAnalytics(dataModel.get(position));
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(playstoreLink));
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });

        holder.web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logAnalytics(dataModel.get(position));
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(webLink));
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
        if (dataModel.get(position).getAutoFetch().equals("true"))
        {
            holder.source.setText("Playstore Suggestions");
            //holder.logoView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        else if (dataModel.get(position).getAutoFetch().equals("false"))
        {
            holder.source.setText("Curated");
            holder.source.setTextColor(Color.parseColor("#c70039"));
            //holder.logoView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!playstoreLink.equals(""))
                {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(playstoreLink));
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
                else if (!webLink.equals(""))
                {
                    logAnalytics(dataModel.get(position));
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(webLink));
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
            }
        });
    }

    private FirebaseAnalytics mFirebaseAnalytics;
    public void logAnalytics(Service service)
    {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, service.getName());
        bundle.putString(FirebaseAnalytics.Param.LOCATION, Constants.citySelected);
        //Constants.serviceHashMap = (HashMap) cityList.get(position).getService();
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }
    @Override
    public int getItemCount() {
        return dataModel.size();
    }


}
