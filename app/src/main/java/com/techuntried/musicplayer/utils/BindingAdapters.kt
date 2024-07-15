package com.techuntried.musicplayer.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("imageResource")
fun setImageResource(imageView: ImageView, resource: Int) {
    imageView.setImageResource(resource)
}
