package com.example.yyeon.cubeit.model;

import android.support.annotation.Nullable;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by jkimab on 2017. 8. 12..
 */

public class RealmString extends RealmObject {
  @PrimaryKey
  private String string;
  RealmList<RealmDate> values;

  public RealmString() {
  }

  public RealmString(String string) {
    this.string = string;
  }

  public RealmString(String string, RealmList<RealmDate> values) {
    this.string = string;
    this.values = values;
  }

  public String getString() {
    return string;
  }

  public void setString(String string) {
    this.string = string;
  }

  public RealmList<RealmDate> getValues() {
    return values;
  }

  public void setValues(RealmList<RealmDate> values) {
    this.values = values;
  }
}
