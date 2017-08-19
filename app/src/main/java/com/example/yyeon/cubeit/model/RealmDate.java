package com.example.yyeon.cubeit.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.Index;
/**
 * Created by dongseok on 2017. 8. 17..
 */

public class RealmDate extends RealmObject {

    @Index
    Date date;

    public RealmDate() {
    }

    public RealmDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
