package com.example.tidings.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.tidings.R
import com.example.tidings.data.model.TidingsArticle
import com.example.tidings.databinding.ItemBreakingTidingsBinding
import com.example.tidings.utils.OnItemClickListener
import kotlinx.android.synthetic.main.item_breaking_tidings.view.*

class SavedTidingsAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<SavedTidingsAdapter.SavedTidingsViewHolder>() {


    //Recyclerview Adapter için SavedTidingsViewHolder ı oluşturduk.
    inner class SavedTidingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    //DiffUtil, RecyclerView adapterındaki verilerin daha verimli bir şekilde güncellenmesi için kullanılır.
    private val differCallback = object : DiffUtil.ItemCallback<TidingsArticle>() {

        override fun areItemsTheSame(oldItem: TidingsArticle, newItem: TidingsArticle): Boolean {
            return oldItem.Id == newItem.Id
        }

        override fun areContentsTheSame(oldItem: TidingsArticle, newItem: TidingsArticle): Boolean {
            return oldItem == newItem
        }
    }

    //    differCallback'i AsyncListDiffer methodununu içerisine koyduktan sonra differ değişkenine atadık.
    val differ = AsyncListDiffer(this, differCallback)


    // Recyclerview ı create ettik.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedTidingsViewHolder {
        return SavedTidingsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_breaking_tidings,
                parent,
                false
            )
        )
    }

    //    Burada verileri belli bir güncel liste boyutuna göre boyutlandırdık.
    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    //
    override fun onBindViewHolder(holder: SavedTidingsViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this)
                .load(article.urlToImage)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.ic_baseline_image_not_supported_24)
                .into(image_view_image)

            text_view_breaking_news_title.text = article.title
            text_view_breaking_news_description.text = article.description
            text_view_breaking_news_date.text = article.publishedAt

            setOnClickListener {
                onClick(holder)
            }
        }
    }

    // Recyclerview içerisindeki tüm itemlara onClik özelliği getirildi.
    private fun onClick(holder: SavedTidingsViewHolder) {
        val position = holder.bindingAdapterPosition
        if (position != RecyclerView.NO_POSITION) {
            val item = differ.currentList[position]
            if (item != null)
                listener.onItemClick(item)
        }
    }


}