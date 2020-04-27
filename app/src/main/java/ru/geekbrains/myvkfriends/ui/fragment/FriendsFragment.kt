package ru.geekbrains.myvkfriends.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_friend.*
import kotlinx.android.synthetic.main.fragment_friends.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.geekbrains.myvkfriends.R
import ru.geekbrains.myvkfriends.mvp.model.image.IImageLoader
import ru.geekbrains.myvkfriends.mvp.presenter.FriendsPresenter
import ru.geekbrains.myvkfriends.mvp.view.FriendsView
import ru.geekbrains.myvkfriends.ui.App
import ru.geekbrains.myvkfriends.ui.BackButtonListener
import ru.geekbrains.myvkfriends.ui.adapter.FriendsRVAdapter
import timber.log.Timber
import javax.inject.Inject

class FriendsFragment : MvpAppCompatFragment(), FriendsView, BackButtonListener {

    companion object {
        fun newInstance() = FriendsFragment()
    }

    @InjectPresenter
    lateinit var presenter: FriendsPresenter

    @ProvidePresenter
    fun providePresenter() = FriendsPresenter(AndroidSchedulers.mainThread()).apply {
        App.instance.appComponent.inject(this)
    }

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    var adapter: FriendsRVAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        View.inflate(context, R.layout.fragment_friends, null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.instance.appComponent.inject(this)
    }

    override fun init() {
        rv_friends.layoutManager = LinearLayoutManager(context)
        adapter = FriendsRVAdapter(presenter.friendsListPresenter).apply {
            App.instance.appComponent.inject(this)
        }
        rv_friends.adapter = adapter
    }

    override fun loadAvatar(url: String) {
        imageLoader.loadInto(url, fmp_avatar)
    }

    override fun setFirstName(text: String) {
        fmp_first_name.text = text
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backClicked() = presenter.backClicked()
}