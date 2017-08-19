package com.example.yyeon.cubeit;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {
    private Mandarat mandarat;
    private Chart chart;
    private Search search;
    private Follow follow;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/NotoSansCJKkr-Regular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        setContentView(R.layout.activity_main);

        mandarat = new Mandarat();
        chart = new Chart();
        search = new Search();
        follow=new Follow();
        actionBar=getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);

        initFragment();

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Log.d("MainActivity","id: "+tabId);
                if (tabId == R.id.mandarat_btn) {

                    actionBar.setIcon(R.drawable.ic_actionbar_cube);
                    actionBar.setTitle(" 나의 만다라트");
                    transaction.replace(R.id.contentContainer, mandarat).commit();
                } else if (tabId == R.id.chart_btn) {
                    actionBar.setIcon(R.drawable.ic_actionbar_chart);
                    actionBar.setTitle(" 분석 차트");
                    transaction.replace(R.id.contentContainer, chart).commit();
                } else if (tabId == R.id.search_btn) {
                    actionBar.setIcon(R.drawable.ic_actionbar_pencil);
                    actionBar.setTitle(" 계획표 작성");
                    transaction.replace(R.id.contentContainer, search).commit();
                } else if (tabId == R.id.follow_btn) {
                    actionBar.setIcon(R.drawable.ic_actionbar_heart);
                    actionBar.setTitle(" 팔로우");
                    transaction.replace(R.id.contentContainer, follow).commit();
                }
            }
        });
    }

    public void initFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.contentContainer, mandarat);
        transaction.commit();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}

