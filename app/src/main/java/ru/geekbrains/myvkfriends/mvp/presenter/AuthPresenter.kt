package ru.geekbrains.myvkfriends.mvp.presenter

import io.reactivex.rxjava3.core.Scheduler
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.geekbrains.myvkfriends.mvp.model.api.Constants
import ru.geekbrains.myvkfriends.mvp.model.entity.VkUserSession
import ru.geekbrains.myvkfriends.mvp.model.repo.VkUserSessionRepo
import ru.geekbrains.myvkfriends.mvp.view.AuthView
import ru.geekbrains.myvkfriends.navigation.Screens
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

@InjectViewState
class AuthPresenter(val mainThread: Scheduler) : MvpPresenter<AuthView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var userSessionRepo: VkUserSessionRepo

    @field:[Inject Named("authUrl")]
    lateinit var authUrl: String

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.loadUrl(authUrl)
    }

    fun parseUrl(url: String){
        if (!url.substringBefore("#").equals(Constants.redirectUri))
            return

        val userId = url.substringAfter("user_id=").toInt()
        val token = url.substringAfter("access_token=").substringBefore("&")
        val expiresIn = url.substringAfter("expires_in=").substringBefore("&").toInt()

        userSessionRepo.putUserSession(VkUserSession(Constants.applicationId, userId, token, expiresIn))
            .observeOn(mainThread)
            .subscribe()

        authorized()
    }

    private fun authorized(){
        router.replaceScreen(Screens.FriendsScreen())
    }

    fun backClicked(): Boolean {
        router.exit()
        return true
    }

}