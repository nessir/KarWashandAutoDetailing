package com.example.karwashandautodetailing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


public class MyAdapter extends RecyclerView.Adapter< ServiceViewHolder > {

    private Context mContext;
    private List< ServiceData > mServiceList;


    MyAdapter(Context mContext, List<ServiceData> mServiceList) {
        this.mContext = mContext;
        this.mServiceList = mServiceList;
    }

    @Override
    public ServiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_row, parent, false);
        return new ServiceViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final ServiceViewHolder holder, int position) {
        holder.mImage.setImageResource(mServiceList.get(position).getServiceImage());

    }

    @Override
    public int getItemCount() {
        return mServiceList.size();
    }
}

class ServiceViewHolder extends RecyclerView.ViewHolder {

    ImageView mImage;
    TextView mTitle;

    ServiceViewHolder(View itemView) {
        super(itemView);

        mImage = itemView.findViewById(R.id.ivImage);

    }
}

