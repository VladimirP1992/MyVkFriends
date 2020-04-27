package ru.geekbrains.myvkfriends.mvp.presenter

import io.reactivex.rxjava3.core.Scheduler
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.geekbrains.myvkfriends.mvp.model.entity.VkUser
import ru.geekbrains.myvkfriends.mvp.model.repo.VkUserSessionRepo
import ru.geekbrains.myvkfriends.mvp.model.repo.VkUsersRepo
import ru.geekbrains.myvkfriends.mvp.presenter.list.IFriendListPresenter
import ru.geekbrains.myvkfriends.mvp.view.FriendsView
import ru.geekbrains.myvkfriends.mvp.view.list.FriendItemView
import ru.geekbrains.myvkfriends.navigation.Screens
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class FriendsPresenter(val mainThreadScheduler: Scheduler) : MvpPresenter<FriendsView>() {

    class FriendsListPresenter : IFriendListPresenter{
        val friends = mutableListOf<VkUser>()
        override var itemClickListener: ((FriendItemView) -> Unit)? = null

        override fun getCount() = friends.size

        override fun bindView(view: FriendItemView) {
            val friend = friends[view.pos]
            view.loadAvatar(friend.photo_50)
            view.setFirstName(friend.firstName)
            view.setLastName(friend.lastName)
        }
    }

    @Inject
    lateinit var userSessionRepo: VkUserSessionRepo
    @Inject
    lateinit var usersRepo: VkUsersRepo
    @Inject
    lateinit var router: Router

    val friendsListPresenter = FriendsListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

        loadMyData()
        loadFriendsData()

        friendsListPresenter.itemClickListener = { itemView ->
            val friend = friendsListPresenter.friends[itemView.pos]
            router.navigateTo(Screens.FriendScreen(friend))
        }
    }

    private fun loadMyData(){
        userSessionRepo.getUserSession()
            .observeOn(mainThreadScheduler)
            .subscribe({
                usersRepo.getUser(it.accessToken, it.userId)
                    .observeOn(mainThreadScheduler)
                    .subscribe({
                        viewState.loadAvatar(it.photo_50)
                        viewState.setFirstName(it.firstName)
                    },{
                        Timber.e(it)
                    })
            },{
                Timber.e(it)
            })
    }

    private fun loadFriendsData(){
        usersRepo.getFriends()
            .observeOn(mainThreadScheduler)
            .subscribe({ users ->
                friendsListPresenter.friends.clear()
                friendsListPresenter.friends.addAll(users)
                viewState.updateList()
            },{
                Timber.e(it)
            })

    }

    fun backClicked(): Boolean {
        router.exit()
        return true
    }
}