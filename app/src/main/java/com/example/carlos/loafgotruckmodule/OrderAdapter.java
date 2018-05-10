package com.example.carlos.loafgotruckmodule;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Carlos on 4/30/2018.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    /**this is an Adpater class meant to bind our data sets in various views, we also added click
     * listeners to one of our overwritten methods to add functionality.
     */
    LayoutInflater layoutInflater;
    ArrayList<Orders> mOrders;
    Context context;
    SparseBooleanArray sparseBooleanArray;

    OrderAdapter(Context context, ArrayList<Orders> orders) {
        mOrders = orders;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        sparseBooleanArray = new SparseBooleanArray();
    }


    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View v = layoutInflater.inflate(R.layout.item_view, parent, false);
        return new OrderAdapter.ViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(final OrderAdapter.ViewHolder holder, final int position) {
        final Orders orders = mOrders.get(position);
        holder.bindTo(orders);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("***", "onClick: clicked ");
                String s = orders.getAddress();
                Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                Uri builder = Uri.parse("google.navigation:q=" + s + "&mode=d");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, builder);
                mapIntent.setPackage("com.google.android.apps.maps");
                context = holder.itemView.getContext();
                context.startActivity(mapIntent);


            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                v.setBackgroundColor(Color.CYAN);
                return true;
            }
        });


    }

    @Override
    public int getItemCount() {
        return mOrders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTv, addressTV, OrdersTV, qtyTV;
        private Orders orders;
        private Context context;
        private OrderAdapter orderAdapter;
        private RecyclerView recyclerView;

        public ViewHolder(View itemView, OrderAdapter orderAdapter) {
            super(itemView);

            this.orderAdapter = orderAdapter;

            nameTv = itemView.findViewById(R.id.name_TV);
            addressTV = itemView.findViewById(R.id.AddressTV);
            OrdersTV = itemView.findViewById(R.id.ordersTV);
            qtyTV = itemView.findViewById(R.id.qtyTV);
        }

        public void bindTo(Orders orders) {
            this.orders = orders;

            nameTv.setText(orders.getName());
            addressTV.setText(orders.getAddress());
            OrdersTV.setText(orders.getOrder());
            qtyTV.setText("69");


        }


    }
}
