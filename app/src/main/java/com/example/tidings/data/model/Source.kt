package com.example.tidings.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Source(
    val id: String ?= null,
    val name: String ?= null
): Parcelable
