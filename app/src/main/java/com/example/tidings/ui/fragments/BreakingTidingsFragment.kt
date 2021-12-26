package com.example.tidings.ui.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tidings.R
import com.example.tidings.databinding.FragmentBreakingTidingsBinding
import com.example.tidings.ui.adapters.TidingsAdapter
import com.example.tidings.ui.adapters.TidingsLoadStateAdapter
import com.example.tidings.ui.viewmodels.BreakingTidingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreakingTidingsFragment : Fragment(R.layout.fragment_breaking_tidings) {

    private val viewModel by viewModels<BreakingTidingsViewModel>()
    private var _binding: FragmentBreakingTidingsBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentBreakingTidingsBinding.bind(view)

        // RecyclerView Adapter Tanımlandı.
        val adapter = TidingsAdapter()
        binding.apply {
            recyclerViewBreaking.setHasFixedSize(true)
            // LoadStateAdapter da oluşturmuş olduğumuz hata görüntüsünü recyclerview de göstermek için adapter.withLoadStateHeaderAndFooter methodunu kullanıyoruz.
            // Böylelikle hata mesajımız olan desingımız recyclerviewın hem header hemde footer kısmında gözükmektedir.
            recyclerViewBreaking.adapter = adapter.withLoadStateHeaderAndFooter(
                header = TidingsLoadStateAdapter { adapter.retry() },
                footer = TidingsLoadStateAdapter { adapter.retry() }

            )
        }

        // viewmodel içerisinde tanımlanmış olan tidings değişkenini çağırdık.
        viewModel.tidings.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

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