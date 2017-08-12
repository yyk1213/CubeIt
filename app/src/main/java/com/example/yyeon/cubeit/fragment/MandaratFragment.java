package com.example.yyeon.cubeit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yyeon.cubeit.R;

public class MandaratFragment extends Fragment {

    public MandaratFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Cube it!");
        return inflater.inflate(R.layout.fragment_mandarat, container,false);
    }

}
