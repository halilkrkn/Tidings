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
class SavedTidingsViewModel @Inject constructor(
    private val repository: TidingsRepository,
) : ViewModel() {

    // Database e haberleri insert etmek için repositoryden o fonksiyonu çekiyoruz.
    fun deleteArticleTidings(article: TidingsArticle) = viewModelScope.launch{
        repository.deleteArticleTidings(article)
    }

    // Saved sayfasındaki kayıt altına alınmış haberleri silmek için repositoryden o fonksiyonu çekiyoruz.
    fun insertArticleTidings(article: TidingsArticle) = viewModelScope.launch {
        repository.insertArticleTidings(article)
    }

    // Saved Sayfasına kayıt altına almış olduğumuz verileri liste halinde gösterebilmek için repositoryden o fonksiyonu çekiyoruz.
    fun getSaveTidings() = repository.saveArticle()

}