package com.example.fetchphotosexample.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchphotosexample.R
import com.example.fetchphotosexample.models.ImageItem
import com.squareup.picasso.Picasso

class PhotosAdapter(private val images: List<ImageItem>, val context: Context) :
        RecyclerView.Adapter<PhotosAdapter.ModelViewHolder>() {
    class ModelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val photo: ImageView = view.findViewById(R.id.iv_photos)

        @SuppressLint("SetTextI18n")
        fun bindItems(item: ImageItem) {
            Picasso.get().load(item.url).into(photo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photos_item, parent, false)
        return ModelViewHolder(view)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.bindItems(images[position])
    }
}