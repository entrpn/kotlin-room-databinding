package com.entrpn.room.livedata.example.util

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

class BindingHelper {
    companion object{
        @JvmStatic
        @BindingAdapter("loadUrl")
        fun setImageUrl(view: ImageView, url: String) {
            Glide.with(view.context).load(url).into(view)
        }
    }
}