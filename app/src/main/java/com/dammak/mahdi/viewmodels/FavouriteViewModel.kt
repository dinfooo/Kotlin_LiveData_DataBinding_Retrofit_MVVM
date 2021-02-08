package com.dammak.mahdi.viewmodels

import androidx.lifecycle.*
import com.dammak.mahdi.di.ActivityScope
import com.dammak.mahdi.domain.Favourite
import com.dammak.mahdi.repository.IFavouriteRepository
import com.dammak.mahdi.util.Event
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

// Scopes this ViewModel to components that use @ActivityScope
@ActivityScope
class FavouriteViewModel @Inject constructor (private val favouriteRepository : IFavouriteRepository) : ViewModel() {
    enum class FavouritesApiStatus { LOADING, ERROR, DONE }

    private val _status = MutableLiveData<FavouritesApiStatus>()
    val listFavourite = favouriteRepository.favourite
    val status: LiveData<FavouritesApiStatus>
        get() = _status
    private val _navigateToDetails = MutableLiveData<Event<Favourite>>()
    val navigateToDetails : LiveData<Event<Favourite>>
        get() = _navigateToDetails

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

    fun setSelectedFavourite(favourite: Favourite) {
        // Trigger the event by setting a new Event as a new value
        _navigateToDetails.value = Event(favourite)
    }
}