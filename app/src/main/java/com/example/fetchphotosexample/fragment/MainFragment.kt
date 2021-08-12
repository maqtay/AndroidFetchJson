package com.example.fetchphotosexample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchphotosexample.R
import com.example.fetchphotosexample.adapter.RandomPhotoAdapter
import com.example.fetchphotosexample.models.ImageItem
import com.example.fetchphotosexample.rest.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        getRandPhotos()
    }

    fun getRandPhotos() {
        val randPosition = (0..5000).random()

        Client.getApiService().getRandPhotos(randPosition).enqueue(object : Callback<ImageItem> {
            override fun onFailure(call: Call<ImageItem>, t: Throwable) {
            }

            override fun onResponse(call: Call<ImageItem>, response: Response<ImageItem>) {
                val photo: ImageItem? = response.body()
                recyclerView.adapter = photo?.let { RandomPhotoAdapter(it, context!!) }
            }
        })
    }
}
