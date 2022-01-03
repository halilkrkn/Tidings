package com.example.tidings.ui.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.tidings.R
import com.example.tidings.data.model.TidingsArticle
import com.example.tidings.databinding.FragmentTopHandlingTidingsBinding
import com.example.tidings.ui.adapters.TidingsAdapter
import com.example.tidings.ui.adapters.TidingsLoadStateAdapter
import com.example.tidings.ui.viewmodels.TopHandlingTidingsViewModel
import com.example.tidings.utils.OnItemClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopHandlingTidingsFragment : Fragment(R.layout.fragment_top_handling_tidings), OnItemClickListener
{
    private val viewModel by viewModels<TopHandlingTidingsViewModel>()
    private var _binding: FragmentTopHandlingTidingsBinding? = null
    private val binding get() = _binding!!
    lateinit var topHandlingAdapter: TidingsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentTopHandlingTidingsBinding.bind(view)

        (activity as? AppCompatActivity)?.supportActionBar?.title = "Top-Handling Tidings"

        topHandlingAdapter = TidingsAdapter(this)

        binding.apply {
            recyclerViewTopHandling.setHasFixedSize(true)
            recyclerViewTopHandling.adapter = topHandlingAdapter.withLoadStateHeaderAndFooter(
                header = TidingsLoadStateAdapter { topHandlingAdapter.retry() },
                footer = TidingsLoadStateAdapter { topHandlingAdapter.retry() }
            )

            buttonRetryTopHandling.setOnClickListener {
                topHandlingAdapter.retry()
            }
        }

        viewModel.topHandlingTidings.observe(viewLifecycleOwner) {
            topHandlingAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        topHandlingAdapter.addLoadStateListener { loadState ->

            binding.apply {
                recyclerViewTopHandling.isVisible = loadState.source.refresh is LoadState.NotLoading
                progressBarTopHandling.isVisible = loadState.source.refresh is LoadState.Loading
                buttonRetryTopHandling.isVisible = loadState.source.refresh is LoadState.Error
                textViewErrorTopHandling.isVisible = loadState.source.refresh is LoadState.Error

                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    topHandlingAdapter.itemCount < 1
                ) {
                    recyclerViewTopHandling.isVisible = false
                    textViewEmptyTopHandling.isVisible = true
                } else {
                    textViewEmptyTopHandling.isVisible = false
                }
            }
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_tidings_search, menu)

        val searchItem = menu.findItem(R.id.menu_tidings_search)
        val searchView = searchItem.actionView as SearchView

        searchView.queryHint = "Select A Category Sport, Health, Business "
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    binding.recyclerViewTopHandling.scrollToPosition(0)
                    viewModel.searchTopHandlingTidings(query)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })


    }

    override fun onItemClick(tidingsArticle: TidingsArticle) {
        val action =
            TopHandlingTidingsFragmentDirections.actionTopHandlingTidingsFragmentToArticleTidingsFragment(
                tidingsArticle
            )
        findNavController().navigate(action)
    }

    // ViewBinding işlemi yapıldığğında onDestroyView methodunu çağırıp bindig i null olarak belirtmemiz geerek çünkü Breaking Tidings Fragmentte crashlenme olmaması için
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}