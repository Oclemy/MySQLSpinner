package com.tutorials.hp.mysqlspinner;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;

import com.tutorials.hp.mysqlspinner.mMySQL.Downloader;


public class MainActivity extends AppCompatActivity {


    final static String urlAddress="http://10.0.2.2/android/spacecraft_select.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        final Spinner sp= (Spinner) findViewById(R.id.sp);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Downloader(MainActivity.this,urlAddress,sp).execute();

            }
        });
    }

}
