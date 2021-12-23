package com.example.tidings.ui.fragments.saved

import androidx.lifecycle.ViewModel
import com.example.tidings.data.db.TidingsDao
import com.example.tidings.repository.TidingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SavedTidingsViewModel @Inject constructor(
    private val repository: TidingsRepository,
    private val tidingsDao: TidingsDao
) : ViewModel() {
}