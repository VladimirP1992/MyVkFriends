package ru.geekbrains.myvkfriends.ui.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.activity_settings.*
import ru.geekbrains.myvkfriends.R
import ru.geekbrains.myvkfriends.mvp.model.PreferenceConstants

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.settings)

        init()
    }

    private fun init() {

        toolbar.setNavigationOnClickListener { onBackPressed() }

        lightThemeSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            getSharedPreferences(PreferenceConstants.SHARED_PREFFERENCES_NAME, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(PreferenceConstants.LIGHT_THEME_SWITCH_STATE, isChecked)
                .apply()
        }

        lightThemeSwitch.isChecked = getSharedPreferences(PreferenceConstants.SHARED_PREFFERENCES_NAME, Context.MODE_PRIVATE)
            .getBoolean(PreferenceConstants.LIGHT_THEME_SWITCH_STATE, false)
    }
}
