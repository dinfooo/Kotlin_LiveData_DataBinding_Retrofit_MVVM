package com.dammak.mahdi.viewmodels

import androidx.lifecycle.*
import com.dammak.mahdi.repository.IFavouriteRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

class FavouritesListViewModel(private val favouriteRepository : IFavouriteRepository) : ViewModel() {
    enum class FavouritesApiStatus { LOADING, ERROR, DONE }

    private val _status = MutableLiveData<FavouritesApiStatus>()
    val listFavourite = favouriteRepository.favourite
    val status: LiveData<FavouritesApiStatus>
        get() = _status

    init {
        getFavouriteList()
    }

    private fun getFavouriteList() {
        viewModelScope.launch {
            try {
                _status.value =
                    FavouritesApiStatus.LOADING
                favouriteRepository.refreshFavourite()
                _status.value =
                    FavouritesApiStatus.DONE
                Timber.d(
                    "From DataBase -> Favourites list from database:" +
                            listFavourite.value.toString()
                )
            } catch (e: Exception) {
                _status.value =
                    FavouritesApiStatus.ERROR
                Timber.e("error ws response :" + e.message)
            }
        }
    }

    /**
     * Factory for constructing FavouritesListViewModel with parameter
     */
    class Factory(private val tasksRepository: IFavouriteRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FavouritesListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return FavouritesListViewModel(tasksRepository) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}