package com.dammak.mahdi.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.dammak.mahdi.database.AppDatabase
import com.dammak.mahdi.database.FavouritesLocalDataSourceImp
import com.dammak.mahdi.network.Api
import com.dammak.mahdi.repository.FavouriteRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

class FavouritesListViewModel(application: Application) : AndroidViewModel(application) {
    enum class FavouritesApiStatus { LOADING, ERROR, DONE }

    private val _status = MutableLiveData<FavouritesApiStatus>()

    /**
     * The data source this ViewModel will fetch results from.
     */
    private val favouriteRepository = FavouriteRepository(
        FavouritesLocalDataSourceImp(AppDatabase.getInstance(application).favouriteDao),
        Api.retrofitService
    )
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
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FavouritesListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return FavouritesListViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}