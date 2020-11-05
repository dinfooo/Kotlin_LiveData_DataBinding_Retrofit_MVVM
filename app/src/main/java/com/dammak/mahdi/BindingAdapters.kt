package com.dammak.mahdi

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.dammak.mahdi.favourites.FavouritesListViewModel


@BindingAdapter("favouritesApiStatus")
fun bindStatus(statusImageView: ImageView,
               status: FavouritesListViewModel.FavouritesApiStatus?) {
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

