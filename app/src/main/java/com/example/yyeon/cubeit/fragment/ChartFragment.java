package com.example.yyeon.cubeit.fragment;

import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.dongseok.libarary.RadarChartView;
import com.dongseok.libarary.RadarTextItemClickListener;
import com.example.yyeon.cubeit.BaseApplication;
import com.example.yyeon.cubeit.R;
import com.example.yyeon.cubeit.model.ChartValue;
import com.example.yyeon.cubeit.model.Dream;
import com.example.yyeon.cubeit.model.RealmString;
import com.example.yyeon.cubeit.model.SubTarget;
import com.example.yyeon.cubeit.model.controller.DreamController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ChartFragment extends Fragment {

    private static final String TAG = "ChartFragment";
    private TextView textView;
    private TextView seeBarTextView;
    private AppCompatSeekBar seekBar;
    private RadarChartView radarChartView;

    private String userDream;
    private List<String> userTargetItems;
    private Map<String, List<Integer>> userTargetValueItems;
    private Map<Integer,List<String>> userSubTargetItems;
    private Map<Integer, Map<String, ChartValue>>  userSubTargetValueItems;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("차트분석");
        View view = inflater.inflate(R.layout.fragment_chart, container, false);


        // TODO 그래프 소수점 나타내기 ( Dream 영역. 계산 값 살리기 위해 ex - 0.8 )
        // TODO 글자 길이 또는 수 제한 또는 변형

       final TabLayout tabLayout = (TabLayout)view.findViewById(R.id.chart_tabLayout);

        textView = (TextView)view.findViewById(R.id.chart_dream_text_view);
        radarChartView = (RadarChartView) view.findViewById(R.id.radar_chart_view);
        seeBarTextView = (TextView) view.findViewById(R.id.chart_seekBar_value_view);
        seekBar = (AppCompatSeekBar) view.findViewById(R.id.chart_seekBar);

        radarChartView.setMaxValue(7);
        textView.setText(userDream);
        radarChartView.setTextValueList(userTargetItems);

        int total = 0;
        for(int i=0 ; i<userTargetItems.size(); i++){
            int count = userTargetValueItems.get(userTargetItems.get(i)).get(0) / userSubTargetItems.get(i).size();
            radarChartView.setPointValuePosition(i, count);
            total += count;
        }
        total =  (total * 100) / (7 * userTargetItems.size());
        seekBar.setProgress(total);
        seeBarTextView.setText(String.valueOf(total) + "%");

        radarChartView.setListener(new RadarTextItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                // GET SubTarget
                if(textView.getText().equals(userDream)) {
                    textView.setText(userTargetItems.get(pos));
                    radarChartView.setTextValueList(userSubTargetItems.get(pos));
                    int total = 0;
                    for(int i=0; i<userSubTargetItems.get(pos).size(); i++) {
                        int count = userSubTargetValueItems
                                .get(pos)
                                .get(userSubTargetItems.get(pos).get(i))
                                .getValue(0);
                        radarChartView.setPointValuePosition(i, count);
                        total += count;
                    }
                    total =  (total * 100) / (7*userSubTargetItems.size());
                    seekBar.setProgress(total);
                    seeBarTextView.setText(String.valueOf(total) + "%");
                }
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // position 0 1 2

                textView.setText(userDream);
                radarChartView.setTextValueList(userTargetItems);
                int total = 0;
                for(int i=0 ; i<userTargetItems.size(); i++){
                    int count = userTargetValueItems.get(userTargetItems.get(i)).get(tab.getPosition()) / userSubTargetItems.get(i).size();
                    radarChartView.setPointValuePosition(i, count);
                    total += count;
                }

                total =  (total * 100) / (7 * userTargetItems.size());
                seekBar.setProgress(total);
                seeBarTextView.setText(String.valueOf(total) + "%");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                textView.setText(userDream);
                radarChartView.setTextValueList(userTargetItems);
                int total = 0;
                for(int i=0 ; i<userTargetItems.size(); i++){
                    int count = userTargetValueItems.get(userTargetItems.get(i)).get(tab.getPosition()) / userSubTargetItems.get(i).size();
                    radarChartView.setPointValuePosition(i, count);
                    total += count;
                }

                total =  (total * 100) / (7 * userTargetItems.size());
                seekBar.setProgress(total);
                seeBarTextView.setText(String.valueOf(total) + "%");
            }
        });

        return view;
    }

    public void setUserDream(String userDream) {
        this.userDream = userDream;
    }

    public void setUserTargetItems(List<String> userTargetItems) {
        this.userTargetItems = userTargetItems;
    }

    public void setUserTargetValueItems(Map<String, List<Integer>> userTargetValueItems) {
        this.userTargetValueItems = userTargetValueItems;
    }

    public void setUserSubTargetItems(Map<Integer, List<String>> userSubTargetItems) {
        this.userSubTargetItems = userSubTargetItems;
    }

    public void setUserSubTargetValueItems(Map<Integer, Map<String, ChartValue>> userSubTargetValueItems) {
        this.userSubTargetValueItems = userSubTargetValueItems;
    }
}
