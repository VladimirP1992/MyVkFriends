package ru.geekbrains.myvkfriends.di.modules

import android.widget.ImageView
import dagger.Module
import dagger.Provides
import ru.geekbrains.myvkfriends.mvp.model.image.IImageLoader
import ru.geekbrains.myvkfriends.ui.image.GlideImageLoader

@Module
class ImageModule {

    @Provides
    fun imageLoader(): IImageLoader<ImageView> {
        return GlideImageLoader()
    }
}