package com.example.yyeon.cubeit.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.widget.Toast;

import com.example.yyeon.cubeit.R;

public class SettingFragment extends PreferenceFragmentCompat {

//    SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(getActivity());
//    SharedPreferences.Editor editor = mSettings.edit();--사용자 값 변경사항 저장

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        final Context myContext = this.getActivity();
        myContext.setTheme(R.style.settingTheme);

        addPreferencesFromResource(R.xml.setting);
        findPreference("pref_key_join").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(){
            @Override
            public boolean onPreferenceClick(Preference preference) {

                Toast.makeText(preference.getContext(), "사용할 수 없는 기능입니다.", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        findPreference("pref_key_image_save").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(){
            @Override
            public boolean onPreferenceClick(Preference preference) {

                Toast.makeText(preference.getContext(), "사용할 수 없는 기능입니다.", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        findPreference("pref_key_rock_screen").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(){
            @Override
            public boolean onPreferenceClick(Preference preference) {

                Toast.makeText(preference.getContext(), "사용할 수 없는 기능입니다.", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}

