package com.dammak.mahdi.util

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dammak.mahdi.R
import com.dammak.mahdi.domain.Favourite
import com.dammak.mahdi.ui.FavouriteAdapter
import com.dammak.mahdi.viewmodels.FavouriteViewModel
import com.google.android.material.snackbar.Snackbar


@BindingAdapter("favouritesApiStatus")
fun bindStatus(
    statusImageView: ImageView,
    status: FavouriteViewModel.FavouritesApiStatus?
) {
    when (status) {
        FavouriteViewModel.FavouritesApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        FavouriteViewModel.FavouritesApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        FavouriteViewModel.FavouritesApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(
    recyclerView: RecyclerView,
    data: List<Favourite>?
) {
    val adapter = recyclerView.adapter as FavouriteAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    }

}

