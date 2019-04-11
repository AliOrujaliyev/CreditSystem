package com.aset.probook.asetcalculator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Locale;


public class FirstActivity extends AppCompatActivity {
    Context context;
    Locale myLocale;
    SharedPreferences sharedPreferences;
    RelativeLayout rltAboutUs;
    RelativeLayout rltPartners;
    RelativeLayout rltMap;
    RelativeLayout rltCalculator;
    SharedPreferences.Editor myeditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.aset_bar4);
        actionBar.setTitle("");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        rltAboutUs = (RelativeLayout) findViewById(R.id.rltAboutUs);
        rltAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this, AboutUs.class);
                startActivity(intent);
            }
        });

        rltCalculator = (RelativeLayout) findViewById(R.id.rltCalculator);
        rltCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        rltMap = (RelativeLayout) findViewById(R.id.rltMap);
        rltMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
        rltPartners = (RelativeLayout) findViewById(R.id.rltPartners);
        rltPartners.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this, Partners.class);
                startActivity(intent);
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.languageAZ:
                setLocale("en");
                break;

            case R.id.languageRU:
                setLocale("ru");
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void setLocale(String lang) {
        // TODO Auto-generated method stub
        sharedPreferences = getApplicationContext().getSharedPreferences("com.aset.probook.asetcalculator", MODE_PRIVATE);
        myeditor = sharedPreferences.edit();
        myeditor.putString("com.aset.probook.asetcalculator", lang);
        myeditor.apply();
        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(FirstActivity.this, FirstActivity.class);
        startActivity(refresh);
        finish();

        /*SharedPreferences getPrefs = getApplicationContext().getSharedPreferences("com.aset.probook.asetcalculator", MODE_PRIVATE);
        String lan = getPrefs.getString("language", "");*/
    }
    //------------------------------------------------------------

}


