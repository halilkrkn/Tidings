package com.example.tidings.ui.fragments.breaking

import androidx.lifecycle.ViewModel
import com.example.tidings.repository.TidingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BreakingTidingsViewModel @Inject constructor (private val repository: TidingsRepository): ViewModel() {
}
