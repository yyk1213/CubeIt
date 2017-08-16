package com.example.yyeon.cubeit.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by jkimab on 2017. 8. 12..
 */

public class SubTarget extends RealmObject {
  String name;
  RealmList<RealmString> objectives;

  public SubTarget() {
  }

  public SubTarget(String name, RealmList<RealmString> objectives) {
    this.name = name;
    this.objectives = objectives;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public RealmList<RealmString> getObjectives() {
    return objectives;
  }

  public void setObjectives(RealmList<RealmString> objectives) {
    this.objectives = objectives;
  }
}
