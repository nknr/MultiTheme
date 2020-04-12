/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.itdose.multitheme.view.fragment;

import android.os.Build;
import android.os.Bundle;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.itdose.multitheme.R;
import com.itdose.multitheme.utils.ThemeHelper;

import java.util.Objects;

public class SettingsFragment extends PreferenceFragmentCompat {

    public static final String TAG = "SettingsFragmentTag";

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preference, rootKey);

        ListPreference themePreference = findPreference("themePref");
        if (themePreference != null) {
            themePreference.setOnPreferenceChangeListener(
                    (preference, newValue) -> {
                        String themeOption = (String) newValue;
                        ThemeHelper.applyTheme(themeOption);
                        Objects.requireNonNull(getActivity()).recreate();
                        return true;
                    });
        }
    }
}
