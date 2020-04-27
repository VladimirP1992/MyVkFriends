package ru.geekbrains.myvkfriends.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_friend.view.*
import ru.geekbrains.myvkfriends.R
import ru.geekbrains.myvkfriends.mvp.model.image.IImageLoader
import ru.geekbrains.myvkfriends.mvp.presenter.list.IFriendListPresenter
import ru.geekbrains.myvkfriends.mvp.view.list.FriendItemView
import javax.inject.Inject

class FriendsRVAdapter (val presenter: IFriendListPresenter) : RecyclerView.Adapter<FriendsRVAdapter.ViewHolder>() {

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_friend, parent, false))

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener { presenter.itemClickListener?.invoke(holder) }
        presenter.bindView(holder)
    }


    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer, FriendItemView {
        override var pos = -1

        override fun loadAvatar(url: String) = with(containerView) {
            imageLoader.loadInto(url, if_avatar)
        }

        override fun setFirstName(text: String)= with(containerView) {
            if_first_name.text = text
        }

        override fun setLastName(text: String)= with(containerView) {
            if_second_name.text = text
        }
    }

}