package com.example.a30androidwithkotlie.ch15_service

import com.example.a30androidwithkotlie.ch15_dto.ch15_VideoDto
import retrofit2.Call
import retrofit2.http.GET

interface ch15_VideoService {
    @GET("/v3/0b56ef9f-0cf9-45e0-a207-7fa9ea568fc5")
    fun listVideos() : Call<ch15_VideoDto>
}