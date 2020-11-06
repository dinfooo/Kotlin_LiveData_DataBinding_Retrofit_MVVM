package com.dammak.mahdi

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
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
    id: Int
) {
    val clickListener = View.OnClickListener { v ->
        v?.let {
            Snackbar.make(
                it, "Click detected on favourite $id",
                Snackbar.LENGTH_LONG
            ).setAction("Action", null).show()
        }
    }
    view.setOnClickListener(clickListener)
}

