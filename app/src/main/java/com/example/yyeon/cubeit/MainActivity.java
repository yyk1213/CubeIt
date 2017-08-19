package com.example.yyeon.cubeit;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.yyeon.cubeit.fragment.ChartFragment;
import com.example.yyeon.cubeit.fragment.SearchFragment;
import com.example.yyeon.cubeit.fragment.SettingFragment;
import com.example.yyeon.cubeit.model.ChartValue;
import com.example.yyeon.cubeit.model.RealmString;
import com.example.yyeon.cubeit.model.SubTarget;
import com.example.yyeon.cubeit.model.controller.DreamController;
import com.example.yyeon.cubeit.model.controller.RealmStringController;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import info.kimjihyok.library.fragment.DreamListFragment;
import info.kimjihyok.library.fragment.ZoomableGridFragment;
import info.kimjihyok.library.widget.ZoomableLayout;
import io.realm.RealmList;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements ZoomableLayout.MaxZoomListener {
  private static final String TAG = "MainActivity";
  private ZoomableGridFragment mandaratFragment;
  private ChartFragment chartFragment;
  private SearchFragment searchFragment;
  private SettingFragment settingFragment;
  private DreamController controller;
    private ActionBar actionBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
      CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
              .setDefaultFontPath("fonts/NotoSansCJKkr-Regular.otf")
              .setFontAttrId(R.attr.fontPath)
              .build()
      );
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

      actionBar=getSupportActionBar();
      actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);

    controller = ((BaseApplication) getApplication()).getDreamController();
    controller.init();

    mandaratFragment = ZoomableGridFragment.newInstance();
    mandaratFragment.setMaxZoomListener(this);
    mandaratFragment.setDream(controller.get().getTargetDream());

    List<String> targetItems = new ArrayList<>();
    Map<Integer, List<String>> subTargetItems = new HashMap<>();
    Map<String, List<Integer>> targetValueItems = new HashMap<>();
    Map<Integer, Map<String, ChartValue>> subTargetValueItems = new HashMap<>();

    RealmStringController stringController = new RealmStringController();
    int pos = 0;
    for (SubTarget targets : controller.get().getTargets()) {
        Log.d(TAG, "Dream Targets: " + targets.getName());
        targetItems.add(targets.getName());

        int tTotal = 0;
        int tOneMonth = 0;
        int tWeeks = 0;

        subTargetValueItems.put(pos, new HashMap<String, ChartValue>());
        List<String> subItems = new ArrayList<>();
        for (RealmString subTargets : targets.getObjectives()) {
            subItems.add(subTargets.getString());

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, -1);

            int sTotal = stringController.getTotalCount(subTargets.getString());
            int sOneMonth = stringController.getTotalCount(subTargets.getString(), calendar.getTime(), new Date());

            calendar.add(Calendar.MONTH, 1);
            calendar.add(Calendar.WEEK_OF_MONTH, -1);

            int sWeeks = stringController.getTotalCount(subTargets.getString(), calendar.getTime(), new Date());

            subTargetValueItems.get(pos).put(subTargets.getString(), new ChartValue(sWeeks, sOneMonth, sTotal));
            tTotal += sTotal;
            tOneMonth += sOneMonth;
            tWeeks += sWeeks;
        }

        subTargetItems.put(pos++, subItems);

        targetValueItems.put(targets.getName(), new ArrayList<Integer>());
        targetValueItems.get(targets.getName()).add(tWeeks);
        targetValueItems.get(targets.getName()).add(tOneMonth);
        targetValueItems.get(targets.getName()).add(tTotal);
    }

    mandaratFragment.setTargetItems(targetItems);

    chartFragment = new ChartFragment();
    chartFragment.setUserDream(controller.get().getTargetDream());
    chartFragment.setUserTargetItems(targetItems);
    chartFragment.setUserSubTargetItems(subTargetItems);
    chartFragment.setUserTargetValueItems(targetValueItems);
    chartFragment.setUserSubTargetValueItems(subTargetValueItems);

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
                , getSubTarget(position)
                , getFollowingDreamElements(position)))
        .addToBackStack(DreamListFragment.TAG)
        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        .commit();
  }

  private ArrayList<String> getFollowingDreamElements(int position) {
    RealmList<RealmString> list = controller.get().getTargets().get(position).getObjectives();
    ArrayList<String> resultList = new ArrayList<>();
    for (RealmString string : list) {
      resultList.add(string.getString());
    }
    return resultList;
  }

  private String getSubTarget(int position) {
    return controller.get().getTargets().get(position).getName();
  }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}

