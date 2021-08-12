package com.example.fetchphotosexample.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchphotosexample.R
import com.example.fetchphotosexample.fragment.PhotosFragment
import com.example.fetchphotosexample.models.ImageItem
import com.example.fetchphotosexample.rest.Client
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RandomPhotoAdapter (private val image: ImageItem, val context: Context) : RecyclerView.Adapter<RandomPhotoAdapter.ModelViewHolder>() {
    class ModelViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val photo: ImageView = view.findViewById(R.id.iv_photo)
        val randBtn: Button = view.findViewById(R.id.randImgBtn)
        val getAllBtn: Button = view.findViewById(R.id.getAllImgsBtn)

        @SuppressLint("SetTextI18n")
        fun bindItems(item: ImageItem) {
            Picasso.get().load(item.url).into(photo)
        }
    }

    // ViewHolder ile Layout dosyasını ilişkilendirdik
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photo_item, parent, false)
        return ModelViewHolder(view)
    }

    // listenin uzunluğu
    override fun getItemCount(): Int {
        return 1
    }

    // View'lere yapılan atamaları tanımladık ve çağırdık
    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.bindItems(image)
        holder.randBtn.setOnClickListener {
            getRandPhoto(holder)
        }
        holder.getAllBtn.setOnClickListener {
            replaceFragment(PhotosFragment())
        }
    }

    private fun getRandPhoto(holder: ModelViewHolder) {
        val randPosition = (0..5000).random()
        Client.getApiService().getRandPhotos(randPosition).enqueue(object : Callback<ImageItem> {
            override fun onFailure(call: Call<ImageItem>, t: Throwable) {

            }

            override fun onResponse(call: Call<ImageItem>, response: Response<ImageItem>) {
                val photo: ImageItem? = response.body()
                holder.bindItems(photo!!)
            }
        })
    }

    private fun replaceFragment(fragment: Fragment?) {
        val transaction: FragmentTransaction = (context as AppCompatActivity?)?.supportFragmentManager!!.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment!!)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}