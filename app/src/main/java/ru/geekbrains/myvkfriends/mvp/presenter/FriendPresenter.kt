package ru.geekbrains.myvkfriends.mvp.presenter

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.geekbrains.myvkfriends.mvp.model.entity.VkUser
import ru.geekbrains.myvkfriends.mvp.view.FriendView
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class FriendPresenter(val vkUser: VkUser) : MvpPresenter<FriendView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        with(vkUser){
            viewState.loadAvatar(photo_50)
            viewState.setFirstName(firstName)
            viewState.setLastName(lastName)
            viewState.setId(id)
            viewState.setFollowersCount(followersCount)
        }
    }

    fun backClicked(): Boolean {
        router.exit()
        return true
    }
}