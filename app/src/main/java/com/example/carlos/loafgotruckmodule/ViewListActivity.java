package com.example.carlos.loafgotruckmodule;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewListActivity extends AppCompatActivity {
    private OrderAdapter orderAdapter;
    ArrayList<Orders> ordersList;
    RecyclerView recyclerView;
    String s = " ";
    public static final String TAG = "ViewListActivity";

    /**Originally we wanted to implemnet a database,
     * however  we decieded to use dummy hardcoded
     * data due to time consttaints
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /**for (Orders orders : ordersArrayList) {
         Log.d(TAG, "onCreate: assgining orders");
         setOrders(orders);
         db.getOrders();
         }**/

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        //populateRecyclerView(s);
        ordersList = new ArrayList<>();
        orderAdapter = new OrderAdapter(getApplicationContext(), ordersList);
        recyclerView.setAdapter(orderAdapter);

        setOrders();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

                Intent scan = new Intent(ViewListActivity.this, ReaderActivity.class);
                startActivity(scan);
            }
        });
    }

    private void populateRecyclerView(String s) {
        //dbHelper = new OrdersDB.DBHelper(this);
        //dbHelper.getReadableDatabase();
        //orderAdapter = new OrderAdapter(this,recyclerView,db.ordersList(s));
        recyclerView.setAdapter(orderAdapter);
    }

    public void setOrders() {
        Log.d(TAG, "setOrders: we made it");
        //order = orders1;

        TextView nameTV = findViewById(R.id.name_TV),
                AddressTV = findViewById(R.id.AddressTV),
                ordersTV = findViewById(R.id.ordersTV),
                qtyTV = findViewById(R.id.qtyTV);

        String[] Name = getResources().getStringArray(R.array.names);
        String[] Address = getResources().getStringArray(R.array.address);
        String[] Orders = getResources().getStringArray(R.array.Orders);

        ordersList.clear();

        for (int i = 0; i < Name.length; i++) {
            ordersList.add(new Orders(Name[i], Address[i], Orders[i], 69));
        }


        orderAdapter.notifyDataSetChanged();

    }


}
