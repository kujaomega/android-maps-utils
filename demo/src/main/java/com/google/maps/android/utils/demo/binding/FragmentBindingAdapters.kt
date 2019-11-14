package com.google.maps.android.utils.demo.binding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import javax.inject.Inject

/**
 * Binding adapters that work with a fragment instance.
 */
class FragmentBindingAdapters @Inject constructor(val fragment: Fragment) {
    @BindingAdapter(value = ["imageFromUrl", "imageRequestListener"], requireAll = false)
    fun bindImage(imageView: ImageView, url: String?, listener: RequestListener<Drawable?>?) {
        Glide.with(fragment).load(url).transition(DrawableTransitionOptions.withCrossFade()).listener(listener).into(imageView)
    }
}