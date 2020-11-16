package com.dammak.mahdi

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dammak.mahdi.data.Favourite
import com.dammak.mahdi.favourites.FavouriteAdapter
import com.dammak.mahdi.favourites.FavouritesListViewModel
import com.google.android.material.snackbar.Snackbar


@BindingAdapter("favouritesApiStatus")
fun bindStatus(
    statusImageView: ImageView,
    status: FavouritesListViewModel.FavouritesApiStatus?
) {
    when (status) {
        FavouritesListViewModel.FavouritesApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        FavouritesListViewModel.FavouritesApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        FavouritesListViewModel.FavouritesApiStatus.DONE -> {
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

@BindingAdapter("android:onClick")
fun setOnClick(
    view: View,
    favourite: Favourite
) {
    val clickListener = View.OnClickListener { v ->
        v?.let {
            Snackbar.make(
                it, "Click detected on favourite ${favourite.id}",
                Snackbar.LENGTH_LONG
            ).setAction("Action", null).show()
        }
    }
    view.setOnClickListener(clickListener)
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

