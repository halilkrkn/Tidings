package com.example.tidings.utils

import com.example.tidings.data.model.TidingsArticle

// Buradaki interface yani arayüzümüz sayesinde onItemClik adında fonksiyon method oluşturduk ki TidingAdapter içerisindeki verileri onItemClick constructerı içerisine atadık.
// Sonra ise BreakingTidingsFragment içerisinde ilk önce OnOnItemClickListener ı extends ettik yani miras aldık ve bu sayede bu interface içerisindeki onItemClick methodunu BreakingTidingsFragmentte override ettik.
// Daha sonra ise bu methodu sayesinde ArticleTidingsFragmente de navigation işlemlerini kullanarak navigate ettirdik.
interface OnItemClickListener {

    fun onItemClick(tidingsArticle: TidingsArticle)

}