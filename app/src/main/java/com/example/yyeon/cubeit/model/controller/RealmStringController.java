package com.example.yyeon.cubeit.model.controller;

import com.example.yyeon.cubeit.model.RealmDate;
import com.example.yyeon.cubeit.model.RealmString;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by dongseok on 2017. 8. 18..
 */

public class RealmStringController implements Controller<RealmString>{

    private static final String TAG = "RealmStringController";

    @Override
    public void copyToRealmOrUpdate(RealmString object) {

    }

    @Override
    public RealmString get() {
        return null;
    }

    @Override
    public void deleteAll() {

    }

    public int getTotalCount(String str){
        Realm realm = null;
        int count = 0;
        try {
            realm = Realm.getDefaultInstance();
            count = realm.where(RealmString.class).equalTo("string",str).findFirst().getValues().size();
        } finally {
            if(realm != null) {
                realm.close();
            }
        }
        //Log.d(TAG, "total count: " + count);
        return count;
    }

    public int getTotalCount(String str, Date start, Date end){
        Realm realm = null;
        int count = 0;
        try {
            realm = Realm.getDefaultInstance();
            RealmList<RealmDate> results = realm.where(RealmString.class).equalTo("string",str).findFirst().getValues();
            count = results.where().between("date", start, end).findAll().size();
        } finally {
            if(realm != null) {
                realm.close();
            }
        }
        //Log.d(TAG, "query count: " + count);
        return count;
    }
}
