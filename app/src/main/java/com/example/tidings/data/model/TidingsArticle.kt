package com.example.tidings.data.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "tidingsTable")
data class TidingsArticle(
    @PrimaryKey(autoGenerate = true)
    var Id: Int? = null,
    val author: String? = null,
    val content: String,
    val description: String,
    val publishedAt: String,
    @Embedded(prefix = "source_")
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
): Parcelable
