package com.example.tidings.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tidings.data.db.TidingsDao
import com.example.tidings.data.model.TidingsArticle
import com.example.tidings.repository.TidingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleTidingsFragmentViewModel @Inject constructor(
    private val repository: TidingsRepository,
) : ViewModel() {

    fun insertArticleTidings(article: TidingsArticle) = viewModelScope.launch {
        repository.insertArticleTidings(article)
    }

}