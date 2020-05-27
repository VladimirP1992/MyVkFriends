package ru.geekbrains.myvkfriends.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.geekbrains.myvkfriends.R
import ru.geekbrains.myvkfriends.mvp.model.PreferenceConstants
import ru.geekbrains.myvkfriends.mvp.presenter.MainPresenter
import ru.geekbrains.myvkfriends.mvp.view.MainView
import ru.geekbrains.myvkfriends.ui.App
import ru.geekbrains.myvkfriends.ui.BackButtonListener
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    val REQUEST_CODE = 11

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    val navigator = SupportAppNavigator(this, R.id.container)

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter() = MainPresenter().apply {
        App.instance.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme()

        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        App.instance.appComponent.inject(this)
    }

    private fun setTheme(){
        val isLightTheme = getSharedPreferences(PreferenceConstants.SHARED_PREFFERENCES_NAME, Context.MODE_PRIVATE)
            .getBoolean(PreferenceConstants.LIGHT_THEME_SWITCH_STATE, false)
        when(isLightTheme){
            true -> {setTheme(R.style.AppThemeLight)
            }
            else -> {setTheme(R.style.AppTheme)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?) =
        MenuInflater(this).inflate(R.menu.main_menu, menu).let { true }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.settings -> openSettings()
        else -> false
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if(it is BackButtonListener && it.backClicked()){
                return
            }
        }
        presenter.backClicked()
    }

    private fun openSettings(): Boolean {
        startActivityForResult(Intent(this, SettingsActivity::class.java), REQUEST_CODE)
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE)
            recreate()
    }
}
