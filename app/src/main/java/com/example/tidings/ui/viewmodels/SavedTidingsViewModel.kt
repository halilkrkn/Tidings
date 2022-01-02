package com.example.tidings.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.tidings.data.db.TidingsDao
import com.example.tidings.data.model.TidingsArticle
import com.example.tidings.repository.TidingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedTidingsViewModel @Inject constructor(
    private val repository: TidingsRepository,
) : ViewModel() {

    // Database e haberleri insert etmek için repositoryden o fonksiyonu çekiyoruz.
    fun insertArticleTidings(article: TidingsArticle) = viewModelScope.launch {
        repository.insertArticleTidings(article)
    }

    // Saved sayfasındaki kayıt altına alınmış haberleri silmek için repositoryden o fonksiyonu çekiyoruz.
    fun deleteArticleTidings(article: TidingsArticle) = viewModelScope.launch{
        repository.deleteArticleTidings(article)
    }

    // Saved Sayfasına kayıt altına almış olduğumuz verileri liste halinde gösterebilmek için repositoryden o fonksiyonu çekiyoruz.
    fun getSaveTidings() = repository.saveArticle()


    // Filtreleme İşlemleri için Repositoryden oluşturudpumuz search fonk getiriyoruz.
    val searchQuery = MutableStateFlow("")

    val searchFlow = searchQuery.flatMapLatest { queryString ->
        repository.searchSavedName(queryString)
    }.asLiveData()

    fun search(query: String){
        searchQuery.value = query
    }

}
