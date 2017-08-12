package com.example.yyeon.cubeit.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by jkimab on 2017. 8. 12..
 */

public class RealmString extends RealmObject {
  @PrimaryKey
  private String string;

  public RealmString() {
  }

  public RealmString(String string) {
    this.string = string;
  }

  public String getString() {
    return string;
  }

  public void setString(String string) {
    this.string = string;
  }
}
