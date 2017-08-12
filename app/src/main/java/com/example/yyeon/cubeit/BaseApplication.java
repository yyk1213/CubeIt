package com.example.yyeon.cubeit;

import android.app.Application;

import com.example.yyeon.cubeit.model.controller.DreamController;
import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by jkimab on 2017. 8. 12..
 */

public class BaseApplication extends Application {
  private DreamController dreamController;

  @Override
  public void onCreate() {
    super.onCreate();

    Realm.init(getApplicationContext());
    RealmConfiguration configuration = new RealmConfiguration.Builder()
        .name("default.realm")
        .schemaVersion(1)
        .build();
    Realm.setDefaultConfiguration(configuration);

    //TODO: only enable in debug build
    Stetho.initialize(Stetho.newInitializerBuilder(this)
        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
        .build());

    dreamController = new DreamController();
  }

  public DreamController getDreamController() {
    return dreamController;
  }
}
