package com.example.karwashandautodetailing;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

public class ServiceActivity extends AppCompatActivity {


    RecyclerView mRecyclerView;
    List<ServiceData> mServiceList;
    ServiceData mServiceData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);




        mRecyclerView = findViewById(R.id.recyclerview);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(ServiceActivity.this, 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        mServiceList = new ArrayList<>();
        mServiceData = new ServiceData(R.drawable.normalwash);
        mServiceList.add(mServiceData);
        mServiceData = new ServiceData(R.drawable.enginewash);
        mServiceList.add(mServiceData);
        mServiceData = new ServiceData(R.drawable.underbodywash);
        mServiceList.add(mServiceData);
        mServiceData = new ServiceData(R.drawable.headlightscleaning);
        mServiceList.add(mServiceData);
        mServiceData = new ServiceData(R.drawable.buffing);
        mServiceList.add(mServiceData);
        mServiceData = new ServiceData(R.drawable.detailing);
        mServiceList.add(mServiceData);
        mServiceData = new ServiceData(R.drawable.watermarksremoval);
        mServiceList.add(mServiceData);
        mServiceData = new ServiceData(R.drawable.minorscratchesremoval);
        mServiceList.add(mServiceData);
        mServiceData = new ServiceData(R.drawable.minorscratchesremoval);

        MyAdapter myAdapter = new MyAdapter(ServiceActivity.this, mServiceList);
        mRecyclerView.setAdapter(myAdapter);
    }


}