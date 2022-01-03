package com.example.tidings.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.tidings.repository.TidingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopHandlingTidingsViewModel @Inject constructor(
    private val repository: TidingsRepository
) : ViewModel() {

    val currentCategory = MutableLiveData("sport")

    val topHandlingTidings = currentCategory.switchMap{categoryString ->
        repository.getTopHandlingTidingsResults(categoryString).cachedIn(viewModelScope)
    }
    fun searchTopHandlingTidings(category: String){
        currentCategory.value = category
    }
}