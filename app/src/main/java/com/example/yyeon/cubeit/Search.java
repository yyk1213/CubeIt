package com.example.yyeon.cubeit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Search extends Fragment  {
    public Search() {
    }

//    ListView list;
//    ListViewAdapter adaper;
//    SearchView searchView;
//    String[] vacation;
//    ArrayList<vacationPlan> arrayList=new ArrayList<vacationPlan>();
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        vacation=new String[]{"방학숙제","방학계획","방학여행","방학준비"};
//        list=(ListView)getActivity().findViewById(R.id.search_listView);
//
//        for(int i=0; i<vacation.length;i++){
//            vacationPlan
//
//        }
//
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);

    }
}
