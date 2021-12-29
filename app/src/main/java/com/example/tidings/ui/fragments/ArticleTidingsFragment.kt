package com.example.tidings.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.tidings.R
import com.example.tidings.databinding.FragmentTidingsArticleBinding
import com.example.tidings.ui.viewmodels.ArticleTidingsFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleTidingsFragment : Fragment(R.layout.fragment_tidings_article) {

    // nav_graph da ArticleTidingsFragment in data lara ulaşması için ona argüment atadık  ve bu argüment ArticleTidings'dir.
    // Sonra ise navArgs sayesinde bunu articleTidingsArgs olarak atadık ki artık bu sayede ArticleTidings içerisindeki verilere ulaşabileceğiz.
    private val articleTidingsArgs by navArgs<ArticleTidingsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Burda viewBinding kullandığımız için ilgili fragment olan FragmentTidingsArticle xml dosyamızı bind ettik.
        val bindingArticleTidingsFragment = FragmentTidingsArticleBinding.bind(view)

        bindingArticleTidingsFragment.apply{
            val articleTidings = articleTidingsArgs.tidingsArticle

            // Burada ise oluşturmuş olduğumuz WebView ımızın id si sayesinde çağırdık ve webview içerisinde hazır bir method olan webViewClient ı atayıp uygulama içerisinde ilgili Url adresine yönlendirilmek için.
            // loadUrl ile de ilgili adresin url verildi.
            webViewArticleTidings.apply{
                webViewClient = WebViewClient()
                loadUrl(articleTidings.url)

            }
            // Buraya fonksiyonumuzu çağırmış olduk ve ilgili haber sitesinin name ni fonksiyonumuza atamış olduk.
            val actionBarTitle = articleTidings.source.name
            actionBarTitle(actionBarTitle)
        }
    }

    // Buradaki fonksiyon sayesinde ilgili haberin detaylarına uygulamadan çıkılmadan Webview ına yani sayfasına yönlendirildiğinde  actionBar da ilgili haber sitesinin ismi yazdırıldı.
    private fun actionBarTitle(appBarTitle: String){
        (activity as? AppCompatActivity)?.supportActionBar?.title = appBarTitle
    }
}