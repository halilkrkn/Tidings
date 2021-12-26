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
    private val repository: TidingsRepository,
    private val tidingsDao: TidingsDao
) : ViewModel() {

    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    val tidings = currentQuery.switchMap{queryString ->
        repository.getTidingsSearchResults(queryString).cachedIn(viewModelScope)
    }

    // burada search yapamak istediğimiz photoların isimlerine göre filtreleme yapmak için gerekli string değerleri alıyoruz.
    fun searchPhotos(query: String){
        currentQuery.value = query
    }
}
