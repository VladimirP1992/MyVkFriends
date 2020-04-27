package ru.geekbrains.myvkfriends.di.modules

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.geekbrains.myvkfriends.mvp.model.api.IVkApi
import ru.geekbrains.myvkfriends.mvp.model.network.INetworkStatus
import ru.geekbrains.myvkfriends.ui.App
import ru.geekbrains.myvkfriends.ui.network.AndroidNetworkStatus
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Named("authUrl")
    @Provides
    fun authUrl(): String {
        return "https://oauth.vk.com/authorize?client_id=7430433&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=friends&response_type=token&v=5.52"
    }

    @Named("baseUrl")
    @Provides
    fun baseUrl(): String {
        return "https://api.vk.com/"
    }

    @Provides
    fun gson(): Gson {
        return GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun api(@Named("baseUrl") baseUrl: String, gson: Gson): IVkApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(IVkApi::class.java)
    }

    @Provides
    fun networkStatus(app: App): INetworkStatus {
        return AndroidNetworkStatus(app)
    }

}