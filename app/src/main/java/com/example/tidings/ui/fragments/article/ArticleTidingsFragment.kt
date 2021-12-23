package com.example.tidings.ui.fragments.article

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tidings.R
import com.example.tidings.databinding.FragmentBreakingTidingsBinding
import com.example.tidings.databinding.FragmentTidingsArticleBinding
import com.example.tidings.ui.fragments.breaking.BreakingTidingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleTidingsFragment : Fragment(R.layout.fragment_tidings_article) {

    private val viewModel by viewModels<ArticleTidingsFragmentViewModel>()
    private var _binding: FragmentTidingsArticleBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentTidingsArticleBinding.bind(view)
    }


    // ViewBinding işlemi yapıldığğında onDestroyView methodunu çağırıp bindig i null olarak belirtmemiz geerek çünkü Article Tidings Fragmentte crashlenme olmaması için
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}