package com.example.fetchphotosexample.rest

import com.example.fetchphotosexample.models.ImageItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Services {

    // get rand images
    @GET("/photos/{id}")
    fun getRandPhotos(@Path("id") id: Int): Call<ImageItem>

    // get all photos
    @GET("/photos")
    fun getAllPhotos() : Call<List<ImageItem>>
}