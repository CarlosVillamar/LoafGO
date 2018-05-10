package com.example.carlos.loafgotruckmodule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class optionsActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * class used to handle the button views and give them functionality
     */

    Button addOrder, viewOrder, route;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        addOrder = findViewById(R.id.scanOrderButton);
        viewOrder = findViewById(R.id.viewOrderButton);
        route = findViewById(R.id.RouteButton);
    }

    @Override
    public void onClick(View v) {
        int c = v.getId();
        switch (c) {
            case R.id.scanOrderButton:
                //Intent scan = new Intent(optionsActivity.this, BarcodeActivity.class);
                Intent scan = new Intent(optionsActivity.this, ReaderActivity.class);
                startActivity(scan);
                break;
            case R.id.viewOrderButton:
                Intent view = new Intent(optionsActivity.this, ViewListActivity.class);
                startActivity(view);
                break;
            case R.id.RouteButton:
                //intent
                Intent routeView = new Intent(getApplicationContext(), RouteActivity.class);
                startActivity(routeView);
                /**Uri gmmIntentUri = Uri.parse("google.navigation:q=Judy+and+Punch,+Queens+NewYork&mode=d");
                 Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                 mapIntent.setPackage("com.google.android.apps.maps");
                 startActivity(mapIntent);**/

                /**Uri gmmIntentUri = Uri.parse("google.navigation:q=2265DavidsonAvenue,Bronx,NewYork,10453&mode=d");
                 Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                 mapIntent.setPackage("com.google.android.apps.maps");
                 startActivity(mapIntent);
                 **/
                break;
        }
    }
}
