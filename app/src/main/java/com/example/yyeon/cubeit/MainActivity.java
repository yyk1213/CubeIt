package com.example.yyeon.cubeit;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.yyeon.cubeit.fragment.ChartFragment;
import com.example.yyeon.cubeit.fragment.SearchFragment;
import com.example.yyeon.cubeit.fragment.SettingFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import info.kimjihyok.library.fragment.DreamListFragment;
import info.kimjihyok.library.fragment.ZoomableGridFragment;
import info.kimjihyok.library.widget.ZoomableLayout;

public class MainActivity extends AppCompatActivity implements ZoomableLayout.MaxZoomListener {
    private static final String TAG = "MainActivity";

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
        mandaratFragment.setDream(getUserDream(0));
        mandaratFragment.setTargetItems(getUserTargetItems());

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
    public void onZoomedMax(int position) {
        Log.d(TAG, "onZoomedMax() " + position);
        mandaratFragment.resetView();

        // replace with a new fragment
        getSupportFragmentManager().beginTransaction()
            .add(R.id.contentContainer,
                DreamListFragment.newInstance(
                    DreamListFragment.Mode.VIEW_MODE
                    , getUserDream(position)
                    , getFollowingDreamElements(position)))
            .addToBackStack(DreamListFragment.TAG)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit();
    }

    public ArrayList<String> getFollowingDreamElements(int position) {
        ArrayList<String> dreamList = new ArrayList<>();
        dreamList.add("Lose Belly Fat " + position);
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

    private String getUserDream(int position) {
        return "CEO before 29" + " pos: " + position;
    }

    private List<String> getUserTargetItems() {
        ArrayList<String> userTargetItems = new ArrayList<>();
        userTargetItems.add("Luck");
        userTargetItems.add("Leadership");
        userTargetItems.add("Software Development");
        userTargetItems.add("Personality");
        userTargetItems.add("Self-Control");
        userTargetItems.add("Executive Abilities");
        userTargetItems.add("Trend Analyzing Abilities");
        userTargetItems.add("Attractiveness");
        return userTargetItems;
    }
}

