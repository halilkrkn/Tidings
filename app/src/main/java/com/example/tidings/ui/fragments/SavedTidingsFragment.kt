package com.example.tidings.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tidings.R
import com.example.tidings.data.model.TidingsArticle
import com.example.tidings.databinding.FragmentSavedTidingsBinding
import com.example.tidings.ui.adapters.SavedTidingsAdapter
import com.example.tidings.ui.viewmodels.SavedTidingsViewModel
import com.example.tidings.utils.OnItemClickListener
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedTidingsFragment : Fragment(R.layout.fragment_saved_tidings), OnItemClickListener {

    private val viewModel by viewModels<SavedTidingsViewModel>()
    private var _binding: FragmentSavedTidingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var savedAdapter: SavedTidingsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentSavedTidingsBinding.bind(view)

        savedAdapter = SavedTidingsAdapter(this)
        binding.recyclerViewSavedTidings.apply {
            setHasFixedSize(true)
            adapter = savedAdapter
            layoutManager = LinearLayoutManager(activity)
        }


        // Save edilmiş olan haberleri kaldırmak için saga vya sola hareketi sağlanarak saved durumundan çıkarıyoruzz ve databasedende siliyoruz.
        // Eğer silme esnasında silinmesini istemiyorsakda geri al butonu sayesinde o sildiğimiz veriyi tekrardan geri getiriyoruz.
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            // Burada silme ve geri alma işlemi için gerekli komutlar yazıldı.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition
                val article = savedAdapter.differ.currentList[position]
                viewModel.deleteArticleTidings(article)
                Snackbar.make(view, "Successfully deleted article", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo") {
                        viewModel.insertArticleTidings(article)
                    }
                    show()
                }
            }
        }

//        Burada oluşturduğumuz itemTouchHelperCallback değişkenini çağırıp recyclerview a atadık
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.recyclerViewSavedTidings)
        }

        // Burada view Model içerisindeki getSaveTidings() fonksiyonu sayesinde veritabanına saved edilmiş verileri SaveTidingsFragmen'i içerisinsinde listeledik.
         viewModel.getSaveTidings().observe(viewLifecycleOwner) { articles ->
            savedAdapter.differ.submitList(articles)
        }
    }

    // SaveTidingsFragment içerisinde listelenmiş olan kayıt altındaki haberlerin üzerine tıklanarak yine haberlerin detayına erişilmesi sağlandı.
    override fun onItemClick(tidingsArticle: TidingsArticle) {
        val action =
            SavedTidingsFragmentDirections.actionSavedTidingsFragmentToArticleTidingsFragment(
                tidingsArticle
            )
        findNavController().navigate(action)
    }

    // ViewBinding işlemi yapıldığğında onDestroyView methodunu çağırıp bindig i null olarak belirtmemiz geerek çünkü Saved Tidings Fragmentte crashlenme olmaması için
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}