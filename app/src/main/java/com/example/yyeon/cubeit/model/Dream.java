package com.example.yyeon.cubeit.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by jkimab on 2017. 8. 12..
 */

public class Dream extends RealmObject {
  @PrimaryKey
  long key;
  String targetDream;
  RealmList<SubTarget> targets;

  public Dream() {
  }

  public Dream(long key, String targetDream, RealmList<SubTarget> targets) {
    this.key = key;
    this.targetDream = targetDream;
    this.targets = targets;
  }

  public String getTargetDream() {
    return targetDream;
  }

  public void setTargetDream(String targetDream) {
    this.targetDream = targetDream;
  }

  public RealmList<SubTarget> getTargets() {
    return targets;
  }

  public void setTargets(RealmList<SubTarget> targets) {
    this.targets = targets;
  }
}
