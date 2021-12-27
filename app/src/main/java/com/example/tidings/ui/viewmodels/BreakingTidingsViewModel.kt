package com.example.tidings.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.tidings.data.db.TidingsDao
import com.example.tidings.repository.TidingsRepository
import com.example.tidings.utils.Constants.Companion.DEFAULT_QUERY
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BreakingTidingsViewModel @Inject constructor(
    private val repository: TidingsRepository
) : ViewModel() {

    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    val tidings = currentQuery.switchMap{queryString ->
        repository.getTidingsSearchResults(queryString).cachedIn(viewModelScope)
    }

    // TODO: 27.12.2021
    // burada search yapamak istediğimiz haberleri filtreleme yapmak için gerekli string değerleri kullanıcıdan alıyoruz.
    // Ve Alınan string değer yani query i currentQuery nin yeni değer iolarka atıyoruz.
    // Sonrasında ise filtreleme işlemi yapılmış oluyor ve UI'da istenilen değerdeki string(query) sayesindeki haber karşımıza çıkıyor.
    fun searchTidings(query: String){
        currentQuery.value = query
    }
}
