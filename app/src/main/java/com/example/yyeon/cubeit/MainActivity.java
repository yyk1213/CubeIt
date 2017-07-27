package com.example.yyeon.cubeit;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends FragmentActivity {
    private Mandarat mandarat;
    private Chart chart;
    private Search search;
    private Setting setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mandarat = new Mandarat();
        chart = new Chart();
        search = new Search();
        setting = new Setting();

        initFragment();

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Log.d("MainActivity","id: "+tabId);
                if (tabId == R.id.mandarat_btn) {
                    transaction.replace(R.id.contentContainer, mandarat).commit();
                } else if (tabId == R.id.chart_btn) {
                    transaction.replace(R.id.contentContainer, chart).commit();
                } else if (tabId == R.id.search_btn) {
                    transaction.replace(R.id.contentContainer, search).commit();
                } else if (tabId == R.id.setting_btn) {
                    transaction.replace(R.id.contentContainer, setting).commit();
                }
            }
        });
    }

    public void initFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.contentContainer, mandarat);
        transaction.commit();
    }
}

