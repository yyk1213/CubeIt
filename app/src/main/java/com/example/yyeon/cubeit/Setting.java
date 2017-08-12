package com.example.yyeon.cubeit;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

public class Setting extends PreferenceFragmentCompat {

//    private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
//        @Override
//        public boolean onPreferenceChange(Preference preference, Object value) {
//            String stringValue = value.toString();
//
//            if (preference instanceof Preference) {
//                preference.setSummary(stringValue);
//            }
//            return true;
//        }
//    };
//
//    private static void bindPreferenceSummaryToValue(Preference preference) {
//        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);
//
//        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
//                PreferenceManager
//                        .getDefaultSharedPreferences(preference.getContext())
//                        .getString(preference.getKey(), ""));
////    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setupActionBar();
//    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        getActivity().setTitle("설정");
        addPreferencesFromResource(R.xml.setting);
    }
//
//    private void setupActionBar() {
//        ActionBar actionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }
//    }

//
//    @Override
//    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
//    public void onBuildHeaders(List<PreferenceActivity.Header> target) {
//        loadHeadersFromResource(R.xml.setting_headers, target);
//    }
//
//    protected boolean isValidFragment(String fragmentName) {
//
//        return PreferenceFragment.class.getName().equals(fragmentName)
//                || JoinFragment.class.getName().equals(fragmentName)
//                || ImageSaveFragment.class.getName().equals(fragmentName)
//                || RockScreenFragment.class.getName().equals(fragmentName)
//                || MandaratOpenFragment.class.getName().equals(fragmentName)
//                || PushFragment.class.getName(),equals(fragmentName);
//    }
//
//    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
//    public static class JoinFragment extends PreferenceFragment {
//        @Override
//        public void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            addPreferencesFromResource(R.xml.setting_join);
//            setHasOptionsMenu(true);
//
//            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
//            // to their values. When their values change, their summaries are
//            // updated to reflect the new value, per the Android Design
//            // guidelines.
//            bindPreferenceSummaryToValue(findPreference("example_text"));
//            bindPreferenceSummaryToValue(findPreference("example_list"));
//        }
//
//        @Override
//
//        public boolean onOptionsItemSelected(MenuItem item) {
//            int id = item.getItemId();
//            if (id == android.R.id.home) {
//                startActivity(new Intent(getActivity(), Setting.class));
//                return true;
//            }
//            return super.onOptionsItemSelected(item);
//        }
//    }
//
//    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
//    public static class ImageSaveFragment extends PreferenceFragment {
//        @Override
//        public void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            addPreferencesFromResource(R.xml.setting_image_save);
//            setHasOptionsMenu(true);
//
//            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
//            // to their values. When their values change, their summaries are
//            // updated to reflect the new value, per the Android Design
//            // guidelines.
//            bindPreferenceSummaryToValue(findPreference("notifications_new_message_ringtone"));
//        }
//
//        @Override
//        public boolean onOptionsItemSelected(MenuItem item) {
//            int id = item.getItemId();
//            if (id == android.R.id.home) {
//                startActivity(new Intent(getActivity(), Setting.class));
//                return true;
//            }
//            return super.onOptionsItemSelected(item);
//        }
//    }
//
//    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
//    public static class RockScreenFragment extends PreferenceFragment {
//        @Override
//        public void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            addPreferencesFromResource(R.xml.setting_rock_screen);
//            setHasOptionsMenu(true);
//
//            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
//            // to their values. When their values change, their summaries are
//            // updated to reflect the new value, per the Android Design
//            // guidelines.
//            bindPreferenceSummaryToValue(findPreference("sync_frequency"));
//        }
//
//        @Override
//        public boolean onOptionsItemSelected(MenuItem item) {
//            int id = item.getItemId();
//            if (id == android.R.id.home) {
//                startActivity(new Intent(getActivity(), Setting.class));
//                return true;
//            }
//            return super.onOptionsItemSelected(item);
//        }
//    }
//
//
//    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
//    public static class MandaratOpenFragment extends PreferenceFragment {
//        @Override
//        public void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            addPreferencesFromResource(R.xml.setting_madarat_open);
//            setHasOptionsMenu(true);
//
//            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
//            // to their values. When their values change, their summaries are
//            // updated to reflect the new value, per the Android Design
//            // guidelines.
//            bindPreferenceSummaryToValue(findPreference("sync_frequency"));
//        }
//
//        @Override
//        public boolean onOptionsItemSelected(MenuItem item) {
//            int id = item.getItemId();
//            if (id == android.R.id.home) {
//                startActivity(new Intent(getActivity(), Setting.class));
//                return true;
//            }
//            return super.onOptionsItemSelected(item);
//        }
//    }
//
//
//    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
//    public static class PushFragment extends PreferenceFragment {
//        @Override
//        public void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            addPreferencesFromResource(R.xml.setting_push);
//            setHasOptionsMenu(true);
//
//            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
//            // to their values. When their values change, their summaries are
//            // updated to reflect the new value, per the Android Design
//            // guidelines.
//            bindPreferenceSummaryToValue(findPreference("sync_frequency"));
//        }
//
//        @Override
//        public boolean onOptionsItemSelected(MenuItem item) {
//            int id = item.getItemId();
//            if (id == android.R.id.home) {
//                startActivity(new Intent(getActivity(), Setting.class));
//                return true;
//            }
//            return super.onOptionsItemSelected(item);
//        }
//    }
}
