package com.example.tidings.ui.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
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

            // Burada ise BreakingFragment içrsidneki hata mesajı içerisnde ki Retry BUtonuna tekrardan verileri çekmesi için butonu aktifleştirdik.
            buttonRetryBreaking.setOnClickListener {
                adapter.retry()
            }
        }

        // viewmodel içerisinde tanımlanmış olan tidings değişkenini çağırdık.
        viewModel.tidings.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        // Uygulama ilk açıkldığında internet bağlatısı yoksa veya
        // uygulama içerisinde arama işlemi yapacakken internet erişimi olmadığı halde arama yaptığımızda apiden veriler gelmiyorsa karşımaza bir uyarı mesajı çıkacak.
        // İnternet geldiğinde ise bu uyarı mesajı içerisinde bulunan Retry butonu sayesinde verileri tekrardan çekebileceğiz.
        adapter.addLoadStateListener { loadState ->
            binding.apply {
                recyclerViewBreaking.isVisible = loadState.source.refresh is LoadState.NotLoading
                progressBarBreaking.isVisible = loadState.source.refresh is LoadState.Loading
                buttonRetryBreaking.isVisible = loadState.source.refresh is LoadState.Error
                textViewErrorBreaking.isVisible = loadState.source.refresh is LoadState.Error

                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    adapter.itemCount < 1
                ) {
                    recyclerViewBreaking.isVisible = false
                    textViewEmptyBreaking.isVisible = true
                } else {
                    textViewEmptyBreaking.isVisible = false
                }
            }
        }


        // Menuyü fragmentte bind ettik yani bağladık.
        setHasOptionsMenu(true)
    }


    // Search Menu yü breaking Tidings Fragmente bind ettik.
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        // Menuyü Uı da gösterdik
        inflater.inflate(R.menu.menu_tidings_search, menu)

        // TODO: 27.12.2021
        //  Bu kısımda  SearchView Functionality özelliğini BreakingTidingsFragmente bind etmiş olduk.
        // Yani böylelikle kullanıcıdan aldığımız query değerini viewmodeldeki searchTidings fonksiyonuna atamış olduk.
        // Sonra ise bu alınan query string değeri sayesinde UI da istenilen değeri filtreleme yani arama kısmı sayesinde göstermiş olduk.
        val searchItem = menu.findItem(R.id.menu_tidings_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    binding.recyclerViewBreaking.scrollToPosition(0)
                    viewModel.searchTidings(query)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })


    }

    // ViewBinding işlemi yapıldığğında onDestroyView methodunu çağırıp bindig i null olarak belirtmemiz geerek çünkü Breaking Tidings Fragmentte crashlenme olmaması için
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}