package com.example.a30androidwithkotlie.ch12_api

import com.example.a30androidwithkotlie.ch12_model.Ch12_BastSellerDto
import com.example.a30androidwithkotlie.ch12_model.Ch12_SearchBookDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Ch12_BookService {
    @GET("/api/search.api?output=json")
    fun getBooksByName(
        @Query("key") apiKey : String,
        @Query("query") keyword : String
    ) : Call<Ch12_SearchBookDto>

    @GET("/api/bestSeller.api?output=json&categoryId=100")
    fun getBestSellerBooks(
        @Query("key") apiKey : String
    ) : Call<Ch12_BastSellerDto>
}