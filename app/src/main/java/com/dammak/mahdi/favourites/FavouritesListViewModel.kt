package com.dammak.mahdi.favourites

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dammak.mahdi.data.Favourite
import com.dammak.mahdi.network.FavouritesApi
import kotlinx.coroutines.launch
import java.lang.Exception

class FavouritesListViewModel : ViewModel() {
    enum class FavouritesApiStatus { LOADING, ERROR, DONE }

    // The internal MutableLiveData
    private val _listFavourite = MutableLiveData<List<Favourite>>()
    private val _status = MutableLiveData<FavouritesApiStatus>()

    // The external immutable LiveData
    val listFavourite: LiveData<List<Favourite>>
        get() = _listFavourite
    val status: LiveData<FavouritesApiStatus>
        get() = _status

    init {
        getFavouriteList()
    }

    private fun getFavouriteList() {
        viewModelScope.launch {
            try {
                _status.value = FavouritesApiStatus.LOADING
                val listFavourite = FavouritesApi.retrofitService.getAllFavourites()
                _listFavourite.value = listFavourite
                _status.value = FavouritesApiStatus.DONE
                Log.d("ws response", "Favourites list :" + listFavourite.toString())
            } catch (e: Exception) {
                _status.value = FavouritesApiStatus.ERROR
                Log.d("ws response", "error ws :" + e.message)
            }
        }
    }
}