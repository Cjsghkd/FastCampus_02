package com.example.a30androidwithkotlie.ch12_model

import com.google.gson.annotations.SerializedName

data class Ch12_Book (
    @SerializedName("itemId") val id : Long,
    @SerializedName("title") val title : String,
    @SerializedName("description") val description : String,
    @SerializedName("coverSmallUrl") val coverSmallUrl : String
)