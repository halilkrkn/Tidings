package com.example.tidings.ui.fragments.breaking

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tidings.R
import com.example.tidings.databinding.FragmentBreakingTidingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreakingTidingsFragment : Fragment(R.layout.fragment_breaking_tidings) {

    private val viewModel by viewModels<BreakingTidingsViewModel>()
    private var _binding: FragmentBreakingTidingsBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentBreakingTidingsBinding.bind(view)

        // Menuyü fragmentte bind ettik yani bağladık.
        setHasOptionsMenu(true)
    }


    // Search Menu yü breaking Tidings Fragmente bind ettik.
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        // Menuyü Uı da gösterdik
        inflater.inflate(R.menu.menu_tidings_search, menu)

    }

    // ViewBinding işlemi yapıldığğında onDestroyView methodunu çağırıp bindig i null olarak belirtmemiz geerek çünkü Breaking Tidings Fragmentte crashlenme olmaması için
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}