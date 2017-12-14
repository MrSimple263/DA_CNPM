package com.example.tqmin_000.da_cnpm.Controls;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TabHost;

import com.example.tqmin_000.da_cnpm.R;

/**
 * Created by tqmin_000 on 12/13/2017.
 */

public class Tab extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab);
        TabHost tab=(TabHost) findViewById(R.id.tabhost);
        tab.setup();
        TabHost.TabSpec spec;
        //
        spec=tab.newTabSpec("t1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("tab1");
        tab.addTab(spec);
        ///
        spec=tab.newTabSpec("t2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("tab2");
        tab.addTab(spec);
        //
        tab.setCurrentTab(0);
    }
}
