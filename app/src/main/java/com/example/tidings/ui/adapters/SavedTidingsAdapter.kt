package com.example.tidings.ui.adapters

import android.view.LayoutInflater
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

class SavedTidingsAdapter(private val listener: OnItemClickListener): RecyclerView.Adapter<SavedTidingsAdapter.SavedTidingsViewHolder>() {


    inner class SavedTidingsViewHolder(private val binding: ItemBreakingTidingsBinding) :
        RecyclerView.ViewHolder(binding.root){

            fun bind(tidings: TidingsArticle) {
                binding.apply {
                    Glide.with(itemView)
                        .load(tidings.urlToImage)
                        .centerCrop()
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .error(R.drawable.ic_baseline_image_not_supported_24)
                        .into(imageViewImage)

                    textViewBreakingNewsTitle.text = tidings.title
                    textViewBreakingNewsDescription.text = tidings.description
                    textViewBreakingNewsDate.text = tidings.publishedAt
                }
            }


        // Burada RecyclerView içerisindeki her bir itema tıklama özelliği getirdik.
        init{
            binding.root.setOnClickListener{
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION){
                    val item = differ.currentList[position]
                    if (item != null)
                        listener.onItemClick(item)
                }
            }
        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedTidingsViewHolder {
        val binding =
            ItemBreakingTidingsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SavedTidingsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    override fun onBindViewHolder(holder: SavedTidingsViewHolder, position: Int) {
        val currentPosition = differ.currentList[position]

        if (currentPosition != null) {
            holder.bind(currentPosition)
        }
    }

    //DiffUtil, RecyclerView adapterındaki verilerin daha verimli bir şekilde güncellenmesi için kullanılır.
    private val differCallback = object: DiffUtil.ItemCallback<TidingsArticle>(){

        override fun areItemsTheSame(oldItem: TidingsArticle, newItem: TidingsArticle): Boolean {
            return oldItem.Id == newItem.Id

        }

        override fun areContentsTheSame(oldItem: TidingsArticle, newItem: TidingsArticle): Boolean {
            return  oldItem == newItem
        }

    }

     val differ = AsyncListDiffer(this,differCallback)


}