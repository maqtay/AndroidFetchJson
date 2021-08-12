package com.example.fetchphotosexample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchphotosexample.R
import com.example.fetchphotosexample.adapter.PhotosAdapter
import com.example.fetchphotosexample.models.ImageItem
import com.example.fetchphotosexample.rest.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotosFragment(): Fragment() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.photosRecyclerView)

        GridLayoutManager(
            context,
            2,
            RecyclerView.VERTICAL,
            false,
        ).apply { recyclerView.layoutManager = this }

        getPhotos()
    }

    private fun getPhotos() {
        Client.getApiService().getAllPhotos().enqueue(object : Callback<List<ImageItem>> {
            override fun onResponse(
                call: Call<List<ImageItem>>,
                response: Response<List<ImageItem>>
            ) {
                val photos: List<ImageItem>? = response.body()
                recyclerView.adapter = photos?.let { PhotosAdapter(it, context!!) }
            }

            override fun onFailure(call: Call<List<ImageItem>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}