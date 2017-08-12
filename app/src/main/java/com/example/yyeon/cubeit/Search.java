package com.example.yyeon.cubeit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

public class Search extends Fragment {
    MaterialSearchView search_view;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().setTitle("검색");

        inflater.inflate(R.menu.search_bar, menu);
        search_view = (MaterialSearchView) getView().findViewById(R.id.search_view);
        MenuItem item = menu.findItem(R.id.action_search);
        search_view.setMenuItem(item);
    }

}
