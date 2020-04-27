package ru.geekbrains.myvkfriends.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.fragment_friend.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.geekbrains.myvkfriends.R
import ru.geekbrains.myvkfriends.mvp.model.entity.VkUser
import ru.geekbrains.myvkfriends.mvp.model.image.IImageLoader
import ru.geekbrains.myvkfriends.mvp.presenter.FriendPresenter
import ru.geekbrains.myvkfriends.mvp.view.FriendView
import ru.geekbrains.myvkfriends.ui.App
import ru.geekbrains.myvkfriends.ui.BackButtonListener
import javax.inject.Inject

class FriendFragment : MvpAppCompatFragment(), FriendView, BackButtonListener {

    companion object {
        private const val USER_KEY = "user"
        fun newInstance(user: VkUser) = FriendFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_KEY, user)
            }
        }
    }

    @InjectPresenter
    lateinit var presenter: FriendPresenter

    @ProvidePresenter
    fun providePresenter() = FriendPresenter(arguments!![USER_KEY] as VkUser).apply {
        App.instance.appComponent.inject(this)
    }

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        View.inflate(context, R.layout.fragment_friend, null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.instance.appComponent.inject(this)
    }

    override fun init() {

    }

    override fun loadAvatar(url: String) {
        imageLoader.loadInto(url, ff_avatar)
    }

    override fun setFirstName(text: String) {
        ff_first_name.text = text
    }

    override fun setLastName(text: String) {
        ff_last_name.text = text
    }

    override fun setId(id: Int) {
        ff_id.text = id.toString()
    }

    override fun setFollowersCount(followersCount: Int) {
        ff_followers_count.text = followersCount.toString()
    }

    override fun backClicked() = presenter.backClicked()
}