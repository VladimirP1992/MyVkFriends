package ru.geekbrains.myvkfriends.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_auth.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.geekbrains.myvkfriends.R
import ru.geekbrains.myvkfriends.mvp.presenter.AuthPresenter
import ru.geekbrains.myvkfriends.mvp.view.AuthView
import ru.geekbrains.myvkfriends.ui.App
import ru.geekbrains.myvkfriends.ui.BackButtonListener

class AuthFragment : MvpAppCompatFragment(), AuthView, BackButtonListener {

    companion object {
        fun newInstance() = AuthFragment()
    }

    @InjectPresenter
    lateinit var presenter: AuthPresenter

    @ProvidePresenter
    fun providePresenter() = AuthPresenter(AndroidSchedulers.mainThread()).apply {
        App.instance.appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        View.inflate(context, R.layout.fragment_auth, null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.instance.appComponent.inject(this)
    }



    override fun init() {
        wv_auth.settings.javaScriptEnabled = true
        val webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                presenter.parseUrl(url!!)
                return false
            }
        }
        wv_auth.webViewClient = webViewClient
    }

    override fun loadUrl(authUrl: String) {
        wv_auth.loadUrl(authUrl)
    }

    override fun backClicked() = presenter.backClicked()
}