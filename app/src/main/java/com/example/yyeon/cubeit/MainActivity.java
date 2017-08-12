package com.example.yyeon.cubeit;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.yyeon.cubeit.fragment.ChartFragment;
import com.example.yyeon.cubeit.fragment.SearchFragment;
import com.example.yyeon.cubeit.fragment.SettingFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;

import info.kimjihyok.library.fragment.DreamListFragment;
import info.kimjihyok.library.fragment.ZoomableGridFragment;
import info.kimjihyok.library.widget.ZoomableLayout;

public class MainActivity extends AppCompatActivity implements ZoomableLayout.MaxZoomListener {
    private ZoomableGridFragment mandaratFragment;
    private ChartFragment chartFragment;
    private SearchFragment searchFragment;
    private SettingFragment settingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mandaratFragment = ZoomableGridFragment.newInstance();
        mandaratFragment.setMaxZoomListener(this);

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

    @Override
    public void onZoomedMax() {
        mandaratFragment.resetView();

        // replace with a new fragment
        getSupportFragmentManager().beginTransaction()
            .add(R.id.contentContainer,
                DreamListFragment.newInstance(
                    DreamListFragment.Mode.VIEW_MODE
                    , "나의 목표"
                    , getFollowingDreamElements()))
            .addToBackStack(DreamListFragment.TAG)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit();
    }

    public ArrayList<String> getFollowingDreamElements() {
        ArrayList<String> dreamList = new ArrayList<>();
        dreamList.add("Lose Belly Fat");
        dreamList.add("Lose Leg Fat");
        dreamList.add("Good Eating Habit");

        dreamList.add("Less Drinking Days");
        dreamList.add("Lose Belly Fat");
        dreamList.add("Lose Leg Fat");

        dreamList.add("Good Eating Habit");
        dreamList.add("Lose Belly Fat");
        dreamList.add("Lose Leg Fat");
        return dreamList;
    }
}

