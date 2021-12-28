package com.example.tidings.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.tidings.R
import com.example.tidings.data.model.TidingsArticle
import com.example.tidings.databinding.ItemBreakingTidingsBinding
import com.example.tidings.utils.OnItemClickListener

class TidingsAdapter(private val listener: OnItemClickListener) : PagingDataAdapter<TidingsArticle, TidingsAdapter.TidingsViewHolder>(TIDINGS_COMPARATOR) {

    // Bu class sayesinde RecyclerView mantığı gören PagingDataAdapterı için gerekli tüm aşamalar gerçekleştirip ViewBinding kullanıldığı için ItemBreakingTidingsBinding.xml ine bind ettik yani bağladık.
    // Sonra ise o fragment içerisinde oluşturduğumuz özelliklere tanımlamalar verdik ve UI da göstermesini istedik.
    inner class TidingsViewHolder(private val binding: ItemBreakingTidingsBinding) :
        RecyclerView.ViewHolder(binding.root) {

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
                    val item = getItem(position)
                    if (item != null)
                        listener.onItemClick(item)
                }
            }
        }



    }

    //Burada viewHolder ı create ettik.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TidingsViewHolder {
        val binding =
            ItemBreakingTidingsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TidingsViewHolder(binding)
    }

    //Burada ise bind işlemi yani bağlaman işlemi yaparak TidingsViewHolder a bağladık.
    override fun onBindViewHolder(holder: TidingsViewHolder, position: Int) {
        val currentPosition = getItem(position)

        if (currentPosition != null) {
            holder.bind(currentPosition)
        }
    }

    // DiffUtil, RecyclerView adapterındaki verilerin daha verimli bir şekilde güncellenmesi için kullanılır.
    //  RecyclerView’daki veriyi güncellemek veya filtreleme ihtiyacımız olabiliyor. En verimlli yöntem DiffUtili kulanmaktır.
    //   DiffUtil iki liste arasındaki farkı hesaplayıp bize güncel listeyi veren bir utility sınıfıdır. DiffUtil iki listeyi karşılaştırıp minimum güncelleme sayısını hesaplamak için Eugene W. Myers‘in fark algoritmasını kullanıyor.
    companion object {
        private val TIDINGS_COMPARATOR = object : DiffUtil.ItemCallback<TidingsArticle>() {

            override fun areItemsTheSame(
                oldItem: TidingsArticle,
                newItem: TidingsArticle
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: TidingsArticle, newItem: TidingsArticle
            ): Boolean {
                return oldItem == newItem
            }

        }
    }


}