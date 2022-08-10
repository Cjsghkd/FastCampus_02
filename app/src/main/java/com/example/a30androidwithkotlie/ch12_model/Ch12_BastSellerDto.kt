package com.example.a30androidwithkotlie.ch12_model

import com.google.gson.annotations.SerializedName

data class Ch12_BastSellerDto(
    @SerializedName("title") val title : String,
    @SerializedName("item") val books : List<Ch12_Book>
)