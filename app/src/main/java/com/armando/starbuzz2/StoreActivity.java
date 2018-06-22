package com.armando.starbuzz2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class StoreActivity extends Activity {

    public static final String EXTRA_STOREID = "storeId";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        int storeId = (Integer) getIntent().getExtras().get(EXTRA_STOREID);
        Store store = Store.stores[storeId];

        TextView textName = (TextView) findViewById(R.id.storeName);
        textName.setText(store.getName());

        TextView textDescriptin = (TextView) findViewById(R.id.storeDescription);
        textDescriptin.setText(store.getDescription());

        ImageView photo = (ImageView) findViewById(R.id.storePhoto);
        photo.setImageResource(store.getImageResourceId());
        photo.setContentDescription(store.getName());
    }
}
