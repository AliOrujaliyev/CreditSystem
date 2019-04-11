package com.aset.probook.asetcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Probook on 20.04.2018.
 */

public class Partners extends AppCompatActivity implements View.OnClickListener {

    public PartnersAdapter mMenuAdapter;
    private ArrayList<PartnersMenuItem> menuList;
    private ListView mMenuListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.partners);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.aset_bar4);
        actionBar.setTitle("");

        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        mMenuListView = findViewById(R.id.lvPartners);
        menuList = new ArrayList<PartnersMenuItem>();

        menuList.add(new PartnersMenuItem(1,R.string.electronics));
        menuList.add(new PartnersMenuItem(2, R.string.furniture));
        menuList.add(new PartnersMenuItem(3, R.string.auto_services));
        menuList.add(new PartnersMenuItem(4, R.string.for_home));
        menuList.add(new PartnersMenuItem(5, R.string.tours_tickets));
        menuList.add(new PartnersMenuItem(6, R.string.clothing_others));

        mMenuAdapter = new PartnersAdapter(this, menuList, 0);

        mMenuListView.setAdapter(mMenuAdapter);
        mMenuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View v, int position, long id) {

                if ((menuList.get(position)).getID() == 1) {
                    Intent intent = new Intent(Partners.this,MapsActivity.class);
                    startActivity(intent);
                }
                mMenuAdapter.itemSelected(position);

            }
        });

    }

    @Override
    public void onClick(View view) {

    }
}
