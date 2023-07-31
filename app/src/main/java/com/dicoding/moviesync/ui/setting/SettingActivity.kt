package com.dicoding.moviesync.ui.setting

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.dicoding.moviesync.R
import com.dicoding.moviesync.notification.DailyReminder
import com.dicoding.moviesync.utils.DarkMode
import java.util.Locale


class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        val toolbar: Toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.title = getString(R.string.actionbar_settings)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            val notificationSwitch =
                findPreference<SwitchPreference>(getString(R.string.pref_key_notify))
            notificationSwitch?.setOnPreferenceChangeListener { _, newValue ->
                val isNotificationEnabled = newValue as Boolean
                if (isNotificationEnabled) {
                    DailyReminder().setDailyReminder(requireContext())
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.notification_turned_on), Toast.LENGTH_SHORT
                    )
                        .show()
                } else {
                    DailyReminder().cancelAlarm(requireContext())
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.notification_turned_off), Toast.LENGTH_SHORT
                    )
                        .show()
                }
                true
            }

            val themePreference =
                findPreference<ListPreference>(getString(R.string.pref_key_dark))
            themePreference?.setOnPreferenceChangeListener { _, newValue ->
                val theme = DarkMode.valueOf(newValue.toString().uppercase(Locale.US))
                updateTheme(theme.value)
            }
        }

        private fun updateTheme(mode: Int): Boolean {
            AppCompatDelegate.setDefaultNightMode(mode)
            requireActivity().recreate()
            return true
        }
    }

}