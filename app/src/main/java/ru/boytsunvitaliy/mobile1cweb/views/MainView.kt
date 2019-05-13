package ru.boytsunvitaliy.mobile1cweb.views

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.webkit.DownloadListener
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.main_view.*
import ru.boytsunvitaliy.mobile1cweb.R
import ru.boytsunvitaliy.mobile1cweb.closeAppDioalog
import ru.boytsunvitaliy.mobile1cweb.models.AuthenticationState
import ru.boytsunvitaliy.mobile1cweb.setSupportActionBar
import ru.boytsunvitaliy.mobile1cweb.viewmodels.MainViewModel


class MainView : BaseView(), DownloadListener {

    private val viewModel: MainViewModel by activityViewModels()
    private var progressBarMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.main_view, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSupportActionBar(toolbar)
        webView.webViewClient = WebClient()
        val settings = webView.settings
        settings.javaScriptEnabled = true
        webView.setDownloadListener(this)
        val navController = findNavController()
        viewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState ->
            when (authenticationState) {
                AuthenticationState.AUTHENTICATED -> loadApp()
                AuthenticationState.UNAUTHENTICATED -> navController.navigate(R.id.toLoginView)
                else -> {
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        progressBarMenuItem = menu.findItem(R.id.item_load)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_logout -> {
                viewModel.logout()
                true
            }
            R.id.item_load -> {
                webView.reload()
                true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    private fun loadApp() {
        webView.loadUrl("http://www.iqsklad.ru")
    }

    override fun onDownloadStart(
        url: String?,
        userAgent: String?,
        contentDisposition: String?,
        mimetype: String?,
        contentLength: Long
    ) {
        Log.e("onDownloadStart", "url: $url $contentLength")
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) webView.goBack()
        else this@MainView.closeAppDioalog()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    inner class WebClient : WebViewClient() {
        @TargetApi(Build.VERSION_CODES.N)
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            view.loadUrl(request.url.toString())
            return true
        }

        // Для старых устройств
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            progressBarMenuItem?.setActionView(R.layout.actionbar_indeterminate_progress)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            progressBarMenuItem?.actionView = null
        }
    }
}

