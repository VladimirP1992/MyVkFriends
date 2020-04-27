package ru.geekbrains.myvkfriends.mvp.presenter

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.geekbrains.myvkfriends.mvp.view.MainView
import ru.geekbrains.myvkfriends.navigation.Screens
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class MainPresenter() : MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        Timber.d("MainPresenter()")
        router.replaceScreen(Screens.AuthScreen())
    }

    fun backClicked() {
        router.exit()
    }

}