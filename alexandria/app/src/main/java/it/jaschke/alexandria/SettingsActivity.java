package it.jaschke.alexandria;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by saj on 27/01/15.
 */
public class SettingsActivity extends PreferenceActivity implements Preference.OnPreferenceChangeListener{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        Preference preference = findPreference("pref_startFragment");
        preference.setOnPreferenceChangeListener(this);

        setPreferenceSummary(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

    private void setPreferenceSummary(Preference preference, Object value) {
        String stringValue = value.toString();

        if (preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(stringValue);
            if (prefIndex >= 0) {
                preference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        }
    }


    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        setPreferenceSummary(preference, newValue);
        return true;
    }

}
