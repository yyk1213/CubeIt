package com.example.yyeon.cubeit;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.yyeon.cubeit.fragment.ChartFragment;
import com.example.yyeon.cubeit.fragment.MandaratFragment;
import com.example.yyeon.cubeit.fragment.SearchFragment;
import com.example.yyeon.cubeit.fragment.SettingFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {
    private MandaratFragment mandaratFragment;
    private ChartFragment chartFragment;
    private SearchFragment searchFragment;
    private SettingFragment settingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mandaratFragment = new MandaratFragment();
        chartFragment = new ChartFragment();
        searchFragment = new SearchFragment();
        settingFragment = new SettingFragment();

        initFragment();


        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                if (tabId == R.id.mandarat_btn) {
                    transaction.replace(R.id.contentContainer, mandaratFragment).commit();
                } else if (tabId == R.id.chart_btn) {
                    transaction.replace(R.id.contentContainer, chartFragment).commit();
                } else if (tabId == R.id.search_btn) {
                    transaction.replace(R.id.contentContainer, searchFragment).commit();
                } else if (tabId == R.id.setting_btn) {
                    transaction.replace(R.id.contentContainer, settingFragment).commit();
                }
            }
        });
    }

    public void initFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.contentContainer, mandaratFragment);
        transaction.commit();
    }
}

