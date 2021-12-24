package com.example.tidings.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tidings.R
import com.example.tidings.databinding.FragmentSavedTidingsBinding
import com.example.tidings.ui.viewmodels.SavedTidingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedTidingsFragment : Fragment(R.layout.fragment_saved_tidings) {

    private val viewModel by viewModels<SavedTidingsViewModel>()
    private var _binding: FragmentSavedTidingsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentSavedTidingsBinding.bind(view)
    }

    // ViewBinding işlemi yapıldığğında onDestroyView methodunu çağırıp bindig i null olarak belirtmemiz geerek çünkü Saved Tidings Fragmentte crashlenme olmaması için
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}