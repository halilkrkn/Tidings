package com.example.tidings.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.tidings.R
import com.example.tidings.databinding.FragmentTidingsArticleBinding
import com.example.tidings.ui.viewmodels.ArticleTidingsFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleTidingsFragment : Fragment(R.layout.fragment_tidings_article) {

    private val articleTidingsArgs by navArgs<ArticleTidingsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bindingArticleTidingsFragment = FragmentTidingsArticleBinding.bind(view)

        bindingArticleTidingsFragment.apply{
            val articleTidings = articleTidingsArgs.tidingsArticle

            webViewArticle.apply{
                webViewClient = WebViewClient()
                loadUrl(articleTidings.url)

            }
        }

    }
}