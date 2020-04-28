package in.firebird.hyperkocal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class StatesAdapter extends RecyclerView.Adapter<StatesAdapter.MyViewHolder> {

    private Context mContext;
    private List<StatesHandler> statesHandlerList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.stateName);
            thumbnail = (ImageView) view.findViewById(R.id.stateImageView);
        }
    }
    public StatesAdapter(Context mContext, List<StatesHandler> statelist) {
        this.mContext = mContext;
        this.statesHandlerList = statelist;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.statecardview, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        StatesHandler album = statesHandlerList.get(position);
        holder.name.setText(album.getName());
        // loading album cover using Glide library
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return statesHandlerList.size();
    }

}
