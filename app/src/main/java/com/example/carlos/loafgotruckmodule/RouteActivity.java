package com.example.carlos.loafgotruckmodule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;

public class RouteActivity extends AppCompatActivity  {
    ArrayList<Orders> ordersArrayList = new ArrayList<>();
    OrderAdapter orderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setActionBar(toolbar);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        orderAdapter = new OrderAdapter(getBaseContext(),ordersArrayList);
        recyclerView.setAdapter(orderAdapter);

        setOrders();
    }
    public void setOrders() {
      //  Log.d(TAG, "setOrders: we made it");
        //order = orders1;

        TextView nameTV = findViewById(R.id.name_TV),
                AddressTV = findViewById(R.id.AddressTV),
                ordersTV = findViewById(R.id.ordersTV),
                qtyTV = findViewById(R.id.qtyTV);

        String[] Name = getResources().getStringArray(R.array.names);
        String[] Address = getResources().getStringArray(R.array.address);
        String[] Orders = getResources().getStringArray(R.array.Orders);

        ordersArrayList.clear();

        for (int i = 0; i < Name.length; i++) {
            ordersArrayList.add(new Orders(Name[i], Address[i], Orders[i], 69));
        }


        orderAdapter.notifyDataSetChanged();

    }

}

