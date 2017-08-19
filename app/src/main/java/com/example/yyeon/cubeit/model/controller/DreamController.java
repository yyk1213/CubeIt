package com.example.yyeon.cubeit.model.controller;

import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;

import com.example.yyeon.cubeit.model.Dream;
import com.example.yyeon.cubeit.model.RealmDate;
import com.example.yyeon.cubeit.model.RealmString;
import com.example.yyeon.cubeit.model.SubTarget;

import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

public class DreamController implements Controller<Dream> {
  private static final String TAG = "DreamController";

  @Override
  public void copyToRealmOrUpdate(final Dream object) {
    Realm realm = null;
    try {
      realm = Realm.getDefaultInstance();
      realm.executeTransaction(new Realm.Transaction() {
        @Override
        public void execute(Realm realm) {
          realm.insertOrUpdate(object);
        }
      });
    } finally {
      if(realm != null) {
        realm.close();
      }
    }
  }

  @Override
  @Nullable
  public Dream get() {
    Realm realm = null;
    List<Dream> result = null;
    Dream finalDream = null;
    try {
      realm = Realm.getDefaultInstance();
      result = realm.copyFromRealm(realm
          .where(Dream.class)
          .findAll());
    } finally {
      if (realm != null) {
        realm.close();
      }
    }

    for (Dream dream : result) {
      Log.d(TAG, "get(): " + dream.getTargetDream());
      finalDream = dream;
    }

    return finalDream;
  }

  @Override
  public void deleteAll() {
    Realm realm = null;
    try {
      realm = Realm.getDefaultInstance();
      realm.executeTransactionAsync(new Realm.Transaction() {
        @Override
        public void execute(Realm realm) {
          realm.delete(Dream.class);
        }
      });
    } finally {
      if (realm != null) {
        realm.close();
      }
    }
  }


  @VisibleForTesting
  private RealmList<SubTarget> getFollowingDreamElements() {
    RealmList<SubTarget> dreamList = new RealmList<>();
    dreamList.add(new SubTarget("Lose Belly Fat", getSubTargetItems()));
    dreamList.add(new SubTarget("Lose Leg Fat", getSubTargetItems()));
    dreamList.add(new SubTarget("Good Eating Habit", getSubTargetItems()));

    dreamList.add(new SubTarget("Less Drinking Days", getSubTargetItems()));
    dreamList.add(new SubTarget("Lose Belly Fat", getSubTargetItems()));
    dreamList.add(new SubTarget("Lose Leg Fat", getSubTargetItems()));

    dreamList.add(new SubTarget("Good Eating Habit", getSubTargetItems()));
    dreamList.add(new SubTarget("Lose Belly Fat", getSubTargetItems()));
    dreamList.add(new SubTarget("Lose Leg Fat", getSubTargetItems()));
    return dreamList;
  }

  @VisibleForTesting
  private String getUserDream() {
    return "Diet";
  }

  @VisibleForTesting
  private RealmList<RealmString> getSubTargetItems() {
    RealmList<RealmString> userTargetItems = new RealmList<RealmString>();
    userTargetItems.add(new RealmString("Luck", getStringItems()));
    userTargetItems.add(new RealmString("Leadership", getStringItems()));
    userTargetItems.add(new RealmString("Software Development", getStringItems()));
    userTargetItems.add(new RealmString("Personality", getStringItems()));
    userTargetItems.add(new RealmString("Self-Control", getStringItems()));
    userTargetItems.add(new RealmString("Executive Abilities", getStringItems()));
    userTargetItems.add(new RealmString("Trend Analyzing Abilities", getStringItems()));
    userTargetItems.add(new RealmString("Attractiveness", getStringItems()));
    return userTargetItems;
  }

  @VisibleForTesting
  private RealmList<RealmDate> getStringItems(){
    RealmList<RealmDate> userPointItems = new RealmList<>();
    userPointItems.add(new RealmDate(new Date()));
    return userPointItems;
  }


  // TODO: Should Move to splash screen
  // TODO: for testing purpose, initialize fake data for user
  public void init() {
    Dream userDream = new Dream(1, getUserDream(), getFollowingDreamElements());
    copyToRealmOrUpdate(userDream);
  }
}
