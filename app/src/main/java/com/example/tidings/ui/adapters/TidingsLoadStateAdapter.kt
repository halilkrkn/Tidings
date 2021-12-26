package com.example.tidings.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tidings.databinding.TidingsLoadStateFooterAndHeaderBinding


// Burada Eğer internet bağlatısı kesilirse yada apiden veri yüklenmezse uygulama içersinde recyclerview ın en üst kısmında ve en alt kısmında uyarı mesajı çıkmaktadır.
// Böylelikle bu uyarı mesajı sayesinde eğer internet ya da api veri bağlantısı geri gelirse Retry butonu ile verileri  tekrar çağırabiliyoruz.
// BU özellik Paging 3 kütüphanesinin içerisinde hazır halde bulunan LoadStateAdapter methodu sayesinde yapılmaktadır.
// TidingsBreakingFargmentta ise adapter tanımladığımızyerden LoadStateAdapter ı da tanımlıyoruz.
class TidingsLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<TidingsLoadStateAdapter.LoadStateViewHolder>() {

    inner class LoadStateViewHolder(private val binding: TidingsLoadStateFooterAndHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //retry methodu ile paginationın içerisinde var olan retry methodunu TidingsBreaking Fragmentte alıp buradaki retry methoduna aktardık. Böylelikle UI da karşımıza çıkan butonu kullanabilir özelllliğie kabvuşturduk ve internet bağlantısı tekrar geldiğinde verileri tekrar çekme işlemi yapılmış oldu.
        init {
            binding.buttonRetry.setOnClickListener {
                retry.invoke()
            }
        }

        // TidingsLoadStateFooterAndHeaderBinding view içerisinde olan desinglarımıza gerekli komutları verdik.
        fun bind(loadState: LoadState) {
            binding.apply {
                progressBarLoadState.isVisible = loadState is LoadState.Loading
                buttonRetry.isVisible = loadState !is LoadState.Loading
                textViewError.isVisible = loadState !is LoadState.Loading
            }

        }
    }


    // LoadState Adapter kurulumunu yaptık. Klasik Recycler View kurulumu gibi.
    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = TidingsLoadStateFooterAndHeaderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }


}