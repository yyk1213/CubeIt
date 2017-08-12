package com.example.yyeon.cubeit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yyeon.cubeit.R;

public class ChartFragment extends Fragment {
    public ChartFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("차트분석");
        return inflater.inflate(R.layout.fragment_chart, container,false);
    }
}
