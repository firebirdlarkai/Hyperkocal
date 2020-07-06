package in.firebird.hyperkocal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.MyViewHolder> {


    private Context mContext;
    private List<City> cityList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView thumbnail;
        public RelativeLayout relativeLayout;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.cityName);
            thumbnail = (ImageView) view.findViewById(R.id.cityImageView);
            relativeLayout = view.findViewById(R.id.cityRelativeView);
        }
    }
    public CitiesAdapter(Context mContext, List<City> cityList) {
        this.mContext = mContext;
        this.cityList = cityList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.citycardview, parent, false);

        return new CitiesAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        City city = cityList.get(position);
        holder.name.setText(city.getName());
        // loading album cover using Glide library
        Glide.with(mContext).load(city.getThumbnail()).into(holder.thumbnail);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.serviceHashMap = (HashMap) cityList.get(position).getService();
                Intent intent = new Intent(mContext, ServiceView.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }
}